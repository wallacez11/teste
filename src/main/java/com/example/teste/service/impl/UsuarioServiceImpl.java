package com.example.teste.service.impl;

import com.example.teste.config.JwtService;
import com.example.teste.dto.UsersResponse;
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

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JwtService jwtService;
    @Override
    public void addUser(User user) {

        String name = jwtService.returnUsername();
        user.setUsuarioCriacao(name);
        userRepository.save(user);
    }

    @Override
    public List<UsersResponse> getUsers() {
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
            String name = jwtService.returnUsername();
            updateUser.setNome(user.getNome());
            updateUser.setDataNascimento(user.getDataNascimento());
            updateUser.setEndereco(user.getEndereco());
            updateUser.setStatus(user.getStatus());
            updateUser.setDataHoraAtualizacao(LocalDateTime.now());
            updateUser.setUsuarioAtualizacao(user.getUsuarioAtualizacao());
            updateUser.setUsuarioAtualizacao(name);
            userRepository.save(updateUser);
        }else{
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuario não encontrado");
        }


    }

    @Override
    public void deleteUser(String cpf) {
        Optional<User> optionalUser  = Optional.ofNullable(userRepository.findByCpf(cpf));

        if (optionalUser.isPresent()) {
            String name = jwtService.returnUsername();

            User updateUser = optionalUser.get();
            updateUser.setStatus(StatusRegistro.REMOVIDO);
            updateUser.setUsuarioRemocao(name);
            updateUser.setDataHoraRemocao(LocalDateTime.now());

            userRepository.save(updateUser);
        }else{
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuario não encontrado");
        }
    }

}
