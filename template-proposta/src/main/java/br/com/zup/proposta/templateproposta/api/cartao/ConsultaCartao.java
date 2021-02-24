package br.com.zup.proposta.templateproposta.api.cartao;

import br.com.zup.proposta.templateproposta.cartao.NovoCartaoResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(name = "consultaCartao", url = "${feign.urlCartao}")
public interface ConsultaCartao {

    @RequestMapping(method = RequestMethod.GET, value = "?idProposta={id}")
    NovoCartaoResponse getSolicitacao(@PathVariable("id") Long id);
}
