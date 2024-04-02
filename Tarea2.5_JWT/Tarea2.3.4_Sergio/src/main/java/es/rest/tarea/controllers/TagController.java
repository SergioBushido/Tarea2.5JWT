package es.rest.tarea.controllers;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "TagController", description = "Controlador para operaciones relacionadas con etiquetas (tags)")
@RestController
@AllArgsConstructor
@CrossOrigin(origins = "*")
@RequestMapping("/api/v1/tags")
public class TagController {

    @Operation(summary = "Obtiene todas las etiquetas")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de etiquetas encontrada exitosamente",
                    content = @Content(schema = @Schema(implementation = Tag.class)))
    })
    @GetMapping
    public List<Tag> getAllTags() {
        // Implementar lógica para obtener todas las etiquetas
        return null;  // Simulación de respuesta, reemplazar con lógica real
    }

    @Operation(summary = "Obtiene una etiqueta por su ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Etiqueta encontrada exitosamente",
                    content = @Content(schema = @Schema(implementation = Tag.class))),
            @ApiResponse(responseCode = "404", description = "Etiqueta no encontrada")
    })
    @GetMapping("/{id}")
    public Tag getTagById(@PathVariable Long id) {
        // Implementar lógica para obtener una etiqueta específica por ID
        return null;  // Simulación de respuesta, reemplazar con lógica real
    }

    @Operation(summary = "Crea una nueva etiqueta")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Etiqueta creada exitosamente",
                    content = @Content(schema = @Schema(implementation = Tag.class)))
    })
    @PostMapping
    public Tag createTag(@RequestBody Tag tag) {
        // Implementar lógica para crear una nueva etiqueta
        return tag;  // Simulación de respuesta, reemplazar con lógica real
    }

    @Operation(summary = "Actualiza una etiqueta por su ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Etiqueta actualizada exitosamente",
                    content = @Content(schema = @Schema(implementation = Tag.class)))
    })
    @PutMapping("/{id}")
    public Tag updateTag(@PathVariable Long id, @RequestBody Tag updatedTag) {
        // Implementar lógica para actualizar una etiqueta por su ID
        return updatedTag;  // Simulación de respuesta, reemplazar con lógica real
    }

    @Operation(summary = "Elimina una etiqueta por su ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Etiqueta eliminada exitosamente"),
            @ApiResponse(responseCode = "404", description = "Etiqueta no encontrada")
    })
    @DeleteMapping("/{id}")
    public void deleteTag(@PathVariable Long id) {
        // Implementar lógica para eliminar una etiqueta por su ID
    }
}

