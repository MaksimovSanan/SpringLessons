package ru.maksimov.uiService.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.maksimov.uiService.util.HtmlFormManager;

@Controller
@RequestMapping("/home")
public class DashboardController {
    @GetMapping
    public String homePage(){
        return HtmlFormManager.homePage;
    }
}
