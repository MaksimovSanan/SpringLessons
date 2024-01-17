package ru.maksimov.RESTApp.dto.rentalObjectDto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class NewBookDTO {
    @NotEmpty(message = "Title should not be empty")
    private String title;
    @NotEmpty(message = "Author of book cannot be empty")
    private String author;
    @NotNull(message = "Quantity of book cannot be empty")
    private Integer quantity;
}
