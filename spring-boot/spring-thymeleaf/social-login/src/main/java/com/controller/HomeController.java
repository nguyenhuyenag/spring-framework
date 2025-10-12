package com.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("/home")
    public String home(Model model) {
        // Truyền dữ liệu xuống view (nếu cần)
        model.addAttribute("username", "Huyền Nguyễn");
        // Chỉ cần return tên view "home"
        // Thymeleaf sẽ tự động render layout/main.html + nội dung home.html
        return "home";
    }

}
