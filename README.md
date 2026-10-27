
# Práctica 14 - Pokémon API REST

- Christian Lafayette Martinez
- 00000010383
- Aplicaciones Web
- LuMi 18:00~19:30 hrs
- Mtro. Martín Guadalupe Bernal Lugo

## Descripción

Esta práctica consiste en desarrollar una **API REST**
utilizando Spring Boot y MySQL para administrar Pokémon.

## Tecnologías utilizadas

- Java 21
- Spring Boot
- Spring Data JPA
- MySQL
- Lombok
- Postman

## Objetivo

Implementar las operaciones CRUD mediante métodos HTTP.
## Endpoints de la API

| Método | Endpoint | Descripción |
|--------|----------|-------------|
| GET | `/api/pokemones` | Listar todos los Pokémon |
| GET | `/api/pokemones/{id}` | Buscar un Pokémon por ID |
| POST | `/api/pokemones` | Registrar un Pokémon |
| PUT | `/api/pokemones/{id}` | Actualizar todos sus atributos |
| PATCH | `/api/pokemones/{id}` | Actualizar atributos específicos |
| DELETE | `/api/pokemones/{id}` | Eliminar un Pokémon |

### Ejemplo: registrar un Pokémon

**POST** `/api/pokemones`

```json
{
  "nombre": "Pikachu",
  "tipo": "Electrico",
  "nivel": 10,
  "ataque": 55,
  "defensa": 40,
  "descripcion": "Pokemon de tipo electrico",
  "imagenUrl": "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/25.png"
}
```

**Respuesta esperada:** `201 Created`.


## Configuración de la base de datos

La aplicación utiliza MySQL para almacenar los registros
de Pokémon de manera persistente mediante Spring Data JPA.

**Base de datos:** `pokemon_api_db`

**Puerto de MySQL:** `3306`

### Variables de entorno

Por seguridad, las credenciales no se almacenan
directamente en `application.properties`.

Se utilizan las siguientes variables:

| Variable | Descripción |
|----------|-------------|
| `DB_USERNAME` | Usuario de MySQL |
| `DB_PASSWORD` | Contraseña del usuario |
| `DB_HOST` | Servidor MySQL (localhost por defecto) |
| `DB_PORT` | Puerto MySQL (3306 por defecto) |

### Configuración de Spring Boot

```properties
spring.datasource.url=jdbc:mysql://${DB_HOST:localhost}:${DB_PORT:3306}/pokemon_api_db?createDatabaseIfNotExist=true
spring.datasource.username=${DB_USERNAME}
spring.datasource.password=${DB_PASSWORD}
spring.jpa.hibernate.ddl-auto=update
```

La propiedad `ddl-auto=update` permite que Hibernate
genere o actualice las tablas automáticamente.

Para ejecutar la aplicación, el usuario de MySQL debe
contar con los permisos necesarios sobre `pokemon_api_db`.



## Ejecución del proyecto

### Requisitos previos

- Java 21.
- MySQL 8.x en ejecución.
- Maven Wrapper incluido en el proyecto.
- Usuario MySQL con permisos sobre `pokemon_api_db`.

### Linux (Fedora, Ubuntu y otras distribuciones)

Desde una terminal Bash, ubicarse en el proyecto:

```bash
cd PokemonAPI
```

Configurar las variables de entorno:

```bash
export DB_USERNAME=pokemon_app
read -rsp "Contraseña de MySQL: " DB_PASSWORD
echo
export DB_PASSWORD
```

Compilar y ejecutar las pruebas:

```bash
./mvnw clean verify
```

Iniciar Spring Boot:

```bash
./mvnw spring-boot:run
```

### Windows (PowerShell)

Abrir PowerShell en la carpeta del proyecto.

Configurar las variables de entorno:

```powershell
$env:DB_USERNAME = "pokemon_app"
$securePassword = Read-Host "Contraseña de MySQL" -AsSecureString
$env:DB_PASSWORD = [System.Net.NetworkCredential]::new("", $securePassword).Password
Remove-Variable securePassword
```

Compilar y ejecutar las pruebas:

```powershell
.\mvnw.cmd clean verify
```

Iniciar Spring Boot:

```powershell
.\mvnw.cmd spring-boot:run
```

### Acceso a la API

Una vez iniciada la aplicación, estará disponible en:

`http://localhost:8080/api/pokemones`

Las variables de entorno deben estar disponibles
en la terminal desde la cual se ejecuta Maven.

También es posible ejecutar el proyecto desde
IntelliJ IDEA configurando las variables de entorno
en Run → Edit Configurations.


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
