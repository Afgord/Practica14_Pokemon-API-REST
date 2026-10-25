
package com.example.PokemonAPI.repository;

import com.example.PokemonAPI.model.Pokemon;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Acceso a datos de Pokemon. JpaRepository ya incluye consultas
 * y operaciones CRUD, por eso no necesitamos escribir SQL.
 */
public interface PokemonRepository extends JpaRepository<Pokemon, Long> {

}
