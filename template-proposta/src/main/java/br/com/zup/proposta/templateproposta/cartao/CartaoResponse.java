package br.com.zup.proposta.templateproposta.cartao;

import com.fasterxml.jackson.annotation.JsonFormat;

public class CartaoResponse {
    @JsonFormat
    private String numero;
    @JsonFormat
    private SituacaoCartao status;

    public CartaoResponse(Cartao cartao) {
        if (cartao != null ){
            this.numero = cartao.getNumero();
            this.status = cartao.getSituacaoCartao();
        }
    }
}
