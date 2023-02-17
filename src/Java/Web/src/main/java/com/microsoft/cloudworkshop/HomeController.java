package com.microsoft.cloudworkshop;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("/")
    public String index() {
        return "home/index";
    }

    @GetMapping("/Privacy")
    public String privacy() {
        return "home/Privacy";
    }
}
