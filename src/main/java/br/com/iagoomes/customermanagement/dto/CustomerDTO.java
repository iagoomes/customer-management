package br.com.iagoomes.customermanagement.dto;

import br.com.iagoomes.customermanagement.model.Customer;
import lombok.Getter;

@Getter
public class CustomerDTO {
    private String nome;

    public Customer toModel(CustomerDTO customerDTO) {
        return Customer.builder()
                .fullName(customerDTO.getNome())
                .build();
    }
}
