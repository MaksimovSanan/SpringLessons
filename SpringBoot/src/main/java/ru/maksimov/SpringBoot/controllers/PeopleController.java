package ru.maksimov.SpringBoot.controllers;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.maksimov.SpringBoot.models.Person;
import ru.maksimov.SpringBoot.services.PeopleService;
import ru.maksimov.SpringBoot.util.HtmlFormManager;

import java.util.Optional;

@Controller
@RequestMapping("/people")
public class PeopleController {
    private final PeopleService peopleService;

    @Autowired
    public PeopleController(PeopleService peopleService) {
        this.peopleService = peopleService;
    }

    @GetMapping
    public String index(Model model){
        model.addAttribute("people", peopleService.findAll());
        return HtmlFormManager.PeopleHtml.index;
    }

    @GetMapping("/{id}")
    public String show(@PathVariable("id") Long id, Model model) {
        Optional<Person> person = peopleService.findById(id);
        if(!person.isPresent()) {
            model.addAttribute("id", id);
            return HtmlFormManager.PeopleHtml.notFound;
        }
        else{
            model.addAttribute("person", person.get());
            return HtmlFormManager.PeopleHtml.show;
        }
    }

    @GetMapping("/new")
    public String newPerson(@ModelAttribute("person") Person person) {
        return HtmlFormManager.PeopleHtml.newPerson;
    }

    @PostMapping
    public String createNewPerson(@ModelAttribute("person") @Valid Person person, BindingResult bindingResult){

        if(bindingResult.hasErrors()) {
            return HtmlFormManager.PeopleHtml.newPerson;
        }
        peopleService.save(person);
        return HtmlFormManager.PeopleHtml.redirectToMain;
    }

    @GetMapping("/{id}/edit")
    public String edit(@PathVariable("id") Long id, Model model) {
        Optional<Person> person = peopleService.findById(id);
        if(!person.isPresent()) {
            model.addAttribute("id", id);
            return HtmlFormManager.PeopleHtml.notFound;
        }
        else {
            model.addAttribute("person", person.get());
            return HtmlFormManager.PeopleHtml.edit;
        }
    }

    @PatchMapping("/{id}")
    public String update(@PathVariable("id") Long id, @ModelAttribute("person") @Valid Person person,
                         BindingResult bindingResult) {
        if(bindingResult.hasErrors()) {
            return HtmlFormManager.PeopleHtml.edit;
        }
        else {
            peopleService.update(id, person);
            return HtmlFormManager.PeopleHtml.redirectToMain;
        }
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") Long id, Model model) {
        Optional<Person> person = peopleService.findById(id);
        if(!person.isPresent()) {
            model.addAttribute("id", id);
            return HtmlFormManager.PeopleHtml.notFound;
        }
        else{
            peopleService.delete(person.get());
            return HtmlFormManager.PeopleHtml.redirectToMain;
        }
    }
}





















