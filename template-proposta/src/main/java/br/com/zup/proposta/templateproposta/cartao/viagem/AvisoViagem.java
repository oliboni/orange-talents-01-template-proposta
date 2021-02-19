package br.com.zup.proposta.templateproposta.cartao.viagem;

import br.com.zup.proposta.templateproposta.cartao.Cartao;
import org.springframework.util.Assert;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
public class AvisoViagem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Column(nullable = false)
    private String destino;

    @NotNull
    @Column(nullable = false)
    private LocalDate terminoViagem;

    private LocalDateTime instanteAvisoViagem;

    @NotBlank
    @Column(nullable = false)
    private String ipCliente;

    @NotBlank
    @Column(nullable = false)
    private String userAgent;

    @NotNull
    @Valid
    @ManyToOne
    private Cartao cartao;

    public AvisoViagem(@NotBlank String destino, @NotNull LocalDate terminoViagem, @NotBlank String ipCliente, @NotBlank String userAgent, @NotNull @Valid Cartao cartao) {
        Assert.notNull(destino,"O destino não pode ser nulo");
        Assert.notNull(terminoViagem,"O terminoViagem não pode ser nulo");
        Assert.notNull(ipCliente,"O ipCliente não pode ser nulo");
        Assert.notNull(userAgent,"O userAgent não pode ser nulo");
        Assert.notNull(cartao,"O Cartao não pode ser nulo");
        this.destino = destino;
        this.terminoViagem = terminoViagem;
        this.ipCliente = ipCliente;
        this.userAgent = userAgent;
        this.cartao = cartao;
        this.instanteAvisoViagem = LocalDateTime.now();
    }

    @Deprecated
    public AvisoViagem() {
    }

    public Long getId() {
        return id;
    }

    public String getDestino() {
        return destino;
    }

    public LocalDate getTerminoViagem() {
        return terminoViagem;
    }

    public LocalDateTime getInstanteAvisoViagem() {
        return instanteAvisoViagem;
    }

    public String getIpCliente() {
        return ipCliente;
    }

    public String getUserAgent() {
        return userAgent;
    }

    public Cartao getCartao() {
        return cartao;
    }
}

