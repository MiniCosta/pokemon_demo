package org.example.repository;

/*
 * IUserRepository: Esta interface nos auxiliará a administrar
 * os usuários cadastrados, facilitando o manejo de cenários
 * diversos (como retornar erro ao tentar registrar um usuário
 * já existente).
 */

import java.util.UUID;
import org.example.model.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserModel, UUID> {
    UserModel findByUsername(String username);
}