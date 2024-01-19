package ru.maksimov.ApiService.services;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClientResponseException;
import ru.maksimov.ApiService.config.UsersAPIClient;
import ru.maksimov.ApiService.dto.NewUserDTO;
import ru.maksimov.ApiService.dto.User;
import ru.maksimov.ApiService.util.UserResponse;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RestUsersApiService implements UsersApiService {

    private final UsersAPIClient usersAPIClient;


    @Override
    public List<User> getUsers() {
        return usersAPIClient.getUsers();
    }

    @Override
    public User getUserById(int id) {
        return usersAPIClient.getUserById(id);
    }

    @Override
    public ResponseEntity<UserResponse> create(NewUserDTO newUserDTO) {
        try {
            ResponseEntity<HttpStatus> response = usersAPIClient.save(newUserDTO);
            // Обработка успешного ответа
        } catch (WebClientResponseException ex) {
            if (ex.getStatusCode().is4xxClientError()) {
                // Ошибка 4xx - обработка ошибки
                UserResponse userResponse = new UserResponse(
                        ex.getMessage(), // или используйте другие доступные методы для получения информации
                        System.currentTimeMillis()
                );
                return new ResponseEntity<>(userResponse, ex.getStatusCode());
            } else {
                // Обработка других ошибок
                // например, можно просто пробросить исключение дальше
                throw ex;
            }
        }
        return new ResponseEntity<>(new UserResponse("OK", System.currentTimeMillis()), HttpStatus.OK);
    }
}
