package br.com.iagoomes.customermanagement.service.impl;

import br.com.iagoomes.customermanagement.exceptions.CpfAlreadyExistsException;
import br.com.iagoomes.customermanagement.repository.CustomerRepository;
import br.com.iagoomes.customermanagement.service.Validation;
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
