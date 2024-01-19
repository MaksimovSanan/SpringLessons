package ru.maksimov.ApiService.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class Item {
    private int id;

    private Integer ownerId;

    private String title;

    private String description;

    private Integer status;

    private Integer costPerHour;

    private Integer costPerDay;
}
