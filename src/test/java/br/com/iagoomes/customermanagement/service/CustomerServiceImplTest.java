package br.com.iagoomes.customermanagement.service;


import br.com.iagoomes.customermanagement.domain.customer.CustomerDTO;
import br.com.iagoomes.customermanagement.controller.mapper.CustomerMapper;
import br.com.iagoomes.customermanagement.domain.customer.Customer;
import br.com.iagoomes.customermanagement.domain.customer.CustomerRepository;
import br.com.iagoomes.customermanagement.domain.customer.impl.CustomerServiceImpl;
import br.com.iagoomes.customermanagement.domain.customer.validation.impl.CustomerValidationImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mapstruct.factory.Mappers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.util.UriComponentsBuilder;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Objects;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CustomerServiceImplTest {
    @InjectMocks
    private CustomerServiceImpl customerService;

    @Mock
    private CustomerRepository customerRepository;

    @Spy
    private CustomerMapper customerMapper = Mappers.getMapper(CustomerMapper.class);
    @Mock
    private CustomerValidationImpl customerValidation;
    private CustomerDTO carlos;
    private CustomerDTO ana;

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
        this.ana = CustomerDTO.builder()
                .fullName("Ana Julia")
                .countryOfBirth("Brazil")
                .cpf("124.456.789-08")
                .passport("ATCD223456")
                .dateOfBirth(LocalDateTime.parse("2023-01-01T00:00:00"))
                .addressInCountryOfBirth("Rua ABC, 123")
                .phone("1234567890")
                .email("Ana.julia@example.com")
                .build();
    }

    @Test
    @DisplayName("Deve permitir cadastrar o cliente")
    void createCustomerTest() {
        // Arrange
        UriComponentsBuilder uriBuilder = UriComponentsBuilder.newInstance();
        when(customerRepository.save(any(Customer.class))).thenAnswer(i -> i.getArgument(0));
        doNothing().when(customerValidation).isValid(any());

        // Act
        ResponseEntity<CustomerDTO> response = customerService.createCustomer(carlos, uriBuilder);

        // Assert
        assertThat(response.getBody()).isInstanceOf(CustomerDTO.class).isNotNull();
        assertThat(Objects.requireNonNull(response.getBody()).getFullName()).isEqualTo(this.carlos.getFullName());
        verify(customerRepository, times(1)).save(any(Customer.class));
    }

    @Test
    @DisplayName("Deve permitir buscar cliente")
    void findCustomerTest() {
        // Arrange
        Optional<Customer> optionalCustomer = Optional.of(customerMapper.customerDTOToCustomer(carlos));
        when(customerRepository.findActiveByid(anyLong())).thenReturn(optionalCustomer);

        // Act
        ResponseEntity<CustomerDTO> response = customerService.findCustomer(1L);

        // Assert
        assertThat(response.getBody()).isInstanceOf(CustomerDTO.class).isNotNull();
        assertThat(Objects.requireNonNull(response.getBody()).getFullName()).isEqualTo(this.carlos.getFullName());
        verify(customerRepository, times(1)).findActiveByid(anyLong());
    }

    @Test
    @DisplayName("Deve permitir listar os clientes")
    void findCustomersTest() {
        // Arrange
        Customer carlosModel = customerMapper.customerDTOToCustomer(this.carlos);
        Customer anaModel = customerMapper.customerDTOToCustomer(this.ana);
        Page<Customer> page = new PageImpl<>(Arrays.asList(carlosModel, anaModel));
        when(customerRepository.findAllByActiveIsTrue(any(Pageable.class))).thenReturn(page);

        // Act
        ResponseEntity<Page<CustomerDTO>> response = customerService.findCustomers(Pageable.unpaged());

        // Assert
        assertThat(Objects.requireNonNull(response.getBody()).getContent()).hasSize(2);
        verify(customerRepository, times(1)).findAllByActiveIsTrue(any(Pageable.class));
    }

    @Test
    @DisplayName("Deve permitir alterar o cliente")
    void updateCustomerTest() {
        // Arrange
        Customer carlosModel = customerMapper.customerDTOToCustomer(this.carlos);
        when(customerRepository.getReferenceById(anyLong())).thenReturn(carlosModel);
        CustomerDTO carlosUpdate = CustomerDTO.builder().fullName("Carlinhos").build();
        // Act
        ResponseEntity<CustomerDTO> response = customerService.updateCustomer(1L, carlosUpdate);

        // Assert
        assertThat(response.getBody()).isInstanceOf(CustomerDTO.class).isNotNull();
        assertThat(Objects.requireNonNull(response.getBody()).getFullName()).isEqualTo(carlosUpdate.getFullName());
        verify(customerRepository, times(1)).getReferenceById(anyLong());
    }

    @Test
    @DisplayName("Deve permitir deletar o cliente")
    void deleteCustomerTest() {
        // Arrange
        Customer carlosModel = customerMapper.customerDTOToCustomer(this.carlos);
        when(customerRepository.getReferenceById(anyLong())).thenReturn(carlosModel);

        // Act
        ResponseEntity<Void> response = customerService.deleteCustomer(1L);

        // Assert
        assertThat(response.getStatusCode().value()).isEqualTo(204);
        assertThat(carlosModel.getActive()).isFalse();
        verify(customerRepository, times(1)).getReferenceById(anyLong());
    }
}