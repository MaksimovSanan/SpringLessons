package ru.maksimov.RESTApp.util.exceptions;

public class ContractNotCreatedException extends RuntimeException{
    public ContractNotCreatedException(String message) {
        super(message);
    }
}
