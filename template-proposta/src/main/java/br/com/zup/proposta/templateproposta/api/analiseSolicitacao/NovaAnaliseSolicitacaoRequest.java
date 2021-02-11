package br.com.zup.proposta.templateproposta.api.analiseSolicitacao;

import br.com.zup.proposta.templateproposta.proposta.Proposta;

public class NovaAnaliseSolicitacaoRequest {
    private String nome;
    private String documento;
    private Long idProposta;

    public NovaAnaliseSolicitacaoRequest(Proposta proposta){
        this.nome = proposta.getNome();
        this.documento = proposta.getDocumento();
        this.idProposta = proposta.getId();
    }

    public String getNome() {
        return nome;
    }

    public String getDocumento() {
        return documento;
    }

    public Long getIdProposta() {
        return idProposta;
    }
}
