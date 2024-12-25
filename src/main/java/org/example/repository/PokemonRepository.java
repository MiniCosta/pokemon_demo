package org.example.repository;

import org.example.model.PokemonModel;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.UUID;

public interface PokemonRepository extends JpaRepository<PokemonModel, UUID> {

}
