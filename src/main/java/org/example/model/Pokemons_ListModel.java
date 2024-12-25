package org.example.model;

import jakarta.persistence.*;

@Entity
@Table(name = "pokemons_list")
public class Pokemons_ListModel {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "pokemon_seq")
    @SequenceGenerator(name = "pokemon_seq", sequenceName = "pokemon_seq", allocationSize = 1)
    private String id; // ID sequencial, começando em 1

    @Column(unique = true, nullable = false)
    private int pokemonId; // ID do Pokémon da PokeAPI

    @Column(nullable = false)
    private String name; // Nome do Pokémon

    @Column(nullable = false)
    private String type; // Tipo principal do Pokémon

    @Column(nullable = false)
    private int health; // Pontos de vida (HP)

    @Column(nullable = false)
    private int attack; // Valor de ataque

    @Column(nullable = false, length = 512)
    private String sprite; // URL do sprite (imagem)

    // Getters e Setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getPokemonId() {
        return pokemonId;
    }

    public void setPokemonId(int pokemonId) {
        this.pokemonId = pokemonId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public int getAttack() {
        return attack;
    }

    public void setAttack(int attack) {
        this.attack = attack;
    }

    public String getSprite() {
        return sprite;
    }

    public void setSprite(String sprite) {
        this.sprite = sprite;
    }
}
