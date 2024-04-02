package es.rest.tarea.controllers;

import es.rest.tarea.models.auth.User;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "UserController", description = "Controlador para operaciones relacionadas con usuarios")
@RestController
@AllArgsConstructor
@CrossOrigin(origins = "*")
@RequestMapping("/api/v1/users")
public class UserController {

    @Operation(summary = "Obtiene todos los usuarios")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de usuarios encontrada exitosamente",
                    content = @Content(schema = @Schema(implementation = User.class)))
    })
    @GetMapping
    public List<User> getAllUsers() {
        // Implementar lógica para obtener todos los usuarios
        return null;  // Simulación de respuesta, reemplazar con lógica real
    }

    @Operation(summary = "Obtiene un usuario por su ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Usuario encontrado exitosamente",
                    content = @Content(schema = @Schema(implementation = User.class))),
            @ApiResponse(responseCode = "404", description = "Usuario no encontrado")
    })
    @GetMapping("/{id}")
    public User getUserById(@PathVariable Long id) {
        // Implementar lógica para obtener un usuario específico por ID
        return null;  // Simulación de respuesta, reemplazar con lógica real
    }

    @Operation(summary = "Crea un nuevo usuario")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Usuario creado exitosamente",
                    content = @Content(schema = @Schema(implementation = User.class)))
    })
    @PostMapping
    public User createUser(@RequestBody User user) {
        // Implementar lógica para crear un nuevo usuario
        return user;  // Simulación de respuesta, reemplazar con lógica real
    }

    @Operation(summary = "Actualiza un usuario por su ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Usuario actualizado exitosamente",
                    content = @Content(schema = @Schema(implementation = User.class)))
    })
    @PutMapping("/{id}")
    public User updateUser(@PathVariable Long id, @RequestBody User updatedUser) {
        // Implementar lógica para actualizar un usuario por su ID
        return updatedUser;  // Simulación de respuesta, reemplazar con lógica real
    }

    @Operation(summary = "Elimina un usuario por su ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Usuario eliminado exitosamente"),
            @ApiResponse(responseCode = "404", description = "Usuario no encontrado")
    })
    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable Long id) {
        // Implementar lógica para eliminar un usuario por su ID
    }
}
