package br.com.zup.proposta.templateproposta;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableFeignClients
@EnableScheduling
public class TemplatePropostaApplication {

	public static void main(String[] args) {
		SpringApplication.run(TemplatePropostaApplication.class, args);
	}

}
