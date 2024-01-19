package ru.maksimov.ApiService.services;

import org.springframework.http.ResponseEntity;
import ru.maksimov.ApiService.dto.NewUserDTO;
import ru.maksimov.ApiService.dto.User;
import ru.maksimov.ApiService.util.UserResponse;

import java.util.List;

public interface UsersApiService {
    List<User> getUsers();
    User getUserById(int id);
    ResponseEntity<UserResponse> create(NewUserDTO newUserDTO);
}
