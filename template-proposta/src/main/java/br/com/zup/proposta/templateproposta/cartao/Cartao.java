package br.com.zup.proposta.templateproposta.cartao;

import br.com.zup.proposta.templateproposta.biometria.Biometria;
import br.com.zup.proposta.templateproposta.proposta.Proposta;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Entity
public class Cartao {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String numero;
    private String titular;
    private LocalDateTime emitidoEm;
    private BigDecimal limite;
    @OneToOne
    private Proposta proposta;
    @OneToMany(mappedBy = "cartao")
    private List<Biometria> biometria;

    public Cartao(String numero, String titular, LocalDateTime emitidoEm, BigDecimal limite, Proposta proposta) {
        this.numero = numero;
        this.titular = titular;
        this.emitidoEm = emitidoEm;
        this.limite = limite;
        this.proposta = proposta;
    }

    @Deprecated
    public Cartao() {
    }

    public Long getId() {
        return id;
    }

    public String getNumero() {
        return numero;
    }

    public String getTitular() {
        return titular;
    }

    public LocalDateTime getEmitidoEm() {
        return emitidoEm;
    }
}
