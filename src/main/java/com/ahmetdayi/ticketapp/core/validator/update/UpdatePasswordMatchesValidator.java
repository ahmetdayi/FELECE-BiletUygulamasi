package com.ahmetdayi.ticketapp.core.validator.update;

import com.ahmetdayi.ticketapp.entity.request.UpdateClientRequest;


import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class UpdatePasswordMatchesValidator implements ConstraintValidator<UpdatePasswordMatches, Object> {

    @Override
    public void initialize(UpdatePasswordMatches constraintAnnotation) {
    }
    @Override
    public boolean isValid(Object obj, ConstraintValidatorContext context){
        UpdateClientRequest customer = ( UpdateClientRequest) obj;
        return customer.getPassword().equals(customer.getMatchingPassword());
    }


}
