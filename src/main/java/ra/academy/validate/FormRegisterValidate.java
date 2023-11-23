package ra.academy.validate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import ra.academy.dto.request.FormRegister;
import ra.academy.service.account.IAccountService;

@Component
public class FormRegisterValidate implements Validator {
    @Autowired
    private IAccountService accountService;
    @Override
    public boolean supports(Class<?> clazz) {
        return FormRegister.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        FormRegister form =(FormRegister) target;
        if (accountService.checkExistByEmail(form.getEmail())){
            errors.rejectValue("email","register.email.exist");
        }
        if (accountService.checkExistByPhone(form.getPhone())){
            errors.rejectValue("phone","register.phone.exist");
        }

        if(!form.getRePassword().equals(form.getPassword())){
            errors.rejectValue("rePassword","register.repassword.error");
        }

    }
}
