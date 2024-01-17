package ru.maksimov.RESTApp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.maksimov.RESTApp.models.RentalObject;

@Repository
public interface RentalObjectRepository extends JpaRepository<RentalObject, Integer> {
}
