package ru.maksimov.webclient.models;

import lombok.*;


@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Item {
    private int id;

    private Integer ownerId;

    private String ownerName;

    private String title;

    private String description;

    private Integer status;

    private Integer costPerHour;

    private Integer costPerDay;
}
