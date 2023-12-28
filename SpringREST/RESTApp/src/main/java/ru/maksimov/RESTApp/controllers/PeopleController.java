package ru.maksimov.RESTApp.controllers;

import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import ru.maksimov.RESTApp.dto.NewPersonDTO;
import ru.maksimov.RESTApp.dto.PersonDTO;
import ru.maksimov.RESTApp.dto.PersonWithBooksDTO;
import ru.maksimov.RESTApp.models.Person;
import ru.maksimov.RESTApp.services.PeopleService;
import ru.maksimov.RESTApp.util.PersonErrorResponse;
import ru.maksimov.RESTApp.util.exceptions.PersonNotCreatedException;
import ru.maksimov.RESTApp.util.exceptions.PersonNotFoundException;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/people")
public class PeopleController {
    private final PeopleService peopleService;
    private final ModelMapper modelMapper;

    @Autowired
    public PeopleController(PeopleService peopleService, ModelMapper modelMapper) {
        this.peopleService = peopleService;
        this.modelMapper = modelMapper;
    }

    @GetMapping
    public List<PersonDTO> getPeople() {
        return peopleService.findAll().stream().map(this::convertToPersonDTO).collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public PersonWithBooksDTO findOne(@PathVariable("id") int id) {
        PersonWithBooksDTO person = convertToPersonWithBooksDTO(peopleService.findById(id));
        return person;
    }

    @PostMapping
    public ResponseEntity<HttpStatus> create(@RequestBody @Valid NewPersonDTO newPersonDTO,
                                             BindingResult bindingResult) {
        if(bindingResult.hasErrors()) {
            StringBuilder errorMsg = new StringBuilder();
            List<FieldError> errors = bindingResult.getFieldErrors();
            for(FieldError error : errors) {
                errorMsg.append(error.getField())
                        .append(" - ").append(error.getDefaultMessage())
                        .append(";");
            }
            throw new PersonNotCreatedException(errorMsg.toString());
        }
        peopleService.save(convertToPerson(newPersonDTO));
        return ResponseEntity.ok(HttpStatus.CREATED); // empty body status 200
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> delete(@PathVariable("id") int id) {
        Person person = peopleService.findById(id);
        peopleService.delete(person);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<HttpStatus> update(@PathVariable("id") int id, @RequestBody @Valid NewPersonDTO newPersonDTO,
                                             BindingResult bindingResult) {
        if(bindingResult.hasErrors()) {
            StringBuilder errorMsg = new StringBuilder();
            List<FieldError> errors = bindingResult.getFieldErrors();
            for(FieldError error: errors) {
                errorMsg.append(error.getField())
                        .append(" - ").append(error.getDefaultMessage())
                        .append(";");
            }
            throw new PersonNotCreatedException(errorMsg.toString());
        }
        Person personToBeUpdated = peopleService.findById(id);
        peopleService.update(id, convertToPerson(newPersonDTO));
        return ResponseEntity.ok(HttpStatus.OK);
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
    private ResponseEntity<PersonErrorResponse> handleException(PersonNotCreatedException personNotCreatedException) {
        PersonErrorResponse personErrorResponse = new PersonErrorResponse(
                personNotCreatedException.getMessage(),
                System.currentTimeMillis()
        );
        return new ResponseEntity<>(personErrorResponse, HttpStatus.BAD_REQUEST);
    }

    private Person convertToPerson(NewPersonDTO newPersonDTO) {

        Person person = modelMapper.map(newPersonDTO, Person.class);

        return person;
    }

    private PersonDTO convertToPersonDTO(Person person) {
        return modelMapper.map(person, PersonDTO.class);
    }

    private PersonWithBooksDTO convertToPersonWithBooksDTO(Person person) {
        return modelMapper.map(person, PersonWithBooksDTO.class);
    }
}
















