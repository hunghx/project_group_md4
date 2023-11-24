package ra.academy.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import ra.academy.service.MailService;

import java.util.Arrays;

@Controller
public class HomeController {
    @Autowired
    private MailService mailService;
    @RequestMapping({"/",""})
    public String home(){
        mailService.sendMail("hunghx@rikkeisoft.com","devst2025@gmail.com","test mail","có cái nịt");
        return "index";

    }
}
