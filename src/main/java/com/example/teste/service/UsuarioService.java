package com.example.teste.service;

import com.example.teste.dto.UsersResponse;
import com.example.teste.model.StatusRegistro;
import com.example.teste.model.User;
import java.util.List;


public interface UsuarioService {
    void addUser(User user);

    List<UsersResponse> getUsers();

    User getUser(String cpf);

    void updateUser(String cpf, User user);

    void deleteUser(String cpf);
}
