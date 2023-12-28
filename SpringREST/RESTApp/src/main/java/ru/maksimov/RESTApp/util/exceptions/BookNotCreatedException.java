package ru.maksimov.RESTApp.util.exceptions;

public class BookNotCreatedException extends RuntimeException{
    public BookNotCreatedException(String message) {
        super(message);
    }
}
