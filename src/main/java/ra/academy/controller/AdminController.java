package ra.academy.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ra.academy.service.catalog.ICatalogService;

@Controller
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    private ICatalogService catalogService;
    @RequestMapping
    public String index(Model model){
        model.addAttribute("view","dashboard");
        return "admin/index";
    }
    @RequestMapping("/catalog")
    public String catalog(Model model, @RequestParam(name="page",defaultValue = "0") int page,@RequestParam(name = "size",defaultValue = "3") int size){
        model.addAttribute("catalogs",catalogService.findAll(page,size));
        model.addAttribute("current_page",page);
        model.addAttribute("size",size);
        model.addAttribute("total_page",new int[catalogService.getTotalPage(size)]);
        model.addAttribute("view","catalog");
        return "admin/index";
    }
    @RequestMapping("/product")
    public String product(Model model){
        model.addAttribute("view","product");
        return "admin/index";
    }

    @RequestMapping("/user")
    public String user(Model model){
        model.addAttribute("view","user");
        return "admin/index";
    }
}
