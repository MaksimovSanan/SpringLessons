package ru.maksimov.ItemsService.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.maksimov.ItemsService.models.RentalItem;

@Repository
public interface RentalItemsRepository extends JpaRepository<RentalItem, Integer> {
}
