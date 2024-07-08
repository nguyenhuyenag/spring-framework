package com.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ViewController {

    @GetMapping({"/", "home"})
    public String home() {
        return "home";
    }

    @GetMapping("404")
    public String notfound() {
        return "404";
    }

    @GetMapping("405")
    public String forbidden() {
        return "405";
    }

}
