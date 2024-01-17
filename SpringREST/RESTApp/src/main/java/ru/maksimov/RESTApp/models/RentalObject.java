package ru.maksimov.RESTApp.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "rental_objects")
@NoArgsConstructor
@Getter
@Setter
public class RentalObject {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    @Column(name = "title")
    private String title;

    @Column(name = "author")
    private String author;

    @Column(name = "quantity")
    private Integer quantity;

    @OneToMany(mappedBy = "rentalObject")
    private List<RentContract> rentContracts;

    public RentalObject(String title, String author, Integer quantity) {
        this.title = title;
        this.author = author;
        this.quantity = quantity;
    }
}
