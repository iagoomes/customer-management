package br.com.iagoomes.customermanagement.infra.exceptions;

public class CpfAlreadyExistsException extends RuntimeException {
    public CpfAlreadyExistsException(String cpf) {
        super("Customer with CPF " + cpf + " already exists");
    }
}
