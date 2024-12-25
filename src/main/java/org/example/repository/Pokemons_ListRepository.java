package org.example.repository;

import org.example.model.Pokemons_ListModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface Pokemons_ListRepository extends JpaRepository<Pokemons_ListModel, Long> {
    // Métodos padrão de CRUD já disponíveis.
}
