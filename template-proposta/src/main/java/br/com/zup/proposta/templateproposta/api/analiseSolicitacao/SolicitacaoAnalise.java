package br.com.zup.proposta.templateproposta.api.analiseSolicitacao;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


@FeignClient(name = "${feign.solicitacaoAnalise}", url = "${feign.urlSolicitacaoAnalise}")
public interface SolicitacaoAnalise {
    @RequestMapping(method = RequestMethod.POST)
    NovaAnaliseSolicitacaoResponse getSolicitacao(@RequestBody NovaAnaliseSolicitacaoRequest request);

}
