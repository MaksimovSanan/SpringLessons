package ru.maksimov.RESTApp.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.maksimov.RESTApp.models.Book;
import ru.maksimov.RESTApp.repositories.BooksRepository;
import ru.maksimov.RESTApp.util.exceptions.BookNotFoundException;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class BooksService {
    private final BooksRepository booksRepository;

    @Autowired
    public BooksService(BooksRepository booksRepository) {
        this.booksRepository = booksRepository;
    }

    public List<Book> findAll() {
        return booksRepository.findAll();
    }

    public Book findById(int id) {
        return booksRepository.findById(id).orElseThrow(BookNotFoundException::new);
    }

    public void save(Book book) {
        booksRepository.save(book);
    }

    public void delete(Book book) {
        booksRepository.delete(book);
    }
}
