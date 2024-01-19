package ru.maksimov.ApiService.config;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.service.annotation.GetExchange;
import org.springframework.web.service.annotation.HttpExchange;
import org.springframework.web.service.annotation.PostExchange;
import ru.maksimov.ApiService.dto.NewUserDTO;
import ru.maksimov.ApiService.dto.User;

import java.util.List;

@HttpExchange("http://127.0.0.1:8095/users")
public interface UsersAPIClient {

    @GetExchange()
    List<User> getUsers();

    @GetExchange("/{id}")
    User getUserById(@PathVariable int id);

    @PostExchange()
    ResponseEntity<HttpStatus> save(@RequestBody NewUserDTO newUserDTO);
}
