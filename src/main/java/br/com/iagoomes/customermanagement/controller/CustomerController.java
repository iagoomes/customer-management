package br.com.iagoomes.customermanagement.controller;


import br.com.iagoomes.customermanagement.domain.customer.CustomerDTO;
import br.com.iagoomes.customermanagement.domain.customer.impl.CustomerServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping(value = "v1", produces = "application/json")
@Tag(name = "customer")
public class CustomerController {

    private final CustomerServiceImpl customerServiceImpl;

    public CustomerController(CustomerServiceImpl customerServiceImpl) {
        this.customerServiceImpl = customerServiceImpl;
    }

    @Operation(summary = "Realiza o cadastro do cliente", method = "POST")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Cadastro de cliente realizado com sucesso"),
            @ApiResponse(responseCode = "400", description = "Corpo da requisição contem tributo invalido"),
            @ApiResponse(responseCode = "500", description = "Erro Interno")
    })
    @PostMapping(value = "/customers", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CustomerDTO> createCustomer(@RequestBody @Valid CustomerDTO dto, UriComponentsBuilder uriBuilder) {
        return customerServiceImpl.createCustomer(dto, uriBuilder);
    }

    @Operation(summary = "Realiza a busca do cliente", method = "GET")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "A busca do cliente realizada com sucesso"),
            @ApiResponse(responseCode = "404", description = "Cliente não encontrado"),
            @ApiResponse(responseCode = "500", description = "Erro Interno")
    })
    @GetMapping("/customers/{id}")
    public ResponseEntity<CustomerDTO> findCustomer(@PathVariable Long id) {
        return customerServiceImpl.findCustomer(id);
    }

    @Operation(summary = "Realiza a busca dos clientes", method = "GET")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "A busca do cliente realizada com sucesso"),
            @ApiResponse(responseCode = "500", description = "Erro Interno")
    })
    @GetMapping("/customers")
    public ResponseEntity<Page<CustomerDTO>> findCustomers(@RequestParam(value = "page", defaultValue = "0") int page,
                                                           @RequestParam(value = "size", defaultValue = "10") int size) {
        Pageable pageable = PageRequest.of(page, size);
        return customerServiceImpl.findCustomers(pageable);
    }

    @Operation(summary = "Realiza a atualização do cliente", method = "PUT")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Alteração realizada com sucesso"),
            @ApiResponse(responseCode = "404", description = "Cliente não encontrado ou deletado"),
            @ApiResponse(responseCode = "500", description = "Erro Interno")
    })
    @PutMapping(value = "/customers/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CustomerDTO> updateCustomer(@PathVariable Long id, @RequestBody @Valid CustomerDTO dto) {
        return customerServiceImpl.updateCustomer(id, dto);
    }

    @Operation(summary = "Realiza a atualização do cliente", method = "PUT")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Alteração realizada com sucesso"),
            @ApiResponse(responseCode = "404", description = "Cliente não encontrado ou deletado"),
            @ApiResponse(responseCode = "500", description = "Erro Interno")
    })
    @DeleteMapping("/customers/{id}")
    @Transactional
    public ResponseEntity<Void> deleteCustomer(@PathVariable Long id) {
        return customerServiceImpl.deleteCustomer(id);
    }
}
