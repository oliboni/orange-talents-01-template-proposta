package br.com.zup.proposta.templateproposta.cartao.carteira;

import br.com.zup.proposta.templateproposta.cartao.Cartao;
import br.com.zup.proposta.templateproposta.validations.EnumValid;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

public class NovaCarteiraRequest {
    @NotBlank
    @Email
    private String email;

    @NotBlank
    @EnumValid(enumClass = CarteiraDigital.class, message = "Carteira digital inválida!")
    private String carteiraDigital;

    public NovaCarteiraRequest(@NotBlank @Email String email, String carteiraDigital) {
        this.email = email;
        this.carteiraDigital = carteiraDigital;
    }

    @Deprecated
    public NovaCarteiraRequest(){

    }

    public String getEmail() {
        return email;
    }

    public String getCarteiraDigital() {
        return carteiraDigital;
    }

    public Carteira toCarteira(Cartao cartao){
        return new Carteira(email,toCarteiraDigital(),cartao);
    }

    public CarteiraDigital toCarteiraDigital() {
        return CarteiraDigital.valueOf(getCarteiraDigital());
    }

}
