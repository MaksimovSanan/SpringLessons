package ru.maksimov.SpringBoot.controllers;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.maksimov.SpringBoot.models.Book;
import ru.maksimov.SpringBoot.models.Person;
import ru.maksimov.SpringBoot.services.BookService;
import ru.maksimov.SpringBoot.util.HtmlFormManager;

import java.util.Optional;

@Controller
@RequestMapping("/books")
public class BooksController {
    private final BookService bookService;

    @Autowired
    public BooksController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping
    public String index(Model model){
        model.addAttribute("books", bookService.findAll());
        return HtmlFormManager.BookHtml.index;
    }

    @GetMapping("/{id}")
    public String show(@PathVariable("id") Long id, Model model) {
        Optional<Book> book = bookService.findById(id);
        if(!book.isPresent()) {
            model.addAttribute("id", id);
            return HtmlFormManager.BookHtml.notFound;
        }
        else{
            model.addAttribute("book", book.get());
            return HtmlFormManager.BookHtml.show;
        }
    }

    @GetMapping("/new")
    public String newBook(@ModelAttribute("book") Book book) {
        return HtmlFormManager.BookHtml.newBook;
    }

    @PostMapping
    public String createNewBook(@ModelAttribute("book") @Valid Book book, BindingResult bindingResult){

        if(bindingResult.hasErrors()) {
            return HtmlFormManager.BookHtml.newBook;
        }
        bookService.save(book);
        return HtmlFormManager.BookHtml.redirectToMain;
    }

    @GetMapping("/{id}/edit")
    public String edit(@PathVariable("id") Long id, Model model) {
        Optional<Book> book = bookService.findById(id);
        if(!book.isPresent()) {
            model.addAttribute("id", id);
            return HtmlFormManager.BookHtml.notFound;
        }
        else {
            model.addAttribute("book", book.get());
            return HtmlFormManager.BookHtml.edit;
        }
    }

    @PatchMapping("/{id}")
    public String update(@PathVariable("id") Long id, @ModelAttribute("book") @Valid Book book,
                         BindingResult bindingResult) {
        if(bindingResult.hasErrors()) {
            return HtmlFormManager.BookHtml.edit;
        }
        else {
            bookService.update(id, book);
            return HtmlFormManager.BookHtml.redirectToMain;
        }
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") Long id, Model model) {
        Optional<Book> book = bookService.findById(id);
        if(!book.isPresent()) {
            model.addAttribute("id", id);
            return HtmlFormManager.BookHtml.notFound;
        }
        else{
            bookService.delete(book.get());
            return HtmlFormManager.BookHtml.redirectToMain;
        }
    }
}
