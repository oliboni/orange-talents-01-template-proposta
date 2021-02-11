package br.com.zup.proposta.templateproposta.api.consulta;

import br.com.zup.proposta.templateproposta.api.consulta.AnaliseSolicitacao;

public class NovaAnaliseSolicitacaoResponse {

    private String nome;
    private String documento;
    private AnaliseSolicitacao resultadoSolicitacao;

    public String getNome() {
        return nome;
    }

    public String getDocumento() {
        return documento;
    }

    public AnaliseSolicitacao getResultadoSolicitacao() {
        return resultadoSolicitacao;
    }

}
