package ra.academy.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import ra.academy.dto.request.FormLogin;
import ra.academy.dto.request.FormRegister;
import ra.academy.model.Account;
import ra.academy.service.account.IAccountService;
import ra.academy.validate.FormLoginValidate;
import ra.academy.validate.FormRegisterValidate;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@Controller
public class UserController {
    @Autowired
    private IAccountService accountService;
    @Autowired
    private FormLoginValidate loginValidate;
    @Autowired
    private FormRegisterValidate registerValidate;
    @RequestMapping("/form-signin")
    public String login(Model model){
        model.addAttribute("form_login",new FormLogin());
        return "auth/signin-form";
    }
    @RequestMapping(value = "/handle-signin", method = RequestMethod.POST)
    public String doLogin(HttpSession session, @ModelAttribute("form_login") FormLogin formLogin, Model model, BindingResult bindingResult){
        // xử lí
        // kiểm tra tính hợp lệ của dữu liệu
        loginValidate.validate(formLogin,bindingResult);
        if(bindingResult.hasErrors()){
            return "auth/signin-form";
        }
        Account userLogin = accountService.login(formLogin);
        if(userLogin == null){
            model.addAttribute("login_fail","Username or password is inccorect");
            return "auth/signin-form";
        }
        session.setAttribute("user_login",userLogin);
        return "index";
    }
    @RequestMapping("/form-signup")
    public String register(Model model){
        model.addAttribute("signup_form",new FormRegister());
        return "auth/signup-form";
    }
    @RequestMapping(value = "/handle-signup",method = RequestMethod.POST)
    public String doRegister(@ModelAttribute("signup_form") @Valid FormRegister formRegister, BindingResult bindingResult){
        registerValidate.validate(formRegister,bindingResult);
        if (bindingResult.hasErrors()){
            return "auth/signup-form";
        }
        accountService.register(formRegister);
        return "redirect:/form-signin";
    }

}
