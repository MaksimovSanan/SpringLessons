package ru.maksimov.ItemsService.dto.rentalObjectDto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class NewItemDTO {
    @NotNull(message = "Owner ID should not be empty")
    private Integer ownerId;

    @NotEmpty(message = "Title should not be empty")
    private String title;

    @NotEmpty(message = "Description of item cannot be empty")
    private String description;

    private Integer costPerHour;

    private Integer costPerDay;
}
