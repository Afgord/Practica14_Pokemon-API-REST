package com.example.PokemonAPI.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.URL;

import java.time.LocalDate;


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

    @NotBlank(message = "El nombre no puede ser vacío.")
    @Size(min = 2, max = 50, message = "El nombre debe tener entre 2 y 50 caracteres.")
    private String nombre;

    @NotBlank(message = "El tipo no puede ser vacío.")
    private String tipo;

    @NotNull(message = "El nivel es requerido.")
    @Min(value=1, message = "El nivel mínimo es 1.")
    @Max(value=100, message = "El nivel máximo es 100.")
    private Integer nivel;

    @NotNull(message = "El ataque es requerido.")
    @Min(value = 0, message = "El ataque no puede ser negativo")
    private Integer ataque;

    @NotNull(message = "La defensa es requerida.")
    @Min(value = 0, message = "La defensa no puede ser negativa")
    private Integer defensa;

    @Size(max=255, message = "La longitud de la descripción no puede exceder los 255 caracteres.")
    private String descripcion;

    @URL(message = "La URL de la imagen debe tener un formato válido")
    private String imagenUrl;

    @Email(message = "El correo debe tener un formato válido.")
    private String correoEntrenador;

    @PastOrPresent(message = "La fecha de nacimiento no puede ser mayor a la actual.")
    private LocalDate fechaNacimiento;

    @Positive(message = "El peso del pokemon debe ser mayor a 0")
    private Double peso;


    @Pattern(
            regexp = "^(\\+\\d{1,2}\\s?)?\\(?\\d{3}\\)?[\\s.-]?\\d{3}[\\s.-]?\\d{4}$",
            message = "El formato del teléfono no es válido."
    )
    private String telefonoEntrenador;


    @AssertTrue(message = "El pokemon debe estar sanito.")
    private Boolean estaSano;
}
