
package com.example.PokemonAPI.service;

import com.example.PokemonAPI.repository.PokemonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.PokemonAPI.model.Pokemon;
import java.util.List;
import java.util.Optional;
import java.util.Map;

/**
 * Capa de servicio: concentra las operaciones CRUD y comunica
 * el controlador con la capa de persistencia.
 */
@Service
public class PokemonService {

    // Spring inyecta el repositorio; no creamos la instancia con new.
    @Autowired
    private PokemonRepository pokemonRepository;


    /** Obtiene todos los Pokemon almacenados mediante findAll(). */
    public List<Pokemon> obtenerTodos() {
        return pokemonRepository.findAll();
    }

    /**
     * Busca un Pokemon mediante su id.
     *
     * Utilizamos Optional porque no podemos garantizar
     * que el registro solicitado exista en la base de datos.
     * De esta manera, el controlador podrá responder con
     * un código HTTP 404 cuando no encuentre al Pokemon.
     */
    public Optional<Pokemon> obtenerPorId(Long id) {
        return pokemonRepository.findById(id);
    }

    /**
     * Fuerza un alta nueva al descartar un ID recibido del cliente.
     * La BD asignará el identificador autoincrementable.
     */
    public Pokemon crear(Pokemon pokemon) {
        pokemon.setId(null);
        return pokemonRepository.save(pokemon);
    }

    /**
     * PUT: reemplaza todos los atributos editables del registro existente.
     * No modifica el ID. Si no existe, devuelve Optional.empty().
     */
    public Optional<Pokemon> actualizar(Long id, Pokemon datos) {
        return pokemonRepository.findById(id).map(pokemon -> {
            pokemon.setNombre(datos.getNombre());
            pokemon.setTipo(datos.getTipo());
            pokemon.setNivel(datos.getNivel());
            pokemon.setAtaque(datos.getAtaque());
            pokemon.setDefensa(datos.getDefensa());
            pokemon.setDescripcion(datos.getDescripcion());
            pokemon.setImagenUrl(datos.getImagenUrl());

            return pokemonRepository.save(pokemon);
        });
    }

    /**
     * Actualiza solamente los atributos enviados por el cliente.
     *
     * A diferencia de PUT, PATCH no necesita recibir todos los
     * datos del Pokemon. Primero recuperamos el registro original
     * y despues verificamos que campos fueron enviados.
     *
     * Los atributos que no aparezcan en el JSON conservarán
     * sus valores actuales.
     */
    public Optional<Pokemon> actualizarParcial(
            Long id, Map<String, Object> cambios) {

        return pokemonRepository.findById(id).map(pokemon -> {

            if (cambios.containsKey("nombre")) {
                pokemon.setNombre((String) cambios.get("nombre"));
            }

            if (cambios.containsKey("tipo")) {
                pokemon.setTipo((String) cambios.get("tipo"));
            }

            if (cambios.containsKey("nivel")) {
                pokemon.setNivel((Integer) cambios.get("nivel"));
            }

            if (cambios.containsKey("ataque")) {
                pokemon.setAtaque((Integer) cambios.get("ataque"));
            }

            if (cambios.containsKey("defensa")) {
                pokemon.setDefensa((Integer) cambios.get("defensa"));
            }

            if (cambios.containsKey("descripcion")) {
                pokemon.setDescripcion(
                        (String) cambios.get("descripcion"));
            }

            if (cambios.containsKey("imagenUrl")) {
                pokemon.setImagenUrl(
                        (String) cambios.get("imagenUrl"));
            }

            return pokemonRepository.save(pokemon);
        });
    }

    /**
     * DELETE: realiza una eliminación física. Devuelve false si el ID
     * no existe, para que el controlador pueda responder 404.
     */
    public boolean eliminar(Long id) {
        if (!pokemonRepository.existsById(id)) {
            return false;
        }

        pokemonRepository.deleteById(id);
        return true;
    }

}
