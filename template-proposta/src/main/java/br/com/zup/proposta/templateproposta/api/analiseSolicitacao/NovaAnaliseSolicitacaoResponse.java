package br.com.zup.proposta.templateproposta.api.analiseSolicitacao;

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
