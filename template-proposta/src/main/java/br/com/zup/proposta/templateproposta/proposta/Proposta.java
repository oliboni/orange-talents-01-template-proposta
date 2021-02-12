package br.com.zup.proposta.templateproposta.proposta;

import br.com.zup.proposta.templateproposta.cartao.Cartao;
import br.com.zup.proposta.templateproposta.endereco.Endereco;
import br.com.zup.proposta.templateproposta.validations.CpfOuCnpj;
import org.springframework.util.Assert;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.math.BigDecimal;

@Entity
public class Proposta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank
    @Column(nullable = false)
    private String nome;
    @NotBlank
    @CpfOuCnpj
    @Column(unique = true, nullable = false)
    private String documento;
    @NotBlank
    @Email
    @Column(nullable = false)
    private String email;
    @NotNull
    @Positive
    @Column(nullable = false)
    private BigDecimal salario;
    @Valid
    @NotNull
    @OneToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(nullable = false)
    private Endereco endereco;
    @Enumerated(EnumType.STRING)
    private EstadoProposta estadoProposta;
    @OneToOne(mappedBy = "proposta", cascade = {CascadeType.MERGE,CascadeType.PERSIST})
    private Cartao cartao;

    public Proposta(@NotBlank String nome, @NotBlank String documento, @NotBlank @Email String email, @NotNull @Positive BigDecimal salario, @Valid @NotNull Endereco endereco) {
        Assert.notNull(endereco,"O endereço não pode ser nulo!");
        this.nome = nome;
        this.documento = documento;
        this.email = email;
        this.salario = salario;
        this.endereco = endereco;
    }

    @Deprecated
    public Proposta() {
    }

    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getDocumento() {
        return documento;
    }

    public String getEmail() {
        return email;
    }

    public BigDecimal getSalario() {
        return salario;
    }

    public Endereco getEndereco() {
        return endereco;
    }

    public EstadoProposta getAnalise() {
        return estadoProposta;
    }

    public EstadoProposta getEstadoProposta() {
        return estadoProposta;
    }

    public Cartao getCartao() {
        return cartao;
    }

    public void atualizaStatus(EstadoProposta estadoProposta) {
        this.estadoProposta = estadoProposta;
    }

    public void atualizaCartao(Cartao cartao) {
        this.cartao = cartao;
    }
}
