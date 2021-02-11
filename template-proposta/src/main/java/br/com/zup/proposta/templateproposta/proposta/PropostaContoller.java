package br.com.zup.proposta.templateproposta.proposta;

import br.com.zup.proposta.templateproposta.api.consulta.NovaAnaliseSolicitacaoRequest;
import br.com.zup.proposta.templateproposta.api.consulta.NovaAnaliseSolicitacaoResponse;
import br.com.zup.proposta.templateproposta.api.consulta.SolicitacaoAnalise;
import br.com.zup.proposta.templateproposta.exceptions.ApiErrorException;
import feign.FeignException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.util.UriComponentsBuilder;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.net.URI;

@RestController
@RequestMapping("/api/propostas")
public class PropostaContoller {
    @Autowired
    private SolicitacaoAnalise solicitacaoAnalise;

    @Autowired
    private PropostaRepository propostaRepository;

    @PostMapping
    @Transactional
    public ResponseEntity<?> novaProposta(@RequestBody @Valid NovaPropostaRequest request,
                                          UriComponentsBuilder uriBuilder){

        var findDocumento = propostaRepository.findByDocumento(request.getDocumento());
        if(findDocumento.isPresent()){
            throw new ApiErrorException(HttpStatus.UNPROCESSABLE_ENTITY,"Já existe uma proposta para o documento informado!");
        }
        Proposta proposta = request.toProposta();
        propostaRepository.save(proposta);

        try {
            EstadoProposta estadoProposta = solicitacaoAnalise.getSolicitacao(new NovaAnaliseSolicitacaoRequest(proposta))
                                                              .getResultadoSolicitacao()
                                                              .getEstadoProposta();
            proposta.atualizaStatus(estadoProposta);
        } catch (FeignException.UnprocessableEntity e){
            proposta.atualizaStatus(EstadoProposta.NAO_ELEGIVEL);
        }

        propostaRepository.save(proposta);

        URI location = uriBuilder.path("/api/propostas/{id}").buildAndExpand(proposta.getId()).toUri();
        return ResponseEntity.created(location).build();
    }
}
