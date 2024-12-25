package org.example.repository;

import org.example.model.PokemonModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.nio.channels.FileChannel;
import java.util.List;
import java.util.UUID;

@Repository
public interface PokemonRepository extends JpaRepository<PokemonModel, String> {

//    List<PokemonModel> findByName(String name);
//    List<PokemonModel> findById(String id);

}
