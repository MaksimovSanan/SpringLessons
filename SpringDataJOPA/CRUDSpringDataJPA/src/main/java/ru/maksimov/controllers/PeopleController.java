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
        model.addAttribute("people", peopleService.findAll());
        return "people/index";
    }

    @GetMapping("/{id}")
    public String show(@PathVariable("id") int id, Model model) {
        Optional<Person> person = peopleService.findById(id);
        if(!person.isPresent()) {
            model.addAttribute("id", id);
            return "people/notFound";
        }
        model.addAttribute("person", person.get());
        return "people/show";
    }

    @GetMapping("/new")
    public String newPerson(@ModelAttribute("person") Person person) {
        return "people/new";
    }

    @PostMapping()
    public String createNewPerson(@ModelAttribute("person") @Valid Person person,
                                  BindingResult bindingResult) {
        personValidator.validate(person, bindingResult);

        if(bindingResult.hasErrors()) {
            return "people/new";
        }
        peopleService.save(person);
        return "redirect:/people";
    }

    @GetMapping("/{id}/edit")
    public String edit(Model model, @PathVariable("id") int id) {
        Optional<Person> person = peopleService.findById(id);
        if(!person.isPresent()) {
            model.addAttribute("id", id);
            return "people/notFound";
        }
        model.addAttribute("person", person.get());
        return "people/edit";
    }

    @PatchMapping("/{id}")
    public String update(@ModelAttribute("person") @Valid Person person,
                         BindingResult bindingResult,
                         @PathVariable("id") int id) {

        if(bindingResult.hasErrors()) {
            return "people/edit";
        }
        person.setId(id);
        peopleService.save(person);
        return "redirect:/people";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id, Model model) {
        Optional<Person> person = peopleService.findById(id);
        if(!person.isPresent()) {
            model.addAttribute("id", id);
            return "people/notFound";
        }
        peopleService.delete(person.get());
        return "redirect:/people";
    }
}
