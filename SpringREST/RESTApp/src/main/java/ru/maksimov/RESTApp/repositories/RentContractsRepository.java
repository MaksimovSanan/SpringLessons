package ru.maksimov.RESTApp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.maksimov.RESTApp.models.RentContract;

@Repository
public interface RentContractsRepository extends JpaRepository<RentContract, Integer> {
}
