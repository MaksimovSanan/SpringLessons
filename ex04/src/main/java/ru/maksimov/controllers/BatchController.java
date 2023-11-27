package ru.maksimov.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import ru.maksimov.dao.PersonDAO;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@Controller
@RequestMapping("/batch-update")
public class BatchController {

    private final PersonDAO personDAO;

    public BatchController(PersonDAO personDAO) {
        this.personDAO = personDAO;
    }

    @GetMapping
    public String forBatchUpdate() {
        return "batch-update/batch";
    }

    @PostMapping
    public String batchUpdate(@RequestParam("quantity") int quantity) {
        personDAO.batchUpdate(quantity);
        return "redirect:/people";
    }

    @DeleteMapping
    public String batchDelete(@RequestParam("begin") int  begin, @RequestParam("end") int end) {
        personDAO.batchDelete(begin, end);
        return "redirect:/people";
    }
}
