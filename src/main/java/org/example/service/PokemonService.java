package org.example.service;

import org.example.model.PokemonModel;
import org.example.details.PokemonDetails;
import org.example.repository.PokemonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class PokemonService {

    private static final String BASE_URL = "https://pokeapi.co/api/v2/pokemon/";

    @Autowired
    private PokemonRepository pokemonRepository;

    public PokemonModel fetchAndSavePokemon(int pokemonId) {
        RestTemplate restTemplate = new RestTemplate();
        String url = BASE_URL + pokemonId;

        // Faz a requisição à PokeAPI
        PokemonDetails details = restTemplate.getForObject(url, PokemonDetails.class);

        if (details == null) {
            throw new RuntimeException("Erro ao buscar Pokémon com ID: " + pokemonId);
        }

        // Mapear os dados para o modelo Pokemon
        PokemonModel pokemonModel = new PokemonModel();
        pokemonModel.setPokemonId(details.id); // Acessa diretamente o campo id
        pokemonModel.setName(details.name); // Acessa diretamente o campo name

        // Extrai o tipo principal
        if (!details.types.isEmpty()) {
            pokemonModel.setType(details.types.get(0).type.name); // Acessa diretamente o tipo
        }

        // Extrai HP e Ataque
        if (details.stats.size() > 1) {
            pokemonModel.setHealth(details.stats.get(0).baseStat); // HP
            pokemonModel.setAttack(details.stats.get(1).baseStat); // Ataque
        }

        // Salvar no banco de dados
        return pokemonRepository.save(pokemonModel);
    }
}


