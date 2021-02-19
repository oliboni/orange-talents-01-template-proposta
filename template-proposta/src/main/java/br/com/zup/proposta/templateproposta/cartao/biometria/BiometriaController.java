package br.com.zup.proposta.templateproposta.cartao.biometria;

import br.com.zup.proposta.templateproposta.cartao.Cartao;
import br.com.zup.proposta.templateproposta.cartao.CartaoRepository;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.Optional;

@RestController
@RequestMapping("/api/biometrias")
public class BiometriaController {

    @Autowired
    private CartaoRepository cartaoRepository;
    @Autowired
    private BiometriaRepository biometriaRepository;

    @PostMapping("/{idCartao}")
    public ResponseEntity<?> criaBiometria(@PathVariable("idCartao") Long idCartao,
                                           @RequestBody @Valid NovaBiometria request,
                                           UriComponentsBuilder uriBuilder) {
        Optional<Cartao> cartao = cartaoRepository.findById(idCartao);
        if (cartao.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        if (!Base64.isBase64(request.getBiometria().getBytes())){
            return ResponseEntity.badRequest().body("Biometria não é uma string em formato base64 válida!");
        }

        Biometria biometria = request.toBiometria(cartao.get());
        biometriaRepository.save(biometria);

        URI location = uriBuilder.path("/{idCartao}/{idBiometria}").buildAndExpand(idCartao,biometria.getId()).toUri();
        return ResponseEntity.created(location).build();
    }
}
