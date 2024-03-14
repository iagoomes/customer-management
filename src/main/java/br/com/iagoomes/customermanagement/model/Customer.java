package br.com.iagoomes.customermanagement.model;

import br.com.iagoomes.customermanagement.dto.CustomerDTO;
import jakarta.persistence.*;
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
@Entity(name = "Customer")
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
    @CPF
    private String cpf;

    private String passport;

    @NotNull
    private LocalDateTime dateOfBirth;

    @NotBlank
    private String addressInCountryOfBirth;

    @NotBlank
    @Pattern(regexp = "\\d{10,12}")
    private String phone;

    @NotBlank
    @Email
    private String email;

    @Builder.Default
    private Boolean active = true;

    public void updateInfos(CustomerDTO dto) {
        if (dto.getFullName() != null) {
            this.setFullName(dto.getFullName());
        }
        if (dto.getCountryOfBirth() != null) {
            this.setCountryOfBirth(dto.getCountryOfBirth());
        }
        if (dto.getCpf() != null) {
            this.setCpf(dto.getCpf());
        }
        if (dto.getPassport() != null) {
            this.setPassport(dto.getPassport());
        }
        if (dto.getDateOfBirth() != null) {
            this.setDateOfBirth(dto.getDateOfBirth());
        }
        if (dto.getAddressInCountryOfBirth() != null) {
            this.setAddressInCountryOfBirth(dto.getAddressInCountryOfBirth());
        }
        if (dto.getPhone() != null) {
            this.setPhone(dto.getPhone());
        }
        if (dto.getEmail() != null) {
            this.setEmail(dto.getEmail());
        }
    }

    public void desativar() {
        this.active = false;
    }
}
