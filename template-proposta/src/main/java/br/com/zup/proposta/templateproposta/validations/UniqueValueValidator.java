package br.com.zup.proposta.templateproposta.validations;

import br.com.zup.proposta.templateproposta.exceptions.ApiErrorException;
import org.springframework.http.HttpStatus;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.List;

public class UniqueValueValidator implements ConstraintValidator<UniqueValue, Object> {

    private Class<?> domainClass;
    private String fieldName;
    private String message;

    @PersistenceContext
    private EntityManager manager;

    @Override
    public void initialize(UniqueValue param) {
        domainClass = param.domainClass();
        fieldName = param.fieldName();
        message = param.message();
    }

    @Override
    public boolean isValid(Object value, ConstraintValidatorContext context) {
        Query query = manager.createQuery("SELECT 1 FROM " + domainClass.getName() + " WHERE " + fieldName + " = :value" )
                                .setParameter("value", value);

        List<?> list = query.getResultList();
        return list.isEmpty();
    }
}
