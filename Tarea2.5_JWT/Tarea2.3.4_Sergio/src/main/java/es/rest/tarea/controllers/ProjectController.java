package es.rest.tarea.controllers;

import es.rest.tarea.models.Project;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "ProjectController", description = "Controlador para operaciones relacionadas con proyectos")
@RestController
@AllArgsConstructor
@CrossOrigin(origins = "*")
@RequestMapping("/api/v1/projects")
public class ProjectController {

    @Operation(summary = "Obtiene todos los proyectos")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de proyectos encontrada exitosamente",
                    content = @Content(schema = @Schema(implementation = Project.class)))
    })
    @GetMapping
    public List<Project> getAllProjects() {
        // Implementar lógica para obtener todos los proyectos
        return null;  // Simulación de respuesta, reemplazar con lógica real
    }

    @Operation(summary = "Obtiene un proyecto por su ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Proyecto encontrado exitosamente",
                    content = @Content(schema = @Schema(implementation = Project.class))),
            @ApiResponse(responseCode = "404", description = "Proyecto no encontrado")
    })
    @GetMapping("/{id}")
    public Project getProjectById(@PathVariable Long id) {
        // Implementar lógica para obtener un proyecto por su ID
        return null;  // Simulación de respuesta, reemplazar con lógica real
    }

    @Operation(summary = "Crea un nuevo proyecto")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Proyecto creado exitosamente",
                    content = @Content(schema = @Schema(implementation = Project.class)))
    })
    @PostMapping
    public Project createProject(@RequestBody Project project) {
        // Implementar lógica para crear un nuevo proyecto
        return project;  // Simulación de respuesta, reemplazar con lógica real
    }

    @Operation(summary = "Actualiza un proyecto por su ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Proyecto actualizado exitosamente",
                    content = @Content(schema = @Schema(implementation = Project.class)))
    })
    @PutMapping("/{id}")
    public Project updateProject(@PathVariable Long id, @RequestBody Project updatedProject) {
        // Implementar lógica para actualizar un proyecto por su ID
        return updatedProject;  // Simulación de respuesta, reemplazar con lógica real
    }

    @Operation(summary = "Elimina un proyecto por su ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Proyecto eliminado exitosamente"),
            @ApiResponse(responseCode = "404", description = "Proyecto no encontrado")
    })
    @DeleteMapping("/{id}")
    public void deleteProject(@PathVariable Long id) {
        // Implementar lógica para eliminar un proyecto por su ID
    }
}
