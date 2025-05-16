package com.cvgenerator.cvg.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/templates")
public class TemplateController {

    @GetMapping("/show")
    public String showTemplates() {
        return "viewAll/show-template";  // Thymeleaf will render show-template.html
    }

    @GetMapping("/one")
    public String showTemplatesOne() {
        return "viewAll/template_one";  // Thymeleaf will render show-template.html
    }

    @GetMapping("/two")
    public String showTemplatesTwo() {
        return "viewAll/template_two";  // Thymeleaf will render show-template.html
    }

    @GetMapping("/three")
    public String showTemplatesThree() {
        return "viewAll/template_three";  // Thymeleaf will render show-template.html
    }
}

