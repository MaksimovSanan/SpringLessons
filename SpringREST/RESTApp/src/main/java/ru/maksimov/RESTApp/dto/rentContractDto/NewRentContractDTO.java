package ru.maksimov.RESTApp.dto.rentContractDto;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class NewRentContractDTO {
    @NotNull(message = "Borrower ID cannot be empty")
    private Integer borrowerId;
    @NotNull(message = "RentObject ID cannot be empty")
    private Integer rentObjectId;
}
