package br.com.iagoomes.customermanagement.controller;

import br.com.iagoomes.customermanagement.dto.CustomerDTO;
import br.com.iagoomes.customermanagement.model.Customer;
import br.com.iagoomes.customermanagement.service.CustomerService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("v1")
public class CustomerController {

    private CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @PostMapping("/customers")
    public ResponseEntity<String> createCustomer(@RequestBody CustomerDTO dto) {
        Customer model = dto.toModel(dto);
        return customerService.createCustomer(model);
    }

    @GetMapping("/customers/{id}")
    public ResponseEntity<Customer> getCustomer(@PathVariable Long id) {
        return customerService.findCustomer(id);
    }
}
