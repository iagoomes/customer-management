package br.com.iagoomes.customermanagement.service.impl;

import br.com.iagoomes.customermanagement.dto.CustomerDTO;
import br.com.iagoomes.customermanagement.service.Validation;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomerValidationImpl implements Validation<CustomerDTO> {

    private final CpfValidationImpl cpfValidation;
    private final PassportValidationImpl passportValidation;

    @Override
    public void isValid(CustomerDTO dto) {
        cpfValidation.isValid(dto.getCpf());
        passportValidation.isValid(dto.getPassport());
    }
}
