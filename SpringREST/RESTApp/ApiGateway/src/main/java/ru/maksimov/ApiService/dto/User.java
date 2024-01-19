package ru.maksimov.ApiService.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@Getter
@Setter
@ToString
public class User {
    private Integer id;
    private String login;
    private String password;
    private String email;
}
