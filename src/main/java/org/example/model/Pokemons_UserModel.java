package org.example.model;


import jakarta.persistence.*;
import lombok.Data;
import lombok.ToString;

import java.util.UUID;

@Entity
@Data
@Table
@ToString
public class Pokemons_UserModel {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID )
    private UUID id;

    @ManyToOne
    @JoinColumn(name="id_usuario")
    private UserModel userModel;

    @ManyToOne
    @JoinColumn(name="id_pokemon")
    private PokemonModel pokemonModel;
}
