package org.example.controller;

import org.example.model.PokemonModel;
import org.example.service.PokemonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/pokemon")
public class PokemonController {

    @Autowired
    private PokemonService pokemonService;

    @PostMapping("/{id}")
    public PokemonModel fetchAndSavePokemon(@PathVariable int id) {
        return pokemonService.fetchAndSavePokemon(id);
    }
}
