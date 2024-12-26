package org.example.repository;

import org.example.model.PokemonModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface PokemonRepository extends JpaRepository<PokemonModel, String> {

}
