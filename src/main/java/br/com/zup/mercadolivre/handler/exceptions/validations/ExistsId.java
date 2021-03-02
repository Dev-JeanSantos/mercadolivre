package br.com.zup.mercadolivre.handler.exceptions.validations;
import javax.validation.Payload;

public @interface ExistsId {

    String message() default "{br.com.zup.casadocodigo.validations.existsid}";
    Class<?>[] groups() default { };
    Class<? extends Payload>[] payload() default { };
    String fieldName();
    Class<?> domainClass();
}