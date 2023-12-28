package ru.maksimov.RESTApp.controllers;

import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import ru.maksimov.RESTApp.dto.BookDTO;
import ru.maksimov.RESTApp.dto.NewBookDTO;
import ru.maksimov.RESTApp.dto.RentRequestDTO;
import ru.maksimov.RESTApp.models.Book;
import ru.maksimov.RESTApp.models.Person;
import ru.maksimov.RESTApp.services.BooksService;
import ru.maksimov.RESTApp.services.PeopleService;
import ru.maksimov.RESTApp.util.BookErrorResponse;
import ru.maksimov.RESTApp.util.PersonErrorResponse;
import ru.maksimov.RESTApp.util.RentCodes;
import ru.maksimov.RESTApp.util.exceptions.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/books")
public class BooksController {

    private final BooksService booksService;
    private final PeopleService peopleService;
    private final ModelMapper modelMapper;

    @Autowired
    public BooksController(BooksService booksService, PeopleService peopleService, ModelMapper modelMapper) {
        this.booksService = booksService;
        this.peopleService = peopleService;
        this.modelMapper = modelMapper;
    }

    @GetMapping
    public List<BookDTO> getBooks() {
        return booksService.findAll().stream().map(this::convertToBookDTO).collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public BookDTO findOne(@PathVariable("id") int id) {
        return convertToBookDTO(booksService.findById(id));
    }

    @Transactional
    @PostMapping
    public ResponseEntity<HttpStatus> create(@RequestBody @Valid NewBookDTO newBookDTO,
                                             BindingResult bindingResult) {
        if(bindingResult.hasErrors()) {
            StringBuilder errorMsg = new StringBuilder();
            List<FieldError> errors = bindingResult.getFieldErrors();
            for(FieldError error: errors) {
                errorMsg.append(error.getField())
                        .append(" - ").append(error.getDefaultMessage())
                        .append(";");
                throw new BookNotCreatedException(errorMsg.toString());
            }
        }
        Book book = convertToBook(newBookDTO);
        booksService.save(book);
        return ResponseEntity.ok(HttpStatus.CREATED);
    }

    @Transactional
    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> delete(@PathVariable("id") int id) {
        Book book = booksService.findById(id);
        booksService.delete(book);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @Transactional
    @PatchMapping("/{id}")
    public ResponseEntity<HttpStatus> update(@PathVariable("id") int id,
                                             @RequestBody @Valid NewBookDTO newBookDTO,
                                             BindingResult bindingResult) {
        if(bindingResult.hasErrors()) {
            StringBuilder errorMsg = new StringBuilder();
            List<FieldError> errors = bindingResult.getFieldErrors();
            for(FieldError error: errors) {
                errorMsg.append(error.getField())
                        .append(" - ").append(error.getDefaultMessage())
                        .append(";");
                throw new BookNotCreatedException(errorMsg.toString());
            }
        }
        Book bookToBeUpdated = booksService.findById(id);
        bookToBeUpdated.setTitle(newBookDTO.getTitle());
        bookToBeUpdated.setAuthor(newBookDTO.getAuthor());
        booksService.save(bookToBeUpdated);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @Transactional
    @PostMapping("/{id}/rent")
    public ResponseEntity<HttpStatus> rentBook(@PathVariable("id") int id,
                                               @RequestBody @Valid RentRequestDTO rentRequestDTO,
                                               BindingResult bindingResult) {

        if(bindingResult.hasErrors()) {
            StringBuilder errorMsg = new StringBuilder();
            List<FieldError> errors = bindingResult.getFieldErrors();
            for(FieldError error: errors) {
                errorMsg.append(error.getField())
                        .append(" - ").append(error.getDefaultMessage())
                        .append(";");
                throw new BadRequestException(errorMsg.toString());
            }
        }
        Integer code = rentRequestDTO.getCode();

        Book book = booksService.findById(id);
        Person person = peopleService.findById(rentRequestDTO.getBorrowerId());
        if(code.equals(RentCodes.borrowBook)) {
            peopleService.addBookToPerson(person, book);
        }
        else if(code.equals(RentCodes.returnBook) ) {
            peopleService.removeBookFromPerson(person, book);
        }
        return ResponseEntity.ok(HttpStatus.OK);
    }

    private BookDTO convertToBookDTO(Book book) {
        BookDTO bookDTO = modelMapper.map(book, BookDTO.class);

        // OBALDET MAPPER DELAET ETO SAM
//        if(book.getBorrower() != null) {
//            bookDTO.setBorrowerId(book.getBorrower().getId());
//            bookDTO.setBorrowerName(book.getBorrower().getName());
//        }
        return bookDTO;
    }

    private Book convertToBook(NewBookDTO newBookDTO) {
        return modelMapper.map(newBookDTO, Book.class);
    }

    @ExceptionHandler
    private ResponseEntity<BookErrorResponse> handleException(BookNotFoundException bookNotFoundException) {
        BookErrorResponse bookErrorResponse = new BookErrorResponse(
                "Book with this id not found",
                System.currentTimeMillis()
        );
        return new ResponseEntity<>(bookErrorResponse, HttpStatus.NOT_FOUND); // NOT_FOUND - 404
    }
    @ExceptionHandler
    private ResponseEntity<BookErrorResponse> handleException(BookNotCreatedException bookNotCreatedException) {
        BookErrorResponse bookErrorResponse = new BookErrorResponse(
                bookNotCreatedException.getMessage(),
                System.currentTimeMillis()
        );
        return new ResponseEntity<>(bookErrorResponse, HttpStatus.BAD_REQUEST); // NOT_FOUND - 404
    }

    @ExceptionHandler
    private ResponseEntity<PersonErrorResponse> handleException(PersonNotFoundException personNotFoundException){
        PersonErrorResponse personErrorResponse = new PersonErrorResponse(
                "Person with this id was not found",
                System.currentTimeMillis()
        );
        return new ResponseEntity<>(personErrorResponse, HttpStatus.NOT_FOUND); // NOT_FOUND - 404
    }


    @ExceptionHandler
    private ResponseEntity<BookErrorResponse> handleException(BadRequestException badRequestException){
        BookErrorResponse bookErrorResponse = new BookErrorResponse(
                badRequestException.getMessage(),
                System.currentTimeMillis()
        );
        return new ResponseEntity<>(bookErrorResponse, HttpStatus.BAD_REQUEST);
    }
}
