package br.com.iagoomes.customermanagement.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "customers")
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotBlank
    private String fullName;

    @NotBlank
    private String countryOfBirth;

    @NotBlank
    @Pattern(regexp="[0-9]{11}")
    private String cpf;

    private String passport;

    @NotNull
    private LocalDateTime dateOfBirth;

    @NotBlank
    private String addressInCountryOfBirth;

    @NotBlank
    @Pattern(regexp="[0-9]{10,12}")
    private String phone;

    @NotBlank
    @Email
    private String email;
}
