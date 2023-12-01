package ru.maksimov.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.maksimov.models.Person;
import ru.maksimov.repositories.PeopleRepository;
import ru.maksimov.services.PeopleService;
import ru.maksimov.util.PersonValidator;

import javax.validation.Valid;
import java.util.Optional;

@Controller
@RequestMapping("/people")
public class PeopleController {

    private final PeopleService peopleService;
    private final PersonValidator personValidator;

    @Autowired
    public PeopleController(PeopleService peopleService, PersonValidator personValidator) {
        this.peopleService = peopleService;
        this.personValidator = personValidator;
    }

    @GetMapping()
    public String index(Model model){
        return null;
    }

    @GetMapping("/{id}")
    public String show(@PathVariable("id") int id, Model model) {
        return null;
    }

    @GetMapping("/new")
    public String newPerson(@ModelAttribute("person") Person person) {
        return null;
    }

    @PostMapping()
    public String createNewPerson(@ModelAttribute("person") @Valid Person person,
                                  BindingResult bindingResult) {

        return null;
    }

    @GetMapping("/{id}/edit")
    public String edit(Model model, @PathVariable("id") int id) {
        return null;
    }

    @PatchMapping("/{id}")
    public String update(@ModelAttribute("person") @Valid Person person,
                         BindingResult bindingResult,
                         @PathVariable("id") int id) {

        return null;
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id) {
        return null;
    }
}
