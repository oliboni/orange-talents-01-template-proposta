package br.com.zup.proposta.templateproposta.api.bloqueio;

import javax.validation.constraints.NotBlank;

public class NovaNotificaBloqueioRequest {
    @NotBlank
    private String sistemaResponsavel;

    public NovaNotificaBloqueioRequest(@NotBlank String sistemaResponsavel) {
        this.sistemaResponsavel = sistemaResponsavel;
    }

    public String getSistemaResponsavel() {
        return sistemaResponsavel;
    }
}
