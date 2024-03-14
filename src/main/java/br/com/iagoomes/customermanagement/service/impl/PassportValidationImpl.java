package br.com.iagoomes.customermanagement.service.impl;

import br.com.iagoomes.customermanagement.exceptions.PassportAlreadyExistsException;
import br.com.iagoomes.customermanagement.repository.CustomerRepository;
import br.com.iagoomes.customermanagement.service.Validation;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;


@Component
@RequiredArgsConstructor
public class PassportValidationImpl implements Validation<String> {

    private final CustomerRepository customerRepository;

    @Override
    public void isValid(String passport) {
        boolean exists = customerRepository.existsByPassport(passport);
        if (exists) {
            throw new PassportAlreadyExistsException(passport);
        }
    }
}
