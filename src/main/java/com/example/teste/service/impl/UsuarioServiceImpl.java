package com.example.teste.service.impl;

import com.example.teste.model.User;
import com.example.teste.repository.UserRepository;
import com.example.teste.service.UsuarioService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsuarioServiceImpl implements UsuarioService {

    @Autowired
    private UserRepository userRepository;
    @Override
    public void addUser(User user) {
        userRepository.save(user);
    }

    @Override
    public List<User> getUsers() {
       return userRepository.findAll();
    }

    @Override
    public User getUser(String cpf) {
        return userRepository.findByCpf(cpf);
    }


}
