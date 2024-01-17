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
import ru.maksimov.RESTApp.dto.rentalObjectDto.BookDTO;
import ru.maksimov.RESTApp.dto.rentalObjectDto.NewBookDTO;
import ru.maksimov.RESTApp.models.RentalObject;
import ru.maksimov.RESTApp.services.RentalObjectService;
import ru.maksimov.RESTApp.services.PeopleService;
import ru.maksimov.RESTApp.util.BookErrorResponse;
import ru.maksimov.RESTApp.util.PersonErrorResponse;
import ru.maksimov.RESTApp.util.exceptions.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/rental_objects")
public class RentalObjectController {

    private final RentalObjectService rentalObjectService;
    private final PeopleService peopleService;
    private final ModelMapper modelMapper;

    @Autowired
    public RentalObjectController(RentalObjectService rentalObjectService, PeopleService peopleService, ModelMapper modelMapper) {
        this.rentalObjectService = rentalObjectService;
        this.peopleService = peopleService;
        this.modelMapper = modelMapper;
    }

    @GetMapping
    public List<BookDTO> getBooks() {
        return rentalObjectService.findAll().stream().map(this::convertToBookDTO).collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public BookDTO findOne(@PathVariable("id") int id) {
        return convertToBookDTO(rentalObjectService.findById(id));
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
        RentalObject rentalObject = convertToBook(newBookDTO);
        rentalObjectService.save(rentalObject);
        return ResponseEntity.ok(HttpStatus.CREATED);
    }

    @Transactional
    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> delete(@PathVariable("id") int id) {
        RentalObject rentalObject = rentalObjectService.findById(id);
        rentalObjectService.delete(rentalObject);
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
        RentalObject rentalObjectToBeUpdated = rentalObjectService.findById(id);
        rentalObjectToBeUpdated.setTitle(newBookDTO.getTitle());
        rentalObjectToBeUpdated.setAuthor(newBookDTO.getAuthor());
        rentalObjectToBeUpdated.setQuantity(newBookDTO.getQuantity());
        rentalObjectService.save(rentalObjectToBeUpdated);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    private BookDTO convertToBookDTO(RentalObject rentalObject) {
        BookDTO bookDTO = modelMapper.map(rentalObject, BookDTO.class);

        return bookDTO;
    }

    private RentalObject convertToBook(NewBookDTO newBookDTO) {
        return modelMapper.map(newBookDTO, RentalObject.class);
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
