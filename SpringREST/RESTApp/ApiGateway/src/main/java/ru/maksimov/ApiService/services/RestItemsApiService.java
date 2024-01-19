package ru.maksimov.ApiService.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.maksimov.ApiService.config.ItemsAPIClient;
import ru.maksimov.ApiService.dto.Item;

import java.util.List;

@Service
public class RestItemsApiService implements ItemsApiService{

    private final ItemsAPIClient itemsAPIClient;

    @Autowired
    public RestItemsApiService(ItemsAPIClient itemsAPIClient) {
        this.itemsAPIClient = itemsAPIClient;
    }

    @Override
    public List<Item> getItems() {
        return itemsAPIClient.getItems();
    }

    @Override
    public Item getItemById(int id) {
        return itemsAPIClient.getItemById(id);
    }
}
