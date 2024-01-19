package com.example.teste.model;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.*;

@Data
@RequiredArgsConstructor
@AllArgsConstructor
@NoArgsConstructor
@Embeddable
public class Address {

    @NonNull
    @Column(nullable = false)
    private String rua;

    @NonNull
    @Column(nullable = false)
    private String numero;

    private String complemento;

    @NonNull
    @Column(nullable = false)
    private String bairro;

    @NonNull
    @Column(nullable = false)
    private String cidade;

    @NonNull
    @Column(nullable = false)
    private String estado;

    @NonNull
    @Column(nullable = false)
    private String cep;


}