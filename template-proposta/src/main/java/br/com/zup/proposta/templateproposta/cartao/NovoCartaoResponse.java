package br.com.zup.proposta.templateproposta.cartao;

import br.com.zup.proposta.templateproposta.proposta.Proposta;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class NovoCartaoResponse {
    private String id;
    private LocalDateTime emitidoEm;
    private String titular;
    private BigDecimal limite;

    public String getId() {
        return id;
    }

    public LocalDateTime getEmitidoEm() {
        return emitidoEm;
    }

    public String getTitular() {
        return titular;
    }

    public BigDecimal getLimite() {
        return limite;
    }

    @Override
    public String toString() {
        return "NovoCartaoResponse{" +
                "id='" + id + '\'' +
                ", emitidoEm=" + emitidoEm +
                ", titular='" + titular + '\'' +
                '}';
    }

    public Cartao toCartao(Proposta proposta) {
        return new Cartao(id,titular,emitidoEm,limite,proposta);
    }
}
