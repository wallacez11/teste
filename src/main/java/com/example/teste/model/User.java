package com.example.teste.model;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.SourceType;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.validator.constraints.br.CPF;

import java.time.LocalDate;
import java.time.LocalDateTime;



@Data
@AllArgsConstructor
@RequiredArgsConstructor
@NoArgsConstructor
@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NonNull
    @Column(unique = true, nullable = false)
    private String cpf;

    @NonNull
    @Column(nullable = false)
    private String nome;

    @NonNull
    @Column(nullable = true)
    private LocalDate dataNascimento;

    @NonNull
    @Embedded
    @Column(nullable = false)
    private  Address endereco;

    @NonNull
    @Column(nullable = false)
    private StatusRegistro status = StatusRegistro.ATIVO;

    @CreationTimestamp(source = SourceType.DB)
    @Column(nullable = true, updatable = false)
    private LocalDateTime dataHoraCriacao;

    @Column(nullable = true)
    private String usuarioCriacao;

    @Column(nullable = true)
    private LocalDateTime dataHoraAtualizacao;

    @UpdateTimestamp(source = SourceType.DB)
    @Column(nullable = true)
    private String usuarioAtualizacao;

    @Column(nullable = true)
    private LocalDateTime dataHoraRemocao;

    @Column(nullable = true)
    private String usuarioRemocao;


}