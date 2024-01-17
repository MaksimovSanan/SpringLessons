package ru.maksimov.RESTApp.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "rent_contracts")
@NoArgsConstructor
@Getter
@Setter
public class RentContract {
    @Column(name = "id")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne()
    @JoinColumn(name = "borrower_id", referencedColumnName = "id")
    private Person borrower;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @ManyToOne()
    @JoinColumn(name = "book_id", referencedColumnName = "id")
    private RentalObject rentalObject;

}
