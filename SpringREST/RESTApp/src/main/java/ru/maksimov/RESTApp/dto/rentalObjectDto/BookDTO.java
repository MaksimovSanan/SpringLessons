package ru.maksimov.RESTApp.dto.rentalObjectDto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BookDTO {
    private int id;
    private String title;
    private String author;
    private Integer quantity;
}
