package com.controller;

import com.cloudflare.CloudflareUtils;
import com.util.Authentication;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
public class CloudflareController {

    @GetMapping("/cloudflare")
    public String cloudflare(Model model) {
        model.addAttribute("SITE_KEY", CloudflareUtils.SITE_KEY);
        return "cloudflare";
    }

    @PostMapping("/cloudflare")
    public String cloudflare(HttpServletRequest request, HttpServletResponse response, Model model) {
        model.addAttribute("SITE_KEY", CloudflareUtils.SITE_KEY);

        String errorString;
        String viewName = "cloudflare";

        // Verify CAPTCHA
        String token = request.getParameter("cf-turnstile-response");
        if (StringUtils.isEmpty(token)) {
            errorString = "Captcha invalid!";
            request.setAttribute("errorString", errorString);
            return viewName;
        }

        boolean valid = CloudflareUtils.validateToken(token);
        if (!valid) {
            errorString = "Captcha invalid!";
            request.setAttribute("errorString", errorString);
            return viewName;
        }

        // Check userName & password
        if (Authentication.authentication(request)) {
            errorString = "Username or Password invalid!";
            request.setAttribute("errorString", errorString);
            return viewName;
        }

        // Neu captcha thi chuyen qua trang '/user'
        return "redirect:/user";
    }

}
