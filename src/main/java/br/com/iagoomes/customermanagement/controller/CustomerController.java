package br.com.iagoomes.customermanagement.controller;


import br.com.iagoomes.customermanagement.dto.CustomerDTO;
import br.com.iagoomes.customermanagement.service.impl.CustomerServiceImpl;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("v1")
public class CustomerController {

    private final CustomerServiceImpl customerServiceImpl;

    public CustomerController(CustomerServiceImpl customerServiceImpl) {
        this.customerServiceImpl = customerServiceImpl;
    }

    @PostMapping("/customers")
    public ResponseEntity<CustomerDTO> createCustomer(@RequestBody CustomerDTO dto, UriComponentsBuilder uriBuilder) {
        return customerServiceImpl.createCustomer(dto, uriBuilder);
    }

    @GetMapping("/customers/{id}")
    public ResponseEntity<CustomerDTO> findCustomer(@PathVariable Long id) {
        return customerServiceImpl.findCustomer(id);
    }

    @GetMapping("/customers")
    public ResponseEntity<Page<CustomerDTO>> findCustomers(Pageable page) {
        return customerServiceImpl.findCustomers(page);
    }

    @PutMapping("/customers/{id}")
    public ResponseEntity<CustomerDTO> updateCustomer(@PathVariable Long id, @RequestBody CustomerDTO dto) {
        return customerServiceImpl.updateCustomer(id, dto);
    }

    @DeleteMapping("/customers/{id}")
    @Transactional
    public ResponseEntity<Void> deleteCustomer(@PathVariable Long id) {
        return customerServiceImpl.deleteCustomer(id);
    }
}
