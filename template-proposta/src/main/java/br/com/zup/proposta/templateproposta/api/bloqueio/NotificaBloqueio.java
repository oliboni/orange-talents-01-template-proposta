package br.com.zup.proposta.templateproposta.api.bloqueio;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(name = "notificaBloqueio", url = "${feign.urlCartao}")
public interface NotificaBloqueio {

    @RequestMapping(method = RequestMethod.POST, value = "/{id}/bloqueios")
    NovaNotificaBloqueioResponse getBloqueio(@PathVariable("id") String id,
                                             @RequestBody NovaNotificaBloqueioRequest request);
}
