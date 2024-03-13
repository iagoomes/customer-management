package br.com.iagoomes.customermanagement.service;

import br.com.iagoomes.customermanagement.model.Customer;
import br.com.iagoomes.customermanagement.repository.CustomerRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.util.Optional;

@Service
public class CustomerService {
    private CustomerRepository customerRepository;

    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public ResponseEntity<String> createCustomer(Customer model) {
        Customer customerSave = customerRepository.save(model);
        if (customerSave.getId() == null){
            throw new RuntimeException("Customer dont saved");
        }
        return ResponseEntity.ok("Sucesso");
    }

    public ResponseEntity<Customer> findCustomer(Long id) {
        Optional<Customer> byId = customerRepository.findById(id);
        return ResponseEntity.ok(byId.get());
    }
}
