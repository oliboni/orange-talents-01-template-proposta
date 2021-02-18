package br.com.zup.proposta.templateproposta.proposta;

import br.com.zup.proposta.templateproposta.cartao.CartaoResponse;
import br.com.zup.proposta.templateproposta.cartao.SituacaoCartao;
import br.com.zup.proposta.templateproposta.endereco.EnderecoResponse;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.math.BigDecimal;

public class PropostaResponse {
    @JsonFormat
    private String nome;
    @JsonFormat
    private String documento;
    @JsonFormat
    private String email;
    @JsonFormat
    private BigDecimal salario;
    @JsonFormat
    private EnderecoResponse endereco;
    @JsonFormat
    private EstadoProposta estadoProposta;
    @JsonFormat
    private CartaoResponse cartao;

    public PropostaResponse(Proposta proposta) {
        this.nome = proposta.getNome();
        this.documento = proposta.getDocumento();
        this.email = proposta.getEmail();
        this.salario = proposta.getSalario();
        this.endereco = new EnderecoResponse(proposta.getEndereco());
        this.estadoProposta = proposta.getEstadoProposta();
        this.cartao = new CartaoResponse(proposta.getCartao());
    }

}
