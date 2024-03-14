package br.com.iagoomes.customermanagement.mapper;

import br.com.iagoomes.customermanagement.dto.CustomerDTO;
import br.com.iagoomes.customermanagement.model.Customer;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CustomerMapper {
    CustomerDTO customerToCustomerDTO(Customer customer);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "active", ignore = true)
    Customer customerDTOToCustomer(CustomerDTO dto);
}
