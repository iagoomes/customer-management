package br.com.iagoomes.customermanagement.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class FormError extends DefaultError {
    private final List<ValidationField> messages = new ArrayList<>();

    public void addMenssagens(String campo, String mensagem) {
        messages.add(new ValidationField(campo, mensagem));
    }
}
