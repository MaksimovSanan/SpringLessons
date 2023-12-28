package ru.maksimov.RESTApp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.maksimov.RESTApp.models.Book;

@Repository
public interface BooksRepository extends JpaRepository<Book, Integer> {
}
