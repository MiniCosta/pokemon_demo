package org.example.details;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class PokemonDetails {

    public int id; // ID do Pokémon
    public String name; // Nome do Pokémon

    @JsonProperty("types")
    public List<TypeWrapper> types; // Lista de tipos do Pokémon

    @JsonProperty("stats")
    public List<StatWrapper> stats; // Lista de estatísticas do Pokémon

    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class TypeWrapper {
        public Type type;

        @JsonIgnoreProperties(ignoreUnknown = true)
        public static class Type {
            public String name; // Nome do tipo (ex: "grass")
        }
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class StatWrapper {
        @JsonProperty("base_stat")
        public int baseStat; // Valor da estatística (ex: HP ou ataque)

        @JsonProperty("stat")
        public Stat stat;

        @JsonIgnoreProperties(ignoreUnknown = true)
        public static class Stat {
            public String name; // Nome da estatística (ex: "attack")
        }
    }
}

