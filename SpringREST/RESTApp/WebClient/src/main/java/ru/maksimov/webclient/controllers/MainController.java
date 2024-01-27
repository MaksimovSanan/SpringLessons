package ru.maksimov.webclient.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
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

    @PostMapping("/addItem")
    public String addItem(@ModelAttribute Item newItem) {

        System.out.println(newItem);

        //FIX IT WITH SECURITY
        newItem.setOwnerId(1);
        newItem.setOwnerName("TEST ABOBA");
        newItem.setId(null);
        System.out.println(newItem);

        String addItemUrl = "http://ITEMSSERVICE/items";


        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<Item> requestEntity = new HttpEntity<>(newItem, headers);

        ResponseEntity<Void> responseEntity = restTemplate.postForEntity(addItemUrl, requestEntity, Void.class);

        return "redirect:http://localhost:8080/"; // Перенаправляем пользователя на главную страницу
    }
}
