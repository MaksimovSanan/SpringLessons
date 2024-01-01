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
import ru.maksimov.uiService.dto.*;
import ru.maksimov.uiService.util.HtmlFormManager;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/people")
public class PeopleController {

    private final String peopleServiceUrl = "http://localhost:8090/people";
    private final String booksServiceUrl = "http://localhost:8090/books";
    private final String rentServiceUrl = "http://localhost:8090/rent";
    @Autowired
    private RestTemplate restTemplate;

    @GetMapping()
    public String showPeoplePage(@RequestParam(required = false) String name, Model model) {
        PersonDto[] peopleArray = restTemplate.getForObject(peopleServiceUrl, PersonDto[].class);

        // Фильтрация по имени, если указан параметр поиска
        List<PersonDto> filteredPeople = Arrays.stream(peopleArray)
            .filter(person -> name == null || person.getName().toLowerCase().contains(name.toLowerCase()))
            .collect(Collectors.toList());

        model.addAttribute("people", filteredPeople);
        return HtmlFormManager.peopleIndex;
    }

    @GetMapping("/{id}")
    public String showPersonDetails(@PathVariable("id") int id, Model model) {
        PersonWithBooksDto person = restTemplate.getForObject(
                peopleServiceUrl + "/" + id,
                PersonWithBooksDto.class
        );
        model.addAttribute("person", person);
        return HtmlFormManager.personDetails;
    }

    @GetMapping("/{id}/rent")
    public String rentBookForm(@PathVariable int id, Model model,
                               @ModelAttribute("rentRequest") RentRequest rentRequest) {

        BookDto[] bookArray = restTemplate.getForObject(booksServiceUrl, BookDto[].class);
        List<BookDto> listBooks = Arrays.stream(bookArray).
                filter(bookDto -> bookDto.getBorrowerId() == null)
                .toList();
        model.addAttribute("books", listBooks);

        PersonWithBooksDto person = restTemplate.getForObject(
                peopleServiceUrl + "/" + id,
                PersonWithBooksDto.class
        );
        model.addAttribute("person", person);

        rentRequest.setBorrowerId(id);
        rentRequest.setCode(201);
        if (bookArray.length > 0) {
            rentRequest.setBookId(bookArray[0].getId());
        }

        return HtmlFormManager.rentBookForm;
    }

    @PostMapping("/{id}/rent")
    public String rentBook(@PathVariable int id, Model model,
                               @ModelAttribute("rentRequest") RentRequest rentRequest) {

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<RentRequest> requestEntity = new HttpEntity<>(rentRequest, headers);

        restTemplate.postForObject(rentServiceUrl, requestEntity, Void.class);
//
//        PersonWithBooksDto person = restTemplate.getForObject(
//                peopleServiceUrl + "/" + id,
//                PersonWithBooksDto.class
//        );
//        model.addAttribute("person", person);

        return "redirect:/people/" + id;
    }

    @GetMapping("/new-person-form")
    public String newPersonForm(@ModelAttribute("person") NewPersonDto newPersonDto){
        return HtmlFormManager.newPersonForm;
    }

    @PostMapping("/create")
    public String createNewPerson(@ModelAttribute("person") @Valid NewPersonDto newPersonDto,
                                BindingResult bindingResult) {
        if(bindingResult.hasErrors()) {
            return HtmlFormManager.newPersonForm;
        }

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<NewPersonDto> requestEntity = new HttpEntity<>(newPersonDto, headers);
        restTemplate.postForObject(peopleServiceUrl, requestEntity, String.class);

        return "redirect:/people";
    }

    @DeleteMapping("/{id}")
    public String deleteBook(@PathVariable("id") int id) {
        restTemplate.delete(peopleServiceUrl + "/" + id);
        return "redirect:/people";
    }
}
