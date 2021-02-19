package br.com.zup.proposta.templateproposta.cartao.carteira;

import br.com.zup.proposta.templateproposta.cartao.Cartao;
import org.springframework.util.Assert;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Objects;

@Entity
public class Carteira {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Email
    @Column(nullable = false)
    private String email;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private CarteiraDigital carteiraDigital;

    @NotNull
    @ManyToOne
    @JoinColumn(nullable = false)
    private Cartao cartao;

    public Carteira(@NotBlank @Email String email, @NotNull CarteiraDigital carteiraDigital,@NotNull @Valid Cartao cartao) {
        Assert.notNull(email,"O email não pode ser nulo");
        Assert.notNull(carteiraDigital,"A carteiraDigital não pode ser nula");
        Assert.notNull(cartao,"O cartao não pode ser nulo");
        this.email = email;
        this.carteiraDigital = carteiraDigital;
        this.cartao = cartao;
    }

    @Deprecated
    public Carteira() {
    }

    public Long getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public CarteiraDigital getCarteiraDigital() {
        return carteiraDigital;
    }

    public Cartao getCartao() {
        return cartao;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Carteira)) return false;
        Carteira carteira = (Carteira) o;
        return carteiraDigital == carteira.carteiraDigital && cartao.equals(carteira.cartao);
    }

    @Override
    public int hashCode() {
        return Objects.hash(carteiraDigital, cartao);
    }
}
