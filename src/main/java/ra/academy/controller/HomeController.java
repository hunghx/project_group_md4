package ra.academy.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import ra.academy.model.Student;

import java.util.Arrays;

@Controller
public class HomeController {
    @RequestMapping({"/",""})
    public String home(){
        return "index";
    }
}
