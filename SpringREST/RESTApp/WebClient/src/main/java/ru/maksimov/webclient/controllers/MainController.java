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
        // Здесь можно добавить логику для сохранения нового Item в базе данных
        // Например, вызов сервиса для сохранения Item

        System.out.println(newItem);

        //FIX IT WITH SECURITY
        newItem.setOwnerId(1);
        newItem.setOwnerName("TEST ABOBA");
        newItem.setId(null);
        System.out.println(newItem);

        // Укажите URL микросервиса, куда будет отправлен запрос
        String addItemUrl = "http://ITEMSSERVICE/items";


        // Создайте HttpHeaders для установки заголовков запроса
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        // Создайте HttpEntity, чтобы установить тело запроса и заголовки
        HttpEntity<Item> requestEntity = new HttpEntity<>(newItem, headers);

        ResponseEntity<Void> responseEntity = restTemplate.postForEntity(addItemUrl, requestEntity, Void.class);

        // После успешного сохранения Item можно перенаправить пользователя на домашнюю страницу или на страницу с подтверждением добавления
        return "redirect:/"; // Перенаправляем пользователя на главную страницу
    }
}
