package br.com.zup.proposta.templateproposta.exceptions;

import java.util.Collection;

public class ValidationErrorsDto {
    private Collection<String> mensagens;

    public ValidationErrorsDto(Collection<String> mensagens) {
        this.mensagens = mensagens;
    }

    public Collection<String> getMensagens() {
        return mensagens;
    }
}
