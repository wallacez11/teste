package com.example.teste.controller;

import com.example.teste.model.User;
import com.example.teste.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UsuarioController {

    @Autowired
    private UsuarioService userService;

    @PostMapping("/create")
    public String addUser(@RequestBody User user){
        userService.addUser(user);

        return "Usuario adicionado com sucesso";
    }

    @GetMapping("/getAll")
    public List<User> getAllUsers(){
       return userService.getUsers();
    }

    @GetMapping("/getByCpf")
    public User getUserByCpf(@RequestParam String cpf){
        return userService.getUser(cpf);
    }
}
