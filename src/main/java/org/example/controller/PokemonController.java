package org.example.controller;

import org.example.model.PokemonModel;
import org.example.repository.PokemonRepository;
import org.example.repository.UserRepository;
import org.example.service.PokemonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/pokemon")
public class PokemonController {

    @Autowired
    private PokemonService pokemonService;

    @Autowired
    private PokemonRepository pokemonRepository;

    @Autowired
    private UserRepository userRepository;


    //Endpoint para obter 5 Pokémon aleatórios.
    @GetMapping("/random")                     // GET http://localhost:8080/api/pokemon/random
    public List<PokemonModel> getFiveRandomPokemon() {

        return pokemonService.getFiveRandomPokemon(); // O metódo está em PokemonService
    }

    //Endpoint para salvar 3 Pokémon escolhidos pelo usuário.
    @PostMapping("/choose")
    //Salva os 3 Pokémon escolhidos pelo usuário.
    public List<PokemonModel> saveChosenPokemon(List<PokemonModel> chosenPokemon) {
        return pokemonRepository.saveAll(chosenPokemon);
    }

    /**
     * CRUD
     */

    //GET - Obter todos os Pokémon
    @GetMapping
    public List<PokemonModel> getAllPokemon() {
        return pokemonRepository.findAll();
    }


    //GET - Obter um Pokémon pelo ID
    @GetMapping("/{id}")
    public Optional<PokemonModel> getPokemonById(@PathVariable String id) {

        return pokemonRepository.findById(id);
    }


    //POST - Criar um novo Pokémon
    @PostMapping
    public PokemonModel createPokemon(@RequestBody PokemonModel pokemon) {
        return pokemonRepository.save(pokemon);
    }


    //PUT - Atualizar um Pokémon existente pelo ID
    @PutMapping("/{id}")
    public PokemonModel updatePokemon(@PathVariable String id, @RequestBody PokemonModel updatedPokemon) {
        return pokemonRepository.findById(id)
                .map(pokemon -> {
                    pokemon.setName(updatedPokemon.getName());
                    pokemon.setType(updatedPokemon.getType());
                    pokemon.setHealth(updatedPokemon.getHealth());
                    pokemon.setAttack(updatedPokemon.getAttack());
                    pokemon.setSprite(updatedPokemon.getSprite());
                    return pokemonRepository.save(pokemon);
                })
                .orElseThrow(() -> new IllegalArgumentException("Pokémon com ID " + id + " não encontrado."));
    }

    //DELETE - Excluir um Pokémon pelo ID
    @DeleteMapping("/{id}")
    public void deletePokemon(@PathVariable String id) {
        pokemonRepository.deleteById(id);
    }

}