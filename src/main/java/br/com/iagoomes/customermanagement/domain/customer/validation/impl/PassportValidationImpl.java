package br.com.iagoomes.customermanagement.domain.customer.validation.impl;

import br.com.iagoomes.customermanagement.infra.exceptions.PassportAlreadyExistsException;
import br.com.iagoomes.customermanagement.domain.customer.CustomerRepository;
import br.com.iagoomes.customermanagement.domain.customer.validation.Validation;
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
