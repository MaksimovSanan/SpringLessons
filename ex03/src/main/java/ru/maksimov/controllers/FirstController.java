package ru.maksimov.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/first-controller")
public class FirstController {

    @GetMapping("/hello")
    public String helloPage() {
        return "firstController/hello";
    }

    @GetMapping("/goodbye")
    public String goodbyePage() {
        return "firstController/goodbye";
    }
}
