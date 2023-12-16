package ru.maksimov.SpringBoot.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.maksimov.SpringBoot.models.Person;

@Repository
public interface PersonRepository extends JpaRepository<Person, Long> {
}
