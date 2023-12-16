package ru.maksimov.SpringBoot.util;

public class HtmlFormManager {
    public class PeopleHtml {
        public static final String index = "people/index";
        public static final String notFound = "people/notFound";
        public static final String show = "people/show";
        public static final String  newPerson = "people/new";
        public static final String redirectToMain = "redirect:/people";
        public static final String edit = "people/edit";
    }

    public class BookHtml {
        public static final String index = "books/index";
        public static final String notFound = "books/notFound";
        public static final String show = "books/show";
        public static final String  newBook = "books/new";
        public static final String redirectToMain = "redirect:/books";
        public static final String edit = "books/edit";
    }
}
