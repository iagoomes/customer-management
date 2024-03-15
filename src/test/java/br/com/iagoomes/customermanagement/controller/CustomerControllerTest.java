package br.com.iagoomes.customermanagement.controller;

import br.com.iagoomes.customermanagement.domain.customer.Customer;
import br.com.iagoomes.customermanagement.domain.customer.CustomerDTO;
import br.com.iagoomes.customermanagement.domain.customer.impl.CustomerServiceImpl;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.AutoConfigureJsonTesters;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.time.LocalDateTime;
import java.util.Collections;

import static org.hamcrest.Matchers.empty;
import static org.hamcrest.Matchers.not;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureJsonTesters
class CustomerControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private CustomerServiceImpl customerService;
    private CustomerDTO carlos;
    private Customer carlosModel;

    @BeforeEach
    void setUp() {
        this.carlos = CustomerDTO.builder()
                .fullName("Carlos")
                .countryOfBirth("Brazil")
                .cpf("123.456.789-09")
                .passport("ABCD123456")
                .dateOfBirth(LocalDateTime.parse("2023-01-01T00:00:00"))
                .addressInCountryOfBirth("Rua ABC, 123")
                .phone("1234567890")
                .email("john.doe@example.com")
                .build();
        this.carlosModel = Customer.builder()
                .id(1L)
                .fullName("Carlos")
                .countryOfBirth("Brazil")
                .cpf("123.456.789-09")
                .passport("ABCD123456")
                .dateOfBirth(LocalDateTime.parse("2023-01-01T00:00:00"))
                .addressInCountryOfBirth("Rua ABC, 123")
                .phone("1234567890")
                .email("john.doe@example.com")
                .build();
    }

    @Test
    void createCustomerTest() throws Exception {
        // Arrange
        UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromHttpUrl("http://localhost:8080");
        URI uri = uriBuilder.path("v1/customers/{id}").buildAndExpand("1").toUri();
        when(customerService.createCustomer(any(CustomerDTO.class), any(UriComponentsBuilder.class)))
                .thenReturn(ResponseEntity.created(uri).body(this.carlos));

        // Act & Assert
        mockMvc.perform(MockMvcRequestBuilders
                        .post("/v1/customers")
                        .content(asJsonString(carlos))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated());
        verify(customerService, times(1))
                .createCustomer(any(CustomerDTO.class), any(UriComponentsBuilder.class));
    }

    @Test
    void findCustomer() throws Exception {
        // Arrange
        when(customerService.findCustomer(anyLong()))
                .thenReturn(ResponseEntity.ok(this.carlos));

        // Act & Assert
        mockMvc.perform(MockMvcRequestBuilders
                        .get("/v1/customers/{id}", this.carlosModel.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
        verify(customerService, times(1))
                .findCustomer(anyLong());
    }

    @Test
    void findCustomers() throws Exception {
        // Arrange
        Page<CustomerDTO> page = new PageImpl<>(Collections.singletonList(carlos));
        when(customerService.findCustomers(any(Pageable.class)))
                .thenReturn(ResponseEntity.ok(page));

        // Act & Assert
        mockMvc.perform(get("/v1/customers")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content", not(empty())))
                .andExpect(jsonPath("$.totalPages").value(1));
    }

    @Test
    void updateCustomer() throws Exception {
        // Arrange
        when(customerService.updateCustomer(anyLong(), any(CustomerDTO.class)))
                .thenReturn(ResponseEntity.ok(carlos));

        // Act & Assert
        mockMvc.perform(put("/v1/customers/{id}", this.carlosModel.getId())
                        .content(asJsonString(carlos))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    void deleteCustomer() throws Exception {
        // Arrange
        when(customerService.deleteCustomer(anyLong()))
                .thenReturn(ResponseEntity.noContent().build());

        // Act & Assert
        mockMvc.perform(delete("/v1/customers/{id}", this.carlosModel.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());
    }
    private static String asJsonString(Object object) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.findAndRegisterModules();
        return objectMapper.writeValueAsString(object);
    }
}