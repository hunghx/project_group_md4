package ra.academy.validate;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import ra.academy.dto.request.FormLogin;
@Component
public class FormLoginValidate implements Validator {
    @Override
    public boolean supports(Class<?> clazz) {
        return FormLogin.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        FormLogin formLogin = (FormLogin) target;
        if (formLogin.getUsername().isEmpty()){
            errors.rejectValue("username","form-login.email.empty");
        }else if(!formLogin.getUsername().matches("^(.+)@(\\S+)$")){
            errors.rejectValue("username","form-login.email.invalid");
        }
        if (formLogin.getPassword().isEmpty()){
            errors.rejectValue("password","form-login.password.empty");
        }

    }
}
