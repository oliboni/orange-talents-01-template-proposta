package br.com.zup.proposta.templateproposta.cartao.viagem;

import br.com.zup.proposta.templateproposta.cartao.Cartao;

import javax.validation.constraints.Future;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

public class NovoAvisoViagemRequest {
    @NotBlank
    private String destino;
    @NotNull
    @Future
    private LocalDate terminoViagem;

    public NovoAvisoViagemRequest(@NotBlank String destino, @NotNull LocalDate terminoViagem) {
        this.destino = destino;
        this.terminoViagem = terminoViagem;
    }

    public String getDestino() {
        return destino;
    }

    public LocalDate getTerminoViagem() {
        return terminoViagem;
    }

    public AvisoViagem toAvisoViagem(Cartao cartao, String userAgent, String ipClient) {
        return new AvisoViagem(destino,terminoViagem,ipClient,userAgent,cartao);
    }
}
