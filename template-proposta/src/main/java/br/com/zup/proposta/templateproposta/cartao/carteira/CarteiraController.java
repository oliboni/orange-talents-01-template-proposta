package br.com.zup.proposta.templateproposta.cartao.carteira;

import br.com.zup.proposta.templateproposta.api.carteira.EnviaCarteira;
import br.com.zup.proposta.templateproposta.cartao.Cartao;
import br.com.zup.proposta.templateproposta.cartao.CartaoRepository;
import feign.FeignException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.Optional;

@RestController
@RequestMapping("/api/cartao")
public class CarteiraController {

    private static Logger logger = LoggerFactory.getLogger(CarteiraController.class);

    @Autowired
    private CartaoRepository cartaoRepository;
    @Autowired
    private CarteiraRepository carteiraRepository;
    @Autowired
    private EnviaCarteira enviaCarteira;

    @PostMapping("/{id}/carteira")
    public ResponseEntity<?> criaCarteira(@PathVariable("id") Long idCartao,
                                          @RequestBody @Valid NovaCarteiraRequest request,
                                          UriComponentsBuilder uriBuilder){
        Optional<Cartao> cartao = cartaoRepository.findById(idCartao);
        if (cartao.isEmpty()){
            return ResponseEntity.notFound().build();
        }

        if (carteiraRepository.findByCarteiraDigitalAndCartaoId(request.toCarteiraDigital(),cartao.get().getId()).isPresent()){
            return ResponseEntity.unprocessableEntity().body("Já existe uma carteira associada a este cartão e a "+request.getCarteiraDigital());
        }

        try {
            var carteiraResponse = enviaCarteira.enviaCarteira(cartao.get().getNumero(),
                                                                                          new EnviaCarteira.EnviaCarteiraRequest(request.getEmail(),
                                                                                                                                 request.getCarteiraDigital()));
            logger.info("Carteira enviada, retorno: " + carteiraResponse);
        } catch (FeignException e){
            logger.info("Erro ao enviar carteira, " + e);
            return ResponseEntity.status(500).build();
        }

        Carteira carteira = request.toCarteira(cartao.get());
        carteiraRepository.save(carteira);

        URI location = uriBuilder.path("/api/cartao/carteira/{id}").buildAndExpand(carteira.getId()).toUri();
        return ResponseEntity.created(location).build();
    }
}
