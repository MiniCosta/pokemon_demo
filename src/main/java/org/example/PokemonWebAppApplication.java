package org.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class PokemonWebAppApplication {

    public static void main(String[] args) {
        SpringApplication.run(PokemonWebAppApplication.class, args);
        System.out.println("Pokemon Web App está rodando!");
    }
}