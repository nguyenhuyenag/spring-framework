package com.controller;

import com.cloudflare.CloudflareConstants;
import com.util.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
public class Cloudflare {

    @GetMapping("/cloudflare")
    public String cloudflare(Model model) {
        model.addAttribute("SITE_KEY", CloudflareConstants.SITE_KEY);
        return "cloudflare";
    }

    @PostMapping("/cloudflare")
    public String cloudflare(HttpServletRequest request, HttpServletResponse response, Model model) {
        model.addAttribute("SITE_KEY", CloudflareConstants.SITE_KEY);

        boolean valid = true;
        String errorString = null;

        // Check userName & password
        if (Authentication.authentication(request)) {
            valid = false;
            errorString = "UserName or Password invalid!";
        }

        if (valid) {
            String token = request.getParameter("g-recaptcha-response");
            System.out.println("ReCaptcha response: " + token);
            // Verify CAPTCHA
            valid = false; // VerifyUtils.validateCaptcha(token);
            if (!valid) {
                errorString = "Captcha invalid!";
            }
        }
        if (!valid) {
            request.setAttribute("errorString", errorString);
            return "recaptcha";
        }
        // Neu captcha thi chuyen qua trang '/user'
        return "redirect:/user";
    }

}
