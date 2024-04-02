package es.rest.tarea.controllers;

import es.rest.tarea.models.Code;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

@Tag(name = "CodeController", description = "Controlador para operaciones relacionadas con códigos")
@RestController
@RequestMapping("/api/v1")
public class CodeController {

    @Operation(summary = "Obtiene todos los códigos")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de códigos encontrada exitosamente",
                    content = @Content(schema = @Schema(implementation = Code.class)))
    })
    @GetMapping("/codes")
    public Iterable<Code> getCodes() {
        // Implementar lógica para obtener todos los códigos
        return null;
    }

    @Operation(summary = "Obtiene un código por su ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Código encontrado exitosamente",
                    content = @Content(schema = @Schema(implementation = Code.class))),
            @ApiResponse(responseCode = "404", description = "Código no encontrado")
    })
    @GetMapping("/codes/{id}")
    public Code getCodeById(@PathVariable Long id) {
        // Implementar lógica para obtener un código por ID
        return null;
    }

    @Operation(summary = "Crea un nuevo código")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Código creado exitosamente",
                    content = @Content(schema = @Schema(implementation = Code.class)))
    })
    @PostMapping("/codes")
    public Code createCode(@RequestBody Code code) {
        // Implementar lógica para crear un nuevo código
        return code;
    }

    @Operation(summary = "Actualiza un código por su ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Código actualizado exitosamente",
                    content = @Content(schema = @Schema(implementation = Code.class)))
    })
    @PutMapping("/codes/{id}")
    public Code updateCode(@PathVariable Long id, @RequestBody Code code) {
        // Implementar lógica para actualizar un código por ID
        return code;
    }

    @Operation(summary = "Elimina un código por su ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Código eliminado exitosamente"),
            @ApiResponse(responseCode = "404", description = "Código no encontrado")
    })
    @DeleteMapping("/codes/{id}")
    public void deleteCode(@PathVariable Long id) {
        // Implementar lógica para eliminar un código por ID
    }

}
