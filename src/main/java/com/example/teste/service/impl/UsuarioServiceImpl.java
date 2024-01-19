package com.example.teste.service.impl;

import com.example.teste.model.StatusRegistro;
import com.example.teste.model.User;
import com.example.teste.repository.UserRepository;
import com.example.teste.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class UsuarioServiceImpl implements UsuarioService {


    private UserRepository userRepository;
    @Override
    public void addUser(User user) {
        userRepository.save(user);
    }

    @Override
    public List<User> getUsers() {
        return userRepository.findByStatus(StatusRegistro.ATIVO);
    }

    @Override
    public User getUser(String cpf) {
        return userRepository.findByCpf(cpf);
    }

    @Override
    public void updateUser(String cpf, User user) {

        Optional<User> optionalUser  = Optional.ofNullable(userRepository.findByCpf(cpf));

        if (optionalUser.isPresent()) {
            User updateUser = optionalUser.get();
            updateUser.setNome(user.getNome());
            updateUser.setDataNascimento(user.getDataNascimento());
            updateUser.setEndereco(user.getEndereco());
            updateUser.setStatus(user.getStatus());
            updateUser.setDataHoraAtualizacao(LocalDateTime.now());
            updateUser.setUsuarioAtualizacao(user.getUsuarioAtualizacao());
            userRepository.save(updateUser);
        }else{
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuario não encontrado");
        }


    }

    @Override
    public void deleteUser(String cpf) {
        Optional<User> optionalUser  = Optional.ofNullable(userRepository.findByCpf(cpf));

        if (optionalUser.isPresent()) {
            User updateUser = optionalUser.get();
            updateUser.setStatus(StatusRegistro.REMOVIDO);

            userRepository.save(updateUser);
        }else{
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuario não encontrado");
        }
    }

}
