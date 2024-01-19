package com.example.teste.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.SourceType;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDate;
import java.time.LocalDateTime;



@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String cpf;

    @Column(nullable = false)
    private String nome;

    @Column(nullable = true)
    private LocalDate dataNascimento;

    @Embedded
    private  Address endereco;

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