package br.com.zup.proposta.templateproposta.api.avisoViagem;

import br.com.zup.proposta.templateproposta.cartao.viagem.NovoAvisoViagemRequest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(name = "notificacaoViagem", url = "${feign.urlCartao}")
public interface NotificacaoViagem {
    @RequestMapping(method = RequestMethod.POST, value = "/{id}/avisos")
    void notificarViagem(@PathVariable("id") String id,
                         @RequestBody NovoAvisoViagemRequest request);
}
