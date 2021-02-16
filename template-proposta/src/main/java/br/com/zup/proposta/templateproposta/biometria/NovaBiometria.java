package br.com.zup.proposta.templateproposta.biometria;

import br.com.zup.proposta.templateproposta.cartao.Cartao;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.util.Assert;

import javax.validation.constraints.NotBlank;
import java.util.Optional;

public class NovaBiometria {
    @NotBlank
    private  String biometria;

    public String getBiometria() {
        return biometria;
    }

    public Biometria toBiometria(Cartao cartao) {
        return new Biometria(biometria,cartao);
    }
}
