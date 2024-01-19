package com.example.teste.controller;

import com.example.teste.model.StatusRegistro;
import com.example.teste.model.User;
import com.example.teste.service.UsuarioService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/service")
@RequiredArgsConstructor
public class UsuarioController {


    private UsuarioService userService;

    @PostMapping("/create")
    public String addUser(@RequestBody User user){
        userService.addUser(user);

        return "Usuario adicionado com sucesso";
    }

    @GetMapping("/users")
    public List<User> getAllUsers(){
       return userService.getUsers();
    }

    @PutMapping("/update/{cpf}")
    public ResponseEntity<User> updateUser(@PathVariable String cpf, @RequestBody User user){
        userService.updateUser(cpf,user);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/user")
    public ResponseEntity<User> getUserByCpf(@RequestParam String cpf){
        return Optional
                .ofNullable( userService.getUser(cpf) )
                .map( user -> ResponseEntity.ok().body(user) )
                .orElseGet( () -> ResponseEntity.notFound().build() );


    }


    @DeleteMapping("/delete/{cpf}")
    public ResponseEntity<Void> deleteUser(@PathVariable String cpf){
        userService.deleteUser(cpf);
        return ResponseEntity.noContent().build();
    }
}
