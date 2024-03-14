package br.com.iagoomes.customermanagement.service;

public interface Validation<T> {
    void isValid(T dto);
}
