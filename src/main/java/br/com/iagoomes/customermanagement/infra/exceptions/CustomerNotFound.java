package br.com.iagoomes.customermanagement.infra.exceptions;

public class CustomerNotFound extends RuntimeException {
    public CustomerNotFound() {
        super("Resource not found or deleted");
    }
}
