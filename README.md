
# Práctica 14 - Pokémon API REST

- Christian Lafayette Martinez
- 00000010383
- Aplicaciones Web
- LuMi 18:00~19:30 hrs
- Mtro. Martín Guadalupe Bernal Lugo

## Práctica 15 - Validaciones

Esta práctica amplía la API REST de Pokémon desarrollada en la
Práctica 14, incorporando validaciones de datos y manejo
centralizado de excepciones.

### Validaciones implementadas

Se utiliza Jakarta Bean Validation para comprobar:

- Campos obligatorios mediante `@NotBlank` y `@NotNull`.
- Longitudes de texto mediante `@Size`.
- Rangos numéricos mediante `@Min`, `@Max` y `@Positive`.
- Formato de correo electrónico mediante `@Email`.
- Formato de URL mediante `@URL`.
- Fechas mediante `@PastOrPresent`.
- Formato de teléfono mediante `@Pattern`.
- Valores booleanos mediante `@AssertTrue`.

Las validaciones se activan con `@Valid` en los métodos
POST y PUT del controlador.

### Manejo de errores

Se implementó un `GlobalExceptionHandler` para devolver
respuestas JSON estructuradas mediante `ErrorDTO`.

El DTO contiene:

- `mensaje`: descripción general del error.
- `detalles`: lista de errores específicos.
- `fecha`: fecha y hora en que ocurrió el error.

Se manejan respuestas HTTP 400, 404, 422 y 500.

### Evidencias

Las capturas se encuentran organizadas en:

- `Postman/Practica14/`: pruebas CRUD originales.
- `Postman/Practica15/`: pruebas de validaciones,
  recurso inexistente y verificación en MySQL.
