package com.controller;

import com.recaptcha.v3.ReCaptchaV3Response;
import com.recaptcha.v3.ReCaptchaV3Utils;
import com.util.Authentication;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;

@Slf4j
@Controller
@RequestMapping("/recaptcha")
public class ReCaptchaV3Controller {

    @GetMapping("/v3")
    public String recaptcha(Model model) {
        model.addAttribute("SITE_KEY", ReCaptchaV3Utils.SITE_KEY);
        return "recaptcha-v3";
    }

    @PostMapping("/v3") // Chạy ngầm, không hiển thị gì cả
    public String recaptcha(HttpServletRequest request,
                            RedirectAttributes redirectAttributes,
                            Model model) {

        log.info("POST /recaptcha/v3");

        model.addAttribute("SITE_KEY", ReCaptchaV3Utils.SITE_KEY);

        String viewName = "recaptcha-v3";
        String errorString;

        // Verify reCAPTCHA v3
        String token = request.getParameter(ReCaptchaV3Utils.G_RECAPTCHA_RESPONSE);

        if (StringUtils.isEmpty(token)) {
            log.warn("reCAPTCHA token is empty");
            errorString = "Captcha invalid!";
            model.addAttribute("errorString", errorString);
            return viewName;
        }

        log.debug("Token length: {}", token.length());

        ReCaptchaV3Response response = ReCaptchaV3Utils.validateToken(token);

        if (response == null) {
            log.error("reCAPTCHA response is null");
            errorString = "Captcha verification failed!";
            model.addAttribute("errorString", errorString);
            return viewName;
        }

        log.info("success={}", response.getSuccess());
        log.info("score={}", response.getScore());
        log.info("action={}", response.getAction());
        log.info("hostname={}", response.getHostname());
        log.info("challengeTs={}", response.getChallengeTs());

        if (response.getErrorCodes() != null && !response.getErrorCodes().isEmpty()) {
            log.warn("errorCodes={}", response.getErrorCodes());
        }

        if (!Boolean.TRUE.equals(response.getSuccess())) {
            log.warn("reCAPTCHA verification failed");
            errorString = "Captcha verification failed!";
            model.addAttribute("errorString", errorString);
            return viewName;
        }

        // Kiểm tra action
        if (!"login".equals(response.getAction())) {
            log.warn("Invalid action: {}", response.getAction());
            errorString = "Invalid action!";
            model.addAttribute("errorString", errorString);
            return viewName;
        }

        // Kiểm tra score
        if (response.getScore() == null || response.getScore() < 0.5f) {
            log.warn("Low score: {}", response.getScore());
            errorString = "Low score: " + response.getScore();
            model.addAttribute("errorString", errorString);
            return viewName;
        }

        log.info("reCAPTCHA passed with score={}", response.getScore());

        // Check userName & password
        String username = request.getParameter("username");

        if (Authentication.authentication(request)) {
            log.warn("Authentication failed for user={}", username);
            errorString = "Username or Password invalid!";
            model.addAttribute("errorString", errorString);
            return viewName;
        }

        log.info("Authentication successful for user={}", username);

        redirectAttributes.addFlashAttribute("message", "ReCaptcha v3");

        log.info("Redirect to /user");

        return "redirect:/user";
    }

}
