package com.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class AlpineController {

    @GetMapping("/alpine")
    public String page() {
        return "alpine"; // Giao diện alpine.html
    }

    @GetMapping("/alpine/send")
    @ResponseBody
    public String send(@RequestParam String text) {
        // Trả lại text + "-from-server"
        return text + "-from-server";
    }

}
