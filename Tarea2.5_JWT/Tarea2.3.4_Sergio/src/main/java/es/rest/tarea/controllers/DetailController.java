package es.rest.tarea.controllers;

import es.rest.tarea.models.Detail;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

@Tag(name = "DetailController", description = "Controlador para operaciones relacionadas con detalles")
@RestController
@RequestMapping("/api/v1/details")
public class DetailController {

    @Operation(summary = "Obtiene todos los detalles")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de detalles encontrada exitosamente",
                    content = @Content(schema = @Schema(implementation = Detail.class)))
    })
    @GetMapping
    public Iterable<Detail> getAllDetails() {
        // Implementar lógica para obtener todos los detalles
        return null;
    }

    @Operation(summary = "Obtiene un detalle por su ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Detalle encontrado exitosamente",
                    content = @Content(schema = @Schema(implementation = Detail.class))),
            @ApiResponse(responseCode = "404", description = "Detalle no encontrado")
    })
    @GetMapping("/{id}")
    public Detail getDetailById(@PathVariable Long id) {
        // Implementar lógica para obtener un detalle por ID
        return null;
    }

    @Operation(summary = "Crea un nuevo detalle")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Detalle creado exitosamente",
                    content = @Content(schema = @Schema(implementation = Detail.class)))
    })
    @PostMapping
    public Detail createDetail(@RequestBody Detail detail) {
        // Implementar lógica para crear un nuevo detalle
        return detail;
    }

    @Operation(summary = "Actualiza un detalle por su ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Detalle actualizado exitosamente",
                    content = @Content(schema = @Schema(implementation = Detail.class)))
    })
    @PutMapping("/{id}")
    public Detail updateDetail(@PathVariable Long id, @RequestBody Detail detail) {
        // Implementar lógica para actualizar un detalle por ID
        return detail;
    }

    @Operation(summary = "Elimina un detalle por su ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Detalle eliminado exitosamente"),
            @ApiResponse(responseCode = "404", description = "Detalle no encontrado")
    })
    @DeleteMapping("/{id}")
    public void deleteDetail(@PathVariable Long id) {
        // Implementar lógica para eliminar un detalle por ID
    }
}
