package ra.academy.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import ra.academy.model.Catalog;
import ra.academy.service.MailService;
import ra.academy.service.catalog.ICatalogService;

import java.awt.*;

@RestController
@RequestMapping("/api/v1")
public class ApiController {
    @Autowired
    private MailService mailService;
    @Autowired
    private ICatalogService catalogService;

    @GetMapping(value = "/catalog/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public Catalog findById(@PathVariable Long id) {
//        mailService.sendMail("hunghx@rikkeisoft.com", "devst2025@gmail.com", "test mail", "có cái nịt");
        return catalogService.findById(id);
    }
}
