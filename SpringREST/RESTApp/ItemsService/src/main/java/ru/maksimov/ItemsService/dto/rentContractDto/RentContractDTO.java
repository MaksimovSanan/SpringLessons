package ru.maksimov.ItemsService.dto.rentContractDto;

import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;
import ru.maksimov.ItemsService.dto.rentalObjectDto.ItemDTO;
import ru.maksimov.ItemsService.models.RentalItem;

import java.time.LocalDateTime;

@Getter
@Setter
public class RentContractDTO {
    private int id;

    private ItemDTO rentalItem;

    private Integer borrowerId;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    private LocalDateTime reservedTo;
    public RentContractDTO() {
    }
}
