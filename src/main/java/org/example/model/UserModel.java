package org.example.model;

/*
 * UserModel: Essa classe armazena os dados que serão
 * armazenados na tabela de usuários do banco de dados.
 *
 * Cada entidade dentro da classe irá gerar uma coluna
 * da tabela de usuários no PostgresSQL.
 *
 * Caso seja necessário adicionar mais algum elemento,
 * seja input ou gerado, realize-o neste arquivo.
 */

import java.time.LocalDateTime;
import java.util.UUID;
import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import lombok.Data;

@Data                                                   // Recurso do Lombok que auxilia com Getters e Setters
@Entity(name="tb_users")                                // Nome da tabela que será criada na database
public class UserModel {

    // Será gerado um ID do tipo UUID para cada usuário registrado
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    // A seção a seguir será de entradas, os inputs recebidos no cadastro do usuário

    // Como o username deve ser único para cada usuário criado, essa coluna será configurada
    // para que não receba valores repetidos e nem nulos.
    @Column(unique = true, nullable = false)
    private String username;

    // Para os demais inputs, iremos admitir que as informações sejam repetidas,
    // mas que não sejam nulas.
    @Column(nullable = false)
    private String name;
    private String password;

    // Por fim, será registrado a data e horário que cada usuário foi criado/armazenado
    // no nosso banco de dados.
    @CreationTimestamp
    private LocalDateTime createdAt;

    @ManyToOne
    @JoinColumn(name = "pokemon1_id", referencedColumnName = "id")
    private PokemonModel pokemon1; // Primeiro Pokémon escolhido

    @ManyToOne
    @JoinColumn(name = "pokemon2_id", referencedColumnName = "id")
    private PokemonModel pokemon2; // Segundo Pokémon escolhido

    @ManyToOne
    @JoinColumn(name = "pokemon3_id", referencedColumnName = "id")
    private PokemonModel pokemon3; // Terceiro Pokémon escolhido

    public PokemonModel getPokemon2() {
        return pokemon2;
    }

    public void setPokemon2(PokemonModel pokemon2) {
        this.pokemon2 = pokemon2;
    }

    public PokemonModel getPokemon1() {
        return pokemon1;
    }

    public void setPokemon1(PokemonModel pokemon1) {
        this.pokemon1 = pokemon1;
    }

    public PokemonModel getPokemon3() {
        return pokemon3;
    }

    public void setPokemon3(PokemonModel pokemon3) {
        this.pokemon3 = pokemon3;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}