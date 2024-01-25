package ru.maksimov.webclient.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;
import ru.maksimov.webclient.models.Item;

@Controller
@RequestMapping("/item")
public class ItemsController {
    private final RestTemplate restTemplate;

    @Autowired
    public ItemsController(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @GetMapping("/{id}")
    public String getItemInfo(@PathVariable("id") int id, Model model) {
        Item item = restTemplate.getForObject("http://ITEMSSERVICE/items/" + id, Item.class);
        model.addAttribute("item", item);
        return "items/itemInfo";
    }
}
