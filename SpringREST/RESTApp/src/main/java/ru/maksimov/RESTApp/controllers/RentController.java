package ru.maksimov.RESTApp.controllers;

import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import ru.maksimov.RESTApp.dto.RentRequestDTO;
import ru.maksimov.RESTApp.models.Book;
import ru.maksimov.RESTApp.models.Person;
import ru.maksimov.RESTApp.services.BooksService;
import ru.maksimov.RESTApp.services.PeopleService;
import ru.maksimov.RESTApp.util.BookErrorResponse;
import ru.maksimov.RESTApp.util.RentCodes;
import ru.maksimov.RESTApp.util.exceptions.BadRequestException;

import java.util.List;

@RestController
@RequestMapping("/rent")
public class RentController {

    private final BooksService booksService;
    private final PeopleService peopleService;
    public RentController(BooksService booksService, PeopleService peopleService) {
        this.booksService = booksService;
        this.peopleService = peopleService;
    }

    @Transactional
    @PostMapping
    public ResponseEntity<HttpStatus> rentBook(@RequestBody @Valid RentRequestDTO rentRequestDTO,
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
        Integer bookId = rentRequestDTO.getBookId();
        Integer borrowerId = rentRequestDTO.getBorrowerId();

        Book book = booksService.findById(bookId);
        Person person = peopleService.findById(borrowerId);
        if(code.equals(RentCodes.borrowBook)) {
            peopleService.addBookToPerson(person, book);
        }
        else if(code.equals(RentCodes.returnBook) ) {
            peopleService.removeBookFromPerson(person, book);
        }
        return ResponseEntity.ok(HttpStatus.OK);
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
