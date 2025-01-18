package com.controller;

import com.recaptcha.ReCaptchaUtils;
import com.util.Authentication;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
public class ReCaptchaController {

    @GetMapping("/recaptcha")
    public String recaptcha(Model model) {
        model.addAttribute("SITE_KEY", ReCaptchaUtils.SITE_KEY);
        return "recaptcha";
    }

    @PostMapping("/recaptcha")
    public String recaptcha(HttpServletRequest request, HttpServletResponse response, Model model) {
        model.addAttribute("SITE_KEY", ReCaptchaUtils.SITE_KEY);

        String errorString;
        String viewName = "recaptcha";

        // Verify CAPTCHA
        String token = request.getParameter(ReCaptchaUtils.G_RECAPTCHA_RESPONSE);
        if (StringUtils.isEmpty(token)) {
            errorString = "Captcha invalid!";
            request.setAttribute("errorString", errorString);
            return viewName;
        }
        boolean valid = ReCaptchaUtils.validateToken(token);
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
