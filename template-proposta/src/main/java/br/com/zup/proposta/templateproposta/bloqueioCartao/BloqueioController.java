package br.com.zup.proposta.templateproposta.bloqueioCartao;

import br.com.zup.proposta.templateproposta.api.bloqueio.NotificaBloqueio;
import br.com.zup.proposta.templateproposta.api.bloqueio.NovaNotificaBloqueioRequest;
import br.com.zup.proposta.templateproposta.cartao.Cartao;
import br.com.zup.proposta.templateproposta.cartao.CartaoRepository;
import br.com.zup.proposta.templateproposta.cartao.SituacaoCartao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

@RestController
@RequestMapping("/api/bloqueio")
public class BloqueioController {
    private static Logger logger = LoggerFactory.getLogger(BloqueioController.class);

    @Autowired
    private HttpServletRequest servletRequest;
    @Autowired
    private CartaoRepository cartaoRepository;
    @Autowired
    private BloqueioCartaoRepository bloqueioCartaoRepository;
    @Autowired
    private NotificaBloqueio notificaBloqueio;

    @PostMapping("/{idCartao}")
    public ResponseEntity<?> bloqueiaCartao(@PathVariable("idCartao") Long idCartao){
        Optional<Cartao> cartao = cartaoRepository.findById(idCartao);
        if (cartao.isEmpty()){
            logger.info("Solicitação de bloqueio falhou, cartão {} não encontrado.",idCartao);
            return ResponseEntity.notFound().build();
        }

        String userAgent = servletRequest.getHeader("User-Agent");
        String ipClient = servletRequest.getHeader("X-FORWARDED-FOR");

        if (ipClient == null || "".equals(ipClient)){
            ipClient = servletRequest.getRemoteAddr();
        }

        logger.info("Solicitação de bloqueio: Cartao {} - IpClient: {} - UserAgent: {}",idCartao,ipClient,userAgent);

        if (bloqueioCartaoRepository.findByCartaoId(idCartao).isPresent()){
            logger.info("Solicitação de bloqueio falhou, cartão {} já está bloqueado.",idCartao);
            return ResponseEntity.unprocessableEntity().body("O cartão já está bloqueado! ");
        }

        try {
            SituacaoCartao situacaoCartao = notificaBloqueio.getBloqueio(cartao.get().getNumero(),new NovaNotificaBloqueioRequest("zup_bootcamp")).getResultado();
            cartao.get().atualizaSituacao(situacaoCartao);
            logger.info("Notificação de cancelamento de cartão exeecutada!");
        } catch (Exception e){
            logger.info("Não foi possível notificar o cancelamento do cartao, erro: " + e);
        }

        BloqueioCartao bloqueioCartao = new BloqueioCartao(cartao.get(),ipClient,userAgent);
        bloqueioCartaoRepository.save(bloqueioCartao);

        logger.info("Cartão bloqueado com sucesso.");
        return ResponseEntity.ok().build();
    }
}
