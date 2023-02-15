package com.bettersounds.validator;

import com.bettersounds.dto.SignupRequest;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 *
 * @author TEGA
 */
public class PasswordMatchesValidator implements ConstraintValidator<PasswordMatches, SignupRequest>{

    /**
     *
     * @param user
     * @param cvc
     * @return
     */
    @Override
    public boolean isValid(SignupRequest user, ConstraintValidatorContext cvc) {
//        return user.getPassword().equals(user.getMatchingPassword());
            return true;
    }
    
}
