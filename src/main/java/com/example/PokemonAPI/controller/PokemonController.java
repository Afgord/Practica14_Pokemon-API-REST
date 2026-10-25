
package com.example.PokemonAPI.controller;

import com.example.PokemonAPI.model.Pokemon;
import com.example.PokemonAPI.service.PokemonService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * Expone los endpoints REST bajo /api/pokemones.
 * Recibe y devuelve JSON y utiliza codigos HTTP adecuados.
 */
@RestController
@RequestMapping("/api/pokemones")
public class PokemonController {

    @Autowired
    private PokemonService pokemonService;

    // GET colección: devuelve 200 incluso si la lista esta vacía.
    @GetMapping
    public ResponseEntity<List<Pokemon>> obtenerTodos() {
        return ResponseEntity.ok(pokemonService.obtenerTodos());
    }

    // GET individual: devuelve 404 si el identificador no existe.
    @GetMapping("/{id}")
    public ResponseEntity<Pokemon> obtenerPorId(@PathVariable Long id) {
        return pokemonService.obtenerPorId(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // POST: recibe el JSON y responde 201 cuando se crea el recurso.
    @PostMapping
    public ResponseEntity<Pokemon> crear(@RequestBody Pokemon pokemon) {
        Pokemon nuevoPokemon = pokemonService.crear(pokemon);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(nuevoPokemon);
    }

    // PUT: actualizacion completa de todos los campos editables.
    @PutMapping("/{id}")
    public ResponseEntity<Pokemon> actualizar(
            @PathVariable Long id,
            @RequestBody Pokemon pokemon) {

        return pokemonService.actualizar(id, pokemon)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // PATCH: recibe solo los campos enviados en el cuerpo JSON.
    @PatchMapping("/{id}")
    public ResponseEntity<Pokemon> actualizarParcial(
            @PathVariable Long id,
            @RequestBody Map<String, Object> cambios) {

        return pokemonService.actualizarParcial(id, cambios)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // DELETE: 204 sin cuerpo al eliminar; 404 si no existe.
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {

        if (pokemonService.eliminar(id)) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.notFound().build();
    }

}
