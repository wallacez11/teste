package com.example.teste.repository;

import com.example.teste.dto.UsersResponse;
import com.example.teste.model.StatusRegistro;
import com.example.teste.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {

    @Query("SELECT u FROM User u WHERE u.cpf = :cpf")
    User findByCpf(@Param("cpf") String cpf);

    @Query("SELECT new com.example.teste.dto.UsersResponse(u.cpf, u.nome, u.dataNascimento, u.endereco) FROM User u WHERE u.status = :status")
    List<UsersResponse> findByStatus(@Param("status")StatusRegistro status);
};

