package ru.maksimov.uiService.controllers;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import ru.maksimov.uiService.dto.BookDto;
import ru.maksimov.uiService.dto.NewBookDto;
import ru.maksimov.uiService.dto.PersonDto;
import ru.maksimov.uiService.dto.RentRequest;
import ru.maksimov.uiService.util.HtmlFormManager;

import java.awt.print.Book;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/books")
public class BooksController {
    private final String booksServiceUrl = "http://localhost:8090/books";

    @Autowired
    private RestTemplate restTemplate;

    @GetMapping
    public String showBooksPage(@RequestParam(required = false) String title, Model model) {
        BookDto[] booksArray = restTemplate.getForObject(booksServiceUrl, BookDto[].class);

        // Фильтрация по имени, если указан параметр поиска
        List<BookDto> filteredBooks = Arrays.stream(booksArray)
                .filter(person -> title == null || person.getTitle().toLowerCase().contains(title.toLowerCase()))
                .toList();

        model.addAttribute("books", filteredBooks);
        return HtmlFormManager.booksIndex;
    }

    @GetMapping("/{id}")
    public String showBookDetails(@PathVariable int id, Model model, @ModelAttribute("rentRequest") RentRequest rentRequest) {

        String bookDetailsUrl = booksServiceUrl + "/" + id;
        BookDto book = restTemplate.getForObject(bookDetailsUrl, BookDto.class);

        model.addAttribute("book", book);
        PersonDto[] peopleArray = restTemplate.getForObject("http://localhost:8090/people", PersonDto[].class);
        model.addAttribute("people", peopleArray);

        // Проверка, инициализация rentRequest
        if (book.getBorrowerId() != null) {
            rentRequest.setCode(202);
            rentRequest.setBorrowerId(book.getBorrowerId());
            rentRequest.setBookId(id);
        } else {
            rentRequest.setCode(201);
            rentRequest.setBookId(id);
            // Установите значение borrowerId по вашему усмотрению
            // Например, первый арендатор из списка
            if (peopleArray.length > 0) {
                rentRequest.setBorrowerId(peopleArray[0].getId());
            }
        }

        return HtmlFormManager.bookDetails;
    }


    @PostMapping("/{id}/rent")
    public String rentBook(@PathVariable int id, Model model, @ModelAttribute("rentRequest") RentRequest rentRequest) {

        final String rentBookServiceUrl = "http://localhost:8090/books/{id}/rent";

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<RentRequest> requestEntity = new HttpEntity<>(rentRequest, headers);

        restTemplate.postForObject(rentBookServiceUrl, requestEntity, Void.class, id);

        String bookDetailsUrl = booksServiceUrl + "/" + id;
        BookDto book = restTemplate.getForObject(bookDetailsUrl, BookDto.class);

        model.addAttribute("book", book);

        PersonDto[] peopleArray = restTemplate.getForObject("http://localhost:8090/people", PersonDto[].class);
        model.addAttribute("people", peopleArray);

        return HtmlFormManager.bookDetails;
    }

    @GetMapping("/new-book-form")
    public String newBookForm(@ModelAttribute("book") NewBookDto newBookDto) {
        return HtmlFormManager.newBookForm;
    }

    @PostMapping("/create")
    public String createNewBook(@ModelAttribute("book") @Valid NewBookDto newBookDto,
                                BindingResult bindingResult) {
        if(bindingResult.hasErrors()) {
            return HtmlFormManager.newBookForm;
        }

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<NewBookDto> requestEntity = new HttpEntity<>(newBookDto, headers);
        restTemplate.postForObject(booksServiceUrl, requestEntity, String.class);

        return "redirect:/books";
    }

    @DeleteMapping("/{id}")
    public String deleteBook(@PathVariable("id") int id) {
        restTemplate.delete(booksServiceUrl + "/" + id);
        return "redirect:/books";
    }

}
