package ru.maksimov.RESTApp.dto.rentContractDto;

import jakarta.persistence.Column;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class RentContractDTO {
    private int id;
    private int borrowerId;
    private int rentObjectId;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    public RentContractDTO() {
    }

    public RentContractDTO(int id, int borrowerId, int rentObjectId, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.id = id;
        this.borrowerId = borrowerId;
        this.rentObjectId = rentObjectId;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }
}
