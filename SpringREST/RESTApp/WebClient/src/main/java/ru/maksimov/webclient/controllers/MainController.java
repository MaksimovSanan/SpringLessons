package ru.maksimov.webclient.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.client.RestTemplate;
import ru.maksimov.webclient.models.Item;

import java.util.Arrays;
import java.util.List;


@Controller
public class MainController {
    private final RestTemplate restTemplate;

    @Autowired
    public MainController(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @GetMapping
    public String getHomePage(Model model){
        List<Item> items = Arrays.stream(restTemplate.getForObject("http://ITEMSSERVICE/items", Item[].class)).toList();
        model.addAttribute("items", items);
        return "homePage";
    }
}
