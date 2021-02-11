package br.com.zup.proposta.templateproposta.api.consulta;

import br.com.zup.proposta.templateproposta.proposta.NovaPropostaRequest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


@FeignClient(name = "${feign.name}", url = "${feign.url}")
public interface SolicitacaoAnalise {
    @RequestMapping(method = RequestMethod.POST)
    NovaAnaliseSolicitacaoResponse getSolicitacao(@RequestBody NovaAnaliseSolicitacaoRequest request);

}
