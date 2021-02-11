package br.com.zup.proposta.templateproposta.api.consulta;

import br.com.zup.proposta.templateproposta.proposta.NovaPropostaRequest;
import br.com.zup.proposta.templateproposta.proposta.Proposta;
import com.mysql.cj.log.Log;

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
