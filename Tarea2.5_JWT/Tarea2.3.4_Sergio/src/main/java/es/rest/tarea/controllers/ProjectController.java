package es.rest.tarea.controllers;

import es.rest.tarea.models.Project;
import es.rest.tarea.models.dto.ProjectDto;
import es.rest.tarea.services.ProjectService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Tag(name = "ProjectController", description = "Controlador para operaciones relacionadas con proyectos")
@RestController
@AllArgsConstructor
@CrossOrigin(origins = "*")
@RequestMapping("/api/v1/projects")
public class ProjectController {

    private final ProjectService projectService;

    @Operation(summary = "Obtiene todos los proyectos")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de proyectos encontrada exitosamente",
                    content = @Content(schema = @Schema(implementation = Project.class)))
    })
    @GetMapping
    public ResponseEntity<List<Project>> getAllProjects() {
        List<Project> projects = projectService.findAll();
        return ResponseEntity.ok(projects);
    }

    @Operation(summary = "Obtiene un proyecto por su ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Proyecto encontrado exitosamente",
                    content = @Content(schema = @Schema(implementation = Project.class))),
            @ApiResponse(responseCode = "404", description = "Proyecto no encontrado")
    })
    @GetMapping("/{id}")
    public ResponseEntity<Project> getProjectById(@PathVariable Long id) {
        Optional<Project> project = projectService.findById(id);
        return project.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @Operation(summary = "Crea un nuevo proyecto")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Proyecto creado exitosamente",
                    content = @Content(schema = @Schema(implementation = Project.class)))
    })
    @PostMapping
    public ResponseEntity<Project> createProject(@RequestBody ProjectDto projectDto) {
        Project savedProject = projectService.create(projectDto);
        return new ResponseEntity<>(savedProject, HttpStatus.CREATED);
    }
    @Operation(summary = "Actualiza un proyecto por su ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Proyecto actualizado exitosamente",
                    content = @Content(schema = @Schema(implementation = Project.class))),
            @ApiResponse(responseCode = "404", description = "Proyecto no encontrado")
    })
    @PutMapping("/{id}")
    public ResponseEntity<Project> updateProject(@PathVariable Long id, @RequestBody ProjectDto projectDto) {
        Optional<Project> updatedProject = projectService.update(id, projectDto);
        return updatedProject.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @Operation(summary = "Elimina un proyecto por su ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Proyecto eliminado exitosamente"),
            @ApiResponse(responseCode = "404", description = "Proyecto no encontrado")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProject(@PathVariable Long id) {
        if (projectService.findById(id).isPresent()) {
            projectService.delete(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }


}
