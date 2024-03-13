package br.com.iagoomes.customermanagement.service;

import br.com.iagoomes.customermanagement.dto.CustomerDTO;
import br.com.iagoomes.customermanagement.mapper.CustomerMapper;
import br.com.iagoomes.customermanagement.model.Customer;
import br.com.iagoomes.customermanagement.repository.CustomerRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@Service
public class CustomerService {
    private final CustomerRepository customerRepository;
    private final CustomerMapper customerMapper;

    public CustomerService(CustomerRepository customerRepository, CustomerMapper customerMapper) {
        this.customerRepository = customerRepository;
        this.customerMapper = customerMapper;
    }

    public ResponseEntity<CustomerDTO> createCustomer(Customer customer, UriComponentsBuilder uriBuilder) {
        Customer customerSave = customerRepository.save(customer);
        CustomerDTO dto = customerMapper.customerToCustomerDTO(customerSave);
        URI uri = uriBuilder.path("v1/customers/{id}").buildAndExpand(customerSave.getId()).toUri();
        return ResponseEntity.created(uri).body(dto);
    }

    public ResponseEntity<CustomerDTO> findCustomer(Long id) {
        Customer customer = customerRepository.getReferenceById(id);
        CustomerDTO dto = customerMapper.customerToCustomerDTO(customer);
        return ResponseEntity.ok(dto);
    }
}
