package br.com.iagoomes.customermanagement.service;

import br.com.iagoomes.customermanagement.dto.CustomerDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.util.UriComponentsBuilder;

public interface CustomerService {
    ResponseEntity<CustomerDTO> createCustomer(CustomerDTO dto, UriComponentsBuilder uriBuilder);

    ResponseEntity<CustomerDTO> findCustomer(Long id);

    ResponseEntity<Page<CustomerDTO>> findCustomers(Pageable page);

    ResponseEntity<CustomerDTO> updateCustomer(Long id, CustomerDTO dto);

    ResponseEntity<Void> deleteCustomer(Long id);
}
