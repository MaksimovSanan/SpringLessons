package ru.maksimov.ApiService.controllers;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.maksimov.ApiService.config.ItemsAPIClient;
import ru.maksimov.ApiService.dto.Item;
import ru.maksimov.ApiService.dto.NewUserDTO;
import ru.maksimov.ApiService.dto.User;
import ru.maksimov.ApiService.services.ItemsApiService;
import ru.maksimov.ApiService.services.UsersApiService;
import ru.maksimov.ApiService.util.UserResponse;

import java.util.List;

@RestController
@RequestMapping("/api")
@AllArgsConstructor
public class ApiController {

    private final UsersApiService usersApiService;
    private final ItemsApiService itemsApiService;

    @GetMapping("/users")
    public List<User> getUsers(){
        return usersApiService.getUsers();
    }

    @GetMapping("/users/{id}")
    public User getUserById(@PathVariable("id") int id){
        return usersApiService.getUserById(id);
    }

    @PostMapping("/users")
    public ResponseEntity<UserResponse> create(@RequestBody @Valid NewUserDTO newUserDTO) {
        System.out.println("Received request with newUser: {}" + newUserDTO);
        return usersApiService.create(newUserDTO);
    }

    @GetMapping("/items")
    public List<Item> getItems(){ return itemsApiService.getItems(); }

    @GetMapping("/items/{id}")
    public Item getItemById(@PathVariable("id") int id) { return itemsApiService.getItemById(id); }
}
