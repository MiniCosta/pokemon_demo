package org.example.controller;

import org.example.model.PokemonModel;
import org.example.repository.PokemonRepository;
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

    //Endpoint para obter 5 Pokémon aleatórios
    @GetMapping("/random")                           //GET http://localhost:8080/pokemon/random
    public List<PokemonModel> getFiveRandomPokemon() {

        return pokemonService.getFiveRandomPokemon(); // O metódo está em PokemonService
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


    //POST - Criar um ou mais Pokémon
    @PostMapping
    public List<PokemonModel> saveChosenPokemon(@RequestBody List<PokemonModel> chosenPokemon) {
        return pokemonRepository.saveAll(chosenPokemon);
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