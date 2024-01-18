package ru.maksimov.ItemsService.dto.rentalObjectDto;

import jakarta.persistence.Column;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ItemDTO {
    private int id;

    private Integer ownerId;

    private String title;

    private String description;

    private Integer status;

    private Integer costPerHour;

    private Integer costPerDay;
}
