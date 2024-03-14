package br.com.iagoomes.customermanagement.service.impl;

import br.com.iagoomes.customermanagement.dto.CustomerDTO;
import br.com.iagoomes.customermanagement.exceptions.CustomerNotFound;
import br.com.iagoomes.customermanagement.mapper.CustomerMapper;
import br.com.iagoomes.customermanagement.model.Customer;
import br.com.iagoomes.customermanagement.repository.CustomerRepository;
import br.com.iagoomes.customermanagement.service.CustomerService;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;

@Service
public class CustomerServiceImpl implements CustomerService {
    private final CustomerRepository customerRepository;
    private final CustomerMapper customerMapper;

    public CustomerServiceImpl(CustomerRepository customerRepository, CustomerMapper customerMapper) {
        this.customerRepository = customerRepository;
        this.customerMapper = customerMapper;
    }

    @Override
    @Transactional
    public ResponseEntity<CustomerDTO> createCustomer(CustomerDTO dto, UriComponentsBuilder uriBuilder) {
        Customer customer = customerMapper.customerDTOToCustomer(dto);
        Customer customerSave = customerRepository.save(customer);
        CustomerDTO response = customerMapper.customerToCustomerDTO(customerSave);
        URI uri = uriBuilder.path("v1/customers/{id}").buildAndExpand(customerSave.getId()).toUri();
        return ResponseEntity.created(uri).body(response);
    }

    @Override
    public ResponseEntity<CustomerDTO> findCustomer(Long id) {
        Customer customer = customerRepository.findActiveByid(id)
                .orElseThrow(() -> new CustomerNotFound("Resource not found or deleted"));
        CustomerDTO dto = customerMapper.customerToCustomerDTO(customer);
        return ResponseEntity.ok(dto);
    }

    @Override
    public ResponseEntity<Page<CustomerDTO>> findCustomers(Pageable page) {
        Page<Customer> customerPage = customerRepository.findAllByActiveIsTrue(page);

        List<CustomerDTO> dtos = customerPage.getContent()
                .stream()
                .map(customerMapper::customerToCustomerDTO)
                .toList();

        return ResponseEntity.ok(new PageImpl<>(dtos, page, customerPage.getTotalElements()));
    }

    @Override
    @Transactional
    public ResponseEntity<CustomerDTO> updateCustomer(Long id, CustomerDTO dto) {
        Customer customer = customerRepository.getReferenceById(id);
        customer.updateInfos(dto);
        CustomerDTO customerDTO = customerMapper.customerToCustomerDTO(customer);
        return ResponseEntity.ok(customerDTO);
    }

    @Override
    @Transactional
    public ResponseEntity<Void> deleteCustomer(Long id) {
        Customer customer = customerRepository.getReferenceById(id);
        customer.desativar();
        return ResponseEntity.noContent().build();
    }


}
