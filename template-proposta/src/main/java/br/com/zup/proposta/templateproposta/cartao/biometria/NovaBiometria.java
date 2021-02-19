package br.com.zup.proposta.templateproposta.cartao.biometria;

import br.com.zup.proposta.templateproposta.cartao.Cartao;

import javax.validation.constraints.NotBlank;

public class NovaBiometria {
    @NotBlank
    private  String biometria;

    public NovaBiometria(String biometria) {
        this.biometria = biometria;
    }

    public String getBiometria() {
        return biometria;
    }

    public Biometria toBiometria(Cartao cartao) {
        return new Biometria(biometria,cartao);
    }
}
