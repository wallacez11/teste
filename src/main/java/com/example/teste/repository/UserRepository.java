package com.example.teste.repository;

import com.example.teste.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UserRepository extends JpaRepository<User, Long> {

    @Query("SELECT u FROM User u WHERE u.cpf = :cpf")
    User findByCpf(@Param("cpf") String cpf);
}
