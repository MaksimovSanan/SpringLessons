package ru.maksimov.ApiService.services;

import ru.maksimov.ApiService.dto.Item;

import java.util.List;

public interface ItemsApiService {

    List<Item> getItems();
    Item getItemById(int id);
//    ResponseEntity<UserResponse> create(NewUserDTO newUserDTO);
}
