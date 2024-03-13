package br.com.iagoomes.customermanagement.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class CustomerDTO {
    @NotBlank
    private String fullName;

    @NotBlank
    private String countryOfBirth;

    @NotBlank
    private String cpf;

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
