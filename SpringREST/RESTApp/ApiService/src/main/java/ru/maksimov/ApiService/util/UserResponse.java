package ru.maksimov.ApiService.util;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserResponse {
    private String message;
    private long timestamp;

    public UserResponse(String message, long timestamp) {
        this.message = message;
        this.timestamp = timestamp;
    }
}
