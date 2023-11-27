package ru.maksimov.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("second-controller")
public class SecondController {
    @GetMapping("/exit")
    public String exit() {
        return "secondController/exit";
    }
}
