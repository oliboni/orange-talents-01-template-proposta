package br.com.zup.proposta.templateproposta.api.carteira;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(name = "enviaCarteira", url = "${feign.urlCartao}")
public interface EnviaCarteira {

    @RequestMapping(method = RequestMethod.POST, value = "/{id}/carteiras")
    EnviaCarteiraResponse enviaCarteira(@PathVariable("id") String id,
                                        @RequestBody EnviaCarteiraRequest request);

    class EnviaCarteiraResponse {
        private String resultado;
        private String id;

        public String getResultado() {
            return resultado;
        }

        public String getId() {
            return id;
        }

        @Override
        public String toString() {
            return "EnviaCarteiraResponse{" +
                    "resultado='" + resultado + '\'' +
                    ", id='" + id + '\'' +
                    '}';
        }
    }

    class EnviaCarteiraRequest{
        private String email;
        private String carteiraDigital;

        public EnviaCarteiraRequest(String email, String carteiraDigital) {
            this.email = email;
            this.carteiraDigital = carteiraDigital;
        }

        public String getEmail() {
            return email;
        }

        public String getCarteiraDigital() {
            return carteiraDigital;
        }
    }
}
