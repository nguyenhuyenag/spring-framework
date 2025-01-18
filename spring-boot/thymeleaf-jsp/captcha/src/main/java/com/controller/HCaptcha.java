package com.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HCaptcha {

    @GetMapping("/hcaptcha")
    public String recaptcha(Model model) {
        model.addAttribute("SITE_KEY", "");
        return "hcaptcha";
    }

}
