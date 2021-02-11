package br.com.zup.proposta.templateproposta.api.cartao;

import java.time.LocalDateTime;

public class NovoCartaoResponse {
    private String id;
    private LocalDateTime emitidoEm;
    private String titular;

    public String getId() {
        return id;
    }

    public LocalDateTime getEmitidoEm() {
        return emitidoEm;
    }

    public String getTitular() {
        return titular;
    }

    @Override
    public String toString() {
        return "NovoCartaoResponse{" +
                "id='" + id + '\'' +
                ", emitidoEm=" + emitidoEm +
                ", titular='" + titular + '\'' +
                '}';
    }
}
