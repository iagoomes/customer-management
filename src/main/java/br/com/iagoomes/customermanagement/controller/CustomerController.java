package br.com.iagoomes.customermanagement.controller;


import br.com.iagoomes.customermanagement.dto.CustomerDTO;
import br.com.iagoomes.customermanagement.service.CustomerService;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("v1")
public class CustomerController {

    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @PostMapping("/customers")
    public ResponseEntity<CustomerDTO> createCustomer(@RequestBody CustomerDTO dto, UriComponentsBuilder uriBuilder) {
        return customerService.createCustomer(dto, uriBuilder);
    }

    @GetMapping("/customers/{id}")
    public ResponseEntity<CustomerDTO> findCustomer(@PathVariable Long id) {
        return customerService.findCustomer(id);
    }

    @GetMapping("/customers")
    public ResponseEntity<Page<CustomerDTO>> findCustomers(Pageable page) {
        return customerService.findCustomers(page);
    }

    @PutMapping("/customers/{id}")
    public ResponseEntity<CustomerDTO> updateCustomer(@PathVariable Long id, @RequestBody CustomerDTO dto) {
        return customerService.updateCustomer(id, dto);
    }

    @DeleteMapping("/customers/{id}")
    @Transactional
    public ResponseEntity<Void> deleteCustomer(@PathVariable Long id) {
        return customerService.deleteCustomer(id);
    }
}
