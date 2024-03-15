package br.com.iagoomes.customermanagement.domain.customer.validation.impl;

import br.com.iagoomes.customermanagement.infra.exceptions.CpfAlreadyExistsException;
import br.com.iagoomes.customermanagement.domain.customer.CustomerRepository;
import br.com.iagoomes.customermanagement.domain.customer.validation.Validation;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;


@Component
@RequiredArgsConstructor
public class CpfValidationImpl implements Validation<String> {

    private final CustomerRepository customerRepository;

    @Override
    public void isValid(String cpf) {
        boolean exists = customerRepository.existsByCpf(cpf);
        if (exists) {
            throw new CpfAlreadyExistsException(cpf);
        }
    }
}
