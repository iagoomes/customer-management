package br.com.iagoomes.customermanagement.exceptions;

public class CustomerNotFound extends RuntimeException{
    public CustomerNotFound(String message) {
        super(message);
    }
}
