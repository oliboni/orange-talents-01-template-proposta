package br.com.zup.proposta.templateproposta.proposta.endereco;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
public class Endereco {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank
    @Column(nullable = false)
    private String pais;
    @NotBlank
    @Column(nullable = false)
    private String estado;
    @NotBlank
    @Column(nullable = false)
    private String cidade;
    @NotBlank
    @Column(nullable = false)
    private String bairro;
    @NotBlank
    @Column(nullable = false)
    private String rua;
    @NotNull
    @Column(nullable = false)
    private Integer numero;
    private String complemento;

    public Endereco(String pais, String estado, String cidade, String bairro, String rua, Integer numero, String complemento) {
        this.pais = pais;
        this.estado = estado;
        this.cidade = cidade;
        this.bairro = bairro;
        this.rua = rua;
        this.numero = numero;
        this.complemento = complemento;
    }

    @Deprecated
    public Endereco(){

    }

    public Long getId() {
        return id;
    }

    public String getPais() {
        return pais;
    }

    public String getEstado() {
        return estado;
    }

    public String getCidade() {
        return cidade;
    }

    public String getBairro() {
        return bairro;
    }

    public String getRua() {
        return rua;
    }

    public Integer getNumero() {
        return numero;
    }

    public String getComplemento() {
        return complemento;
    }
}
