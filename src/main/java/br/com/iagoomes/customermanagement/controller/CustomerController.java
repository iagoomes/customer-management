package br.com.iagoomes.customermanagement.controller;


import br.com.iagoomes.customermanagement.dto.CustomerDTO;
import br.com.iagoomes.customermanagement.mapper.CustomerMapper;
import br.com.iagoomes.customermanagement.model.Customer;
import br.com.iagoomes.customermanagement.service.CustomerService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("v1")
public class CustomerController {

    private final CustomerService customerService;
    private final CustomerMapper customerMapper;
    public CustomerController(CustomerService customerService, CustomerMapper customerMapper) {
        this.customerService = customerService;
        this.customerMapper = customerMapper;
    }

    @PostMapping("/customers")
    public ResponseEntity<CustomerDTO> createCustomer(@RequestBody CustomerDTO dto, UriComponentsBuilder uriBuilder) {
        Customer model = customerMapper.customerDTOToCustomer(dto);
        return customerService.createCustomer(model, uriBuilder);
    }

    @GetMapping("/customers/{id}")
    public ResponseEntity<CustomerDTO> getCustomer(@PathVariable Long id) {
        return customerService.findCustomer(id);
    }
}
