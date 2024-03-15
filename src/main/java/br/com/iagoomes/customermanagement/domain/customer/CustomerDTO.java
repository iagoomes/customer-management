package br.com.iagoomes.customermanagement.domain.customer;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.*;
import org.hibernate.validator.constraints.br.CPF;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CustomerDTO {
    @NotBlank
    private String fullName;

    @NotBlank
    private String countryOfBirth;

    @NotBlank
    @CPF
    private String cpf;

    @NotBlank
    private String passport;

    @NotNull
    private LocalDateTime dateOfBirth;

    @NotBlank
    private String addressInCountryOfBirth;

    @NotBlank
    @Pattern(regexp="\\d{10,12}")
    private String phone;

    @NotBlank
    @Email
    private String email;
}
