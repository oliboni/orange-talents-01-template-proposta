package br.com.zup.proposta.templateproposta.cartao.bloqueioCartao;

import br.com.zup.proposta.templateproposta.cartao.Cartao;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
public class BloqueioCartao {
    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false)
    private LocalDateTime instanteBloqueio;

    @Column(nullable = false)
    private String ipClient;

    @Column(nullable = false)
    private String userAgent;

    @OneToOne(cascade = {CascadeType.MERGE})
    private Cartao cartao;

    public BloqueioCartao(Cartao cartao, String ipClient, String userAgent) {
        this.cartao = cartao;
        this.ipClient = ipClient;
        this.userAgent = userAgent;
        this.instanteBloqueio = LocalDateTime.now();
    }

    @Deprecated
    public BloqueioCartao() {
    }

    public Long getId() {
        return id;
    }

    public LocalDateTime getInstanteBloqueio() {
        return instanteBloqueio;
    }

    public String getIpClient() {
        return ipClient;
    }

    public String getUserAgent() {
        return userAgent;
    }

    public Cartao getCartao() {
        return cartao;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof BloqueioCartao)) return false;
        BloqueioCartao that = (BloqueioCartao) o;
        return getCartao().equals(that.getCartao());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getCartao());
    }
}
