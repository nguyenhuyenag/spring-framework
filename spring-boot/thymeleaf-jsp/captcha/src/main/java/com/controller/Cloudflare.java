package com.controller;

import com.recaptcha.ReCaptchaConstants;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class Cloudflare {

    @GetMapping("/cloudflare")
    public String cloudflare(Model model) {
        model.addAttribute("SITE_KEY", "");
        return "cloudflare";
    }

}
