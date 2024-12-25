package org.example.service;

import org.example.details.PokemonDetails;
import org.example.model.PokemonModel;
import org.example.repository.PokemonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Service
public class PokemonService {

    private static final String BASE_URL = "https://pokeapi.co/api/v2/pokemon/";
    private static final int MAX_POKEMON_ID = 100; // Limite do ID de Pokémon na API

    @Autowired
    private PokemonRepository pokemonRepository;

    private final RestTemplate restTemplate = new RestTemplate();


    //Gera 5 Pokémon aleatórios
    public List<PokemonModel> getFiveRandomPokemon() {
        List<PokemonModel> randomPokemonList = new ArrayList<>();
        Random random = new Random();

        for (int i = 0; i < 5; i++) {
            int randomId = random.nextInt(MAX_POKEMON_ID) + 1; // Gera números entre 1 e MAX_POKEMON_ID
            PokemonDetails details = restTemplate.getForObject(BASE_URL + randomId, PokemonDetails.class);

            if (details != null) {
                PokemonModel pokemon = mapDetailsToPokemon(details);
                randomPokemonList.add(pokemon);
            }
        }
        return randomPokemonList;
    }


    //Salva os 3 Pokémon escolhidos pelo usuário.
    public List<PokemonModel> saveChosenPokemon(List<PokemonModel> chosenPokemon) {
        return pokemonRepository.saveAll(chosenPokemon);
    }


    //Mapeia os detalhes do Pokémon para o modelo Pokémon.
    private PokemonModel mapDetailsToPokemon(PokemonDetails details) {
        PokemonModel pokemon = new PokemonModel();
        pokemon.setPokemonId(details.id);
        pokemon.setName(details.name);

        if (!details.types.isEmpty()) {
            pokemon.setType(details.types.get(0).type.name);
        }

        if (details.stats.size() > 1) {
            pokemon.setHealth(details.stats.get(0).baseStat); // HP
            pokemon.setAttack(details.stats.get(1).baseStat); // Ataque
        }

        if (details.sprites != null && details.sprites.frontDefault != null) {
            pokemon.setSprite(details.sprites.frontDefault); // URL da imagem
        }
        return pokemon;
    }
}
