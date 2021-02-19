package br.com.zup.proposta.templateproposta.proposta.endereco;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class NovoEnderecoRequest {
    @NotBlank
    private String pais;
    @NotBlank
    private String estado;
    @NotBlank
    private String cidade;
    @NotBlank
    private String bairro;
    @NotBlank
    private String rua;
    @NotNull
    private Integer numero;
    private String complemento;

    public NovoEnderecoRequest(@NotBlank String pais, @NotBlank String estado, @NotBlank String cidade, @NotBlank String bairro, @NotBlank String rua, @NotNull Integer numero, String complemento) {
        this.pais = pais;
        this.estado = estado;
        this.cidade = cidade;
        this.bairro = bairro;
        this.rua = rua;
        this.numero = numero;
        this.complemento = complemento;
    }

    public Endereco toEndereco(){
        return new Endereco(pais,estado,cidade,bairro,rua,numero,complemento);
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
