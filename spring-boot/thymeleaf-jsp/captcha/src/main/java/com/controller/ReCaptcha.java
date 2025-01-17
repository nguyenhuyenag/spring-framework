package com.controller;

import com.recaptcha.CaptchaResponse;
import com.recaptcha.Constants;
import com.recaptcha.VerifyUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
public class ReCaptcha {

    @GetMapping("/recaptcha")
    public String login(Model model) {
        model.addAttribute("SITE_KEY", Constants.SITE_KEY);
        return "recaptcha";
    }

    @PostMapping("/recaptcha")
    public String login(HttpServletRequest request, HttpServletResponse response, Model model) {
        model.addAttribute("SITE_KEY", Constants.SITE_KEY);
        String userName = request.getParameter("username");
        String password = request.getParameter("password");

        boolean valid = true;
        String errorString = null;

        // Check userName & password
        if (!"abc".equals(userName) || !"123".equals(password)) {
            valid = false;
            errorString = "UserName or Password invalid!";
        }

        if (valid) {
            String token = request.getParameter("g-recaptcha-response");
            System.out.println("Captcha response: " + token);
            // Verify CAPTCHA
            valid = VerifyUtils.validateCaptcha(token);
            // valid = VerifyUtils.validateCaptcha(token);
            if (!valid) {
                errorString = "Captcha invalid!";
            }
        }
        if (!valid) {
            request.setAttribute("errorString", errorString);
            return "recaptcha";
        }
        return "redirect:/userInfo";
    }

    @GetMapping("userInfo")
    public String userInfo() {
        return "userInfo";
    }

}
