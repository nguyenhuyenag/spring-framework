package com.controller;

import com.recaptcha.ReCaptchaConstants;
import com.recaptcha.VerifyUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
public class HCaptcha {

    @GetMapping("/hcaptcha")
    public String recaptcha(Model model) {
        model.addAttribute("SITE_KEY", "");
        return "hcaptcha";
    }

}
