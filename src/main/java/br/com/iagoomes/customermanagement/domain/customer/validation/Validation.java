package br.com.iagoomes.customermanagement.domain.customer.validation;

public interface Validation<T> {
    void isValid(T dto);
}
