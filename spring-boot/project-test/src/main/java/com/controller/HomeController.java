package com.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class HomeController {

    @GetMapping({"/", "/home"})
    public String home() {
        return "home";
    }

    @PostMapping("/form-submit")
    public String handleFormSubmit(@RequestParam("email") String email, RedirectAttributes redirectAttributes) {
        System.out.println("Email nhận được: " + email);
        // Truyền dữ liệu sang redirect (hiển thị sau)
        redirectAttributes.addFlashAttribute("email", email);
        redirectAttributes.addFlashAttribute("message", "Email đã được gửi thành công!");
        // Redirect để tránh F5 gửi lại form
        return "redirect:/";
    }

    @GetMapping("/form-submit-get")
    @ResponseBody
    public String handleFetchGet(@RequestParam("email") String email) {
        System.out.println("GET param Email nhận được: " + email);
        return "Email GET param đã được nhận: " + email;
    }

}
