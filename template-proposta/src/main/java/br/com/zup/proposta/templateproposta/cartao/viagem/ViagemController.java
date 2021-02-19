package br.com.zup.proposta.templateproposta.cartao.viagem;

import br.com.zup.proposta.templateproposta.api.avisoViagem.NotificacaoViagem;
import br.com.zup.proposta.templateproposta.cartao.Cartao;
import br.com.zup.proposta.templateproposta.cartao.CartaoRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.Optional;

@RestController
@RequestMapping("/api/cartao")
public class ViagemController {
    private static Logger logger = LoggerFactory.getLogger(ViagemController.class);

    @Autowired
    private HttpServletRequest servletRequest;
    @Autowired
    private CartaoRepository cartaoRepository;
    @Autowired
    private AvisoViagemRepository avisoViagemRepository;
    @Autowired
    private NotificacaoViagem notificacaoViagem;

    @PostMapping("/{id}/aviso-viagem")
    public ResponseEntity<?> criarAvisoViagem(@PathVariable("id") Long idCartao,
                                              @RequestBody @Valid NovoAvisoViagemRequest request){
        Optional<Cartao> cartao = cartaoRepository.findById(idCartao);
        if (cartao.isEmpty()){
            logger.info("Cartão {} não encontrado", idCartao);
            return ResponseEntity.notFound().build();
        }

        String userAgent = servletRequest.getHeader("User-Agent");
        String ipClient = servletRequest.getHeader("X-FORWARDED-FOR");

        if (ipClient == null || "".equals(ipClient)){
            ipClient = servletRequest.getRemoteAddr();
        }

        try {
            notificacaoViagem.notificarViagem(cartao.get().getNumero(), request);
        }catch (Exception e){
            logger.info("Erro ao notificar aviso de viagem: " + e);
            return ResponseEntity.status(500).build();
        }

        AvisoViagem avisoViagem = request.toAvisoViagem(cartao.get(),userAgent,ipClient);
        avisoViagemRepository.save(avisoViagem);

        logger.info("Aviso de viagem cadastrado com sucesso! ip: "+ipClient+" - Agent: "+userAgent);

        return ResponseEntity.ok().build();
    }
}
