package org.example.controller;

import org.example.model.UserModel;
import org.example.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import at.favre.lib.crypto.bcrypt.BCrypt;

@RestController
@RequestMapping("/users")              // Onde faremos as requisições (POST)
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @PostMapping("/")            //usar /add?   // Utilizaremos o "/" após o "/users" para nossas requisições
    // http://localhost:8090/users/
    public ResponseEntity create(@RequestBody UserModel userModel) {
        var user = this.userRepository.findByUsername(userModel.getUsername());

        if (user != null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Este usuário já existe");        // Retorna um Bad Request se o usuário informado já estiver em nosso banco de dados
        }

        var passwordHashed = BCrypt.withDefaults().hashToString(12, userModel.getPassword().toCharArray());         // Criptografa a senha informada usando o BCrypt e a armazena na variável passwordHashed

        userModel.setPassword(passwordHashed);                                      // Informamos que a senha que queremos armazenar é a que foi criptografada

        var userCreated = this.userRepository.save(userModel);                      // Armazena os dados recebidos no banco de dados
        return ResponseEntity.status(HttpStatus.CREATED).body(userCreated);         // Retorna que o usuário foi criado e as informações armazenadas
        // (id, username, nome, senha criptografada, data e hora de criação)
    }
}
