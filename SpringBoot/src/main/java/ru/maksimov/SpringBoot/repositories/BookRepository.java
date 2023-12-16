package ru.maksimov.SpringBoot.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.maksimov.SpringBoot.models.Book;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {

}
