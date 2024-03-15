package br.com.iagoomes.customermanagement.infra.exceptions;

public class PassportAlreadyExistsException extends RuntimeException {
    public PassportAlreadyExistsException(String passport) {
        super("Customer with Passport " + passport + " already exists");
    }
}
