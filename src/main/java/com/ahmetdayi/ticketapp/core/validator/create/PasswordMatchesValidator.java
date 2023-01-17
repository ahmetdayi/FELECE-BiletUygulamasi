package com.ahmetdayi.ticketapp.core.validator.create;


import com.ahmetdayi.ticketapp.entity.request.CreateClientRequest;


import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class PasswordMatchesValidator implements ConstraintValidator<PasswordMatches, Object> {

    @Override
    public void initialize(PasswordMatches constraintAnnotation) {
    }
    @Override
    public boolean isValid(Object obj, ConstraintValidatorContext context){
        CreateClientRequest customer = ( CreateClientRequest) obj;
        return customer.getPassword().equals(customer.getMatchingPassword());
    }


}