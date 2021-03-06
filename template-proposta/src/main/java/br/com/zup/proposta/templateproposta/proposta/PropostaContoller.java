package br.com.zup.proposta.templateproposta.proposta;

import br.com.zup.proposta.templateproposta.api.analiseSolicitacao.NovaAnaliseSolicitacaoRequest;
import br.com.zup.proposta.templateproposta.api.analiseSolicitacao.SolicitacaoAnalise;
import br.com.zup.proposta.templateproposta.api.cartao.ConsultaCartao;
import br.com.zup.proposta.templateproposta.config.metrics.MyMetrics;
import br.com.zup.proposta.templateproposta.config.security.CryptDecryptUtil;
import br.com.zup.proposta.templateproposta.exceptions.ApiErrorException;
import feign.FeignException;
import io.opentracing.Span;
import io.opentracing.Tracer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.Optional;

@RestController
@RequestMapping("/api/propostas")
public class PropostaContoller {
    private final Logger logger = LoggerFactory.getLogger(PropostaContoller.class);

    @Autowired
    private SolicitacaoAnalise solicitacaoAnalise;
    @Autowired
    private ConsultaCartao consultaCartao;
    @Autowired
    private PropostaRepository propostaRepository;
    @Autowired
    private MyMetrics myMetrics;
    @Autowired
    private Tracer tracer;
    @Autowired
    private CryptDecryptUtil cryptDecryptUtil;

    @PostMapping
    public ResponseEntity<?> novaProposta(@RequestBody @Valid NovaPropostaRequest request,
                                          UriComponentsBuilder uriBuilder){
        Span activeSpan = tracer.activeSpan();
        //TAGS
        activeSpan.setTag("user.email", request.getEmail());
        //BAGGAGE
        activeSpan.setBaggageItem("traceID", "0001");
        //LOG
        activeSpan.log("novaProposta");
        if(propostaRepository.findByDocumento(request.getDocumento()).isPresent()){
            logger.info("Proposta recusada, já existe proposta para documento " + request.getDocumento());
            throw new ApiErrorException(HttpStatus.UNPROCESSABLE_ENTITY,"Já existe uma proposta para o documento informado!");
        }

        Proposta proposta = request.toProposta();
        propostaRepository.save(proposta);

        try {
            logger.info("Realizando a solicitação de análise para documento  {}",proposta.getDocumento());
            EstadoProposta estadoProposta = solicitacaoAnalise.getSolicitacao(new NovaAnaliseSolicitacaoRequest(proposta))
                                                              .getResultadoSolicitacao()
                                                              .getEstadoProposta();
            proposta.atualizaStatus(estadoProposta);
        } catch (FeignException.UnprocessableEntity e){
            proposta.atualizaStatus(EstadoProposta.NAO_ELEGIVEL);
        }
        logger.info("Retornou da análise: " + proposta.getAnalise());

        propostaRepository.save(proposta);

        myMetrics.proposalCounter();

        URI location = uriBuilder.path("/api/propostas/{id}").buildAndExpand(proposta.getId()).toUri();
        logger.info("Devolveu a URI " + location);
        return ResponseEntity.created(location).build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getProposta(@PathVariable("id") Long id){
        Optional<Proposta> proposta = propostaRepository.findById(id);
        if(proposta.isEmpty()){
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(new PropostaResponse(proposta.get()));
    }
}
