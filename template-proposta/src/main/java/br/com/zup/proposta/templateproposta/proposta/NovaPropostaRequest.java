package br.com.zup.proposta.templateproposta.proposta;

import br.com.zup.proposta.templateproposta.proposta.endereco.NovoEnderecoRequest;
import br.com.zup.proposta.templateproposta.validations.CpfOuCnpj;

import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.math.BigDecimal;

public class NovaPropostaRequest {
    @NotBlank
    @CpfOuCnpj
    private String documento;
    @NotBlank
    @Email
    private String email;
    @NotBlank
    private String nome;
    @NotNull
    @Positive
    private BigDecimal salario;
    @Valid
    @NotNull
    private NovoEnderecoRequest novoEndereco;

    public NovaPropostaRequest(@NotBlank String documento, @NotBlank @Email String email, @NotBlank String nome, @NotNull @Positive BigDecimal salario, @Valid @NotNull NovoEnderecoRequest novoEndereco) {
        this.documento = documento;
        this.email = email;
        this.nome = nome;
        this.salario = salario;
        this.novoEndereco = novoEndereco;
    }

    public String getDocumento() {
        return documento;
    }

    public String getEmail() {
        return email;
    }

    public String getNome() {
        return nome;
    }

    public BigDecimal getSalario() {
        return salario;
    }

    public NovoEnderecoRequest getNovoEndereco() {
        return novoEndereco;
    }

    public Proposta toProposta() {
        return new Proposta(nome,documento,email,salario,novoEndereco.toEndereco());
    }

}
