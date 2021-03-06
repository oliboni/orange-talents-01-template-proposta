package br.com.zup.proposta.templateproposta.api.cartao;

import br.com.zup.proposta.templateproposta.cartao.NovoCartaoResponse;
import br.com.zup.proposta.templateproposta.proposta.PropostaRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class ConsultaCartaoSheduled {
    private static final Logger logger = LoggerFactory.getLogger(ConsultaCartaoSheduled.class);

    @Autowired
    private PropostaRepository propostaRepository;

    @Autowired
    private ConsultaCartao consultaCartao;

    @Scheduled(fixedRateString = "${proposta.consultaCartao}")
    public void consultaCartaoPropostasElegiveis(){
        var propostas = propostaRepository.findTop100AllElegivelCartaoisNull();
        propostas.forEach(proposta -> {
            try {
                NovoCartaoResponse cartaoResponse = consultaCartao.getSolicitacao(proposta.getId());
                logger.info("Consulta da proposta " + proposta.getId() + " retorno: " + cartaoResponse);
                proposta.atualizaCartao(cartaoResponse.toCartao(proposta));
                propostaRepository.save(proposta);
            } catch (Exception e){
                logger.info("Consulta da proposta " + proposta.getId() + " falhou, erro: " + e);
            }
        });
    }
}
