package br.com.zup.proposta.templateproposta.api.analiseSolicitacao;

import br.com.zup.proposta.templateproposta.endereco.Endereco;
import br.com.zup.proposta.templateproposta.proposta.Proposta;
import feign.FeignException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class SolicitacaoAnaliseTest {

    @Autowired
    private SolicitacaoAnalise solicitacaoAnalise;

    @Test
    void deveRetornarASolicitacaoSemRestricao() {
        var resposta = solicitacaoAnalise.getSolicitacao(new NovaAnaliseSolicitacaoRequest(getProposta("025.672.930-19")));
        assertEquals(AnaliseSolicitacao.SEM_RESTRICAO,resposta.getResultadoSolicitacao());
    }

    @Test
    void deveRetornarASolicitacaoComRestricao() {
        assertThrows(FeignException.UnprocessableEntity.class, () -> {
            var solicitacao = solicitacaoAnalise.getSolicitacao(new NovaAnaliseSolicitacaoRequest(getProposta("35.019.071/0001-10")));
        });
    }

    private Proposta getProposta(String documento){
        return new Proposta("nome",
                            documento,
                            "email@email.com",
                            new BigDecimal("2000"),
                            new Endereco("BR"
                                        ,"SC"
                                        ,"Cidade"
                                        ,"Bairro"
                                        ,"Rua"
                                        ,345
                                        ,"E"));
    }
}