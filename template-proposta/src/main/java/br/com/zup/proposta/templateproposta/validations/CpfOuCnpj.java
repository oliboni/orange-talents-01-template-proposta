package br.com.zup.proposta.templateproposta.validations;

import org.hibernate.validator.constraints.CompositionType;
import org.hibernate.validator.constraints.ConstraintComposition;
import org.hibernate.validator.constraints.br.CNPJ;
import org.hibernate.validator.constraints.br.CPF;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = { })
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@CPF
@CNPJ
@ConstraintComposition(CompositionType.OR)
public @interface CpfOuCnpj {
    String message() default "CPF/CNPJ inválido!";

    Class<?>[] groups() default { };
    Class<? extends Payload>[] payload() default {};

}
