package br.com.zup.proposta.templateproposta.cartao.biometria;

import br.com.zup.proposta.templateproposta.cartao.Cartao;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity
public class Biometria {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Column(nullable = false)
    private String biometria;

    @ManyToOne
    private Cartao cartao;

    public Biometria(@NotBlank String biometria, Cartao cartao) {
        this.biometria = biometria;
        this.cartao = cartao;
    }

    public Long getId() {
        return id;
    }

    public String getBiometria() {
        return biometria;
    }

    public Cartao getCartao() {
        return cartao;
    }
}
