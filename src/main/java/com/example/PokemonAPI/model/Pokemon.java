package com.example.PokemonAPI.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


/**
 * Entidad que representa un Pokemon almacenado en MySQL.
 * Hibernate utiliza estos campos para construir la tabla pokemon.
 * Lombok genera automaticamente los métodos de acceso y constructores.
 */
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Pokemon {

    // Identificador único; MySQL asigna su valor automáticamente.
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombre;
    private String tipo;
    private Integer nivel;
    private Integer ataque;
    private Integer defensa;
    private String descripcion;
    private String imagenUrl;
}
