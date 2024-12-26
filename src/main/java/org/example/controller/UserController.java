package org.example.controller;

import org.example.model.UserModel;
import org.example.repository.UserRepository;
import org.example.repository.PokemonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import at.favre.lib.crypto.bcrypt.BCrypt;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/users")              // Onde faremos as requisições (POST)
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PokemonRepository pokemonRepository;

    @PostMapping("/")               // Utilizaremos o "/" após o "/users" para nossas requisições
    //http://localhost:8090/users/
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

    @PostMapping("/choose") //Adiciona entre 1 e 3 pokemons no tabela users
    public UserModel saveChosenPokemon(@RequestParam String userId, @RequestBody List<String> chosenPokemonIds) { //Requer como parametros o Id do user e no body uma lista com os Ids dos Pokemon
        if (chosenPokemonIds.size() > 3) {
            throw new IllegalArgumentException("Você pode escolher no máximo 3 Pokémon.");
        }

        // Buscar o usuário existente
        UserModel user = userRepository.findById(UUID.fromString(userId))
                .orElseThrow(() -> new IllegalArgumentException("Usuário com ID " + userId + " não encontrado."));

        // Buscar os Pokémon pelo ID e associar ao usuário
        if (chosenPokemonIds.size() >= 1) {
            user.setPokemon1(pokemonRepository.findById(chosenPokemonIds.get(0))
                    .orElseThrow(() -> new IllegalArgumentException("Pokémon com ID " + chosenPokemonIds.get(0) + " não encontrado.")));
        }

        if (chosenPokemonIds.size() >= 2) {
            user.setPokemon2(pokemonRepository.findById(chosenPokemonIds.get(1))
                    .orElseThrow(() -> new IllegalArgumentException("Pokémon com ID " + chosenPokemonIds.get(1) + " não encontrado.")));
        }

        if (chosenPokemonIds.size() == 3) {
            user.setPokemon3(pokemonRepository.findById(chosenPokemonIds.get(2))
                    .orElseThrow(() -> new IllegalArgumentException("Pokémon com ID " + chosenPokemonIds.get(2) + " não encontrado.")));
        }

        // Salvar o usuário na tabela 'users'
        return userRepository.save(user);
    }

}
