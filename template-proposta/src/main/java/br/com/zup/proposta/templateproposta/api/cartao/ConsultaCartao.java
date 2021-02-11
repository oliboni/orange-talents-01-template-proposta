package br.com.zup.proposta.templateproposta.api.cartao;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.scheduling.annotation.Async;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.concurrent.CompletableFuture;

@FeignClient(name = "${feign.consultaCartao}", url = "${feign.urlConsultaCartao}")
public interface ConsultaCartao {

    @RequestMapping(method = RequestMethod.GET, value = "?idProposta={id}")
    NovoCartaoResponse getSolicitacao(@PathVariable("id") Long id);
}
