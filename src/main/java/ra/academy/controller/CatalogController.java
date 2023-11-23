package ra.academy.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import ra.academy.model.Catalog;
import ra.academy.service.catalog.ICatalogService;

@Controller
@RequestMapping("/catalog")
public class CatalogController {
    @Autowired
    private ICatalogService catalogService;
    @RequestMapping("/add")
    public String catalogAdd(Model model){
        model.addAttribute("view","catalog_add");
        return "admin/index";
    }
    @RequestMapping(value = "/create" , method = RequestMethod.POST)
    public String doAdd(@ModelAttribute Catalog cat){
        catalogService.save(cat);
        return "redirect:/admin/catalog";
    }
    @RequestMapping("/edit/{id}")
    public String catalogEdit(@PathVariable Long id, Model model){
        model.addAttribute("cat",catalogService.findById(id));
        model.addAttribute("view","catalog_edit");
        return "admin/index";
    }
    @RequestMapping(value = "/update" , method = RequestMethod.POST)
    public String doUpdate(@ModelAttribute Catalog cat){
        catalogService.save(cat);
        return "redirect:/admin/catalog";
    }
    @RequestMapping("/delete/{id}")
    public String doDelete(@PathVariable Long id){
        catalogService.delete(id);
        return "redirect:/admin/catalog";
    }
}
