package com.example.teste.dto;

import com.example.teste.model.Address;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UsersResponse {
    private String cpf;
    private String nome;
    private LocalDate dataNascimento;
    private Address endereco;

    // construtores, getters e setters


}
