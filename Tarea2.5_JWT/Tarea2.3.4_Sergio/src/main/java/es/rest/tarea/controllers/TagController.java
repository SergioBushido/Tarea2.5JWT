package es.rest.tarea.controllers;


import es.rest.tarea.models.dto.TagDto;
import es.rest.tarea.services.TagService;
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

@Tag(name = "TagController", description = "Controlador para operaciones relacionadas con etiquetas (tags)")
@RestController
@AllArgsConstructor
@CrossOrigin(origins = "*")
@RequestMapping("/api/v1/tags")
public class TagController {

    private final TagService tagService;

    @Operation(summary = "Obtiene todas las etiquetas")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de etiquetas encontrada exitosamente",
                    content = @Content(schema = @Schema(implementation = Tag.class)))
    })
    @GetMapping
    public ResponseEntity<List<es.rest.tarea.models.Tag>> getAllTags() {
        List<es.rest.tarea.models.Tag> tags = tagService.findAll();
        return ResponseEntity.ok(tags);
    }

    @Operation(summary = "Obtiene una etiqueta por su ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Etiqueta encontrada exitosamente",
                    content = @Content(schema = @Schema(implementation = Tag.class))),
            @ApiResponse(responseCode = "404", description = "Etiqueta no encontrada")
    })
    @GetMapping("/{id}")
    public ResponseEntity<es.rest.tarea.models.Tag> getTagById(@PathVariable Long id) {
        Optional<es.rest.tarea.models.Tag> tag = tagService.findById(id);
        return tag.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());

    }

    @Operation(summary = "Crea una nueva etiqueta")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Etiqueta creada exitosamente",
                    content = @Content(schema = @Schema(implementation = Tag.class)))
    })
    @PostMapping
    public ResponseEntity<es.rest.tarea.models.Tag> createTag(@RequestBody TagDto tagDto) {
      es.rest.tarea.models.Tag savedTag = tagService.create(tagDto);
      return new ResponseEntity<>(savedTag, HttpStatus.CREATED);
    }

    @Operation(summary = "Actualiza una etiqueta por su ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Etiqueta actualizada exitosamente",
                    content = @Content(schema = @Schema(implementation = Tag.class)))
    })
    @PutMapping("/{id}")
    public ResponseEntity<es.rest.tarea.models.Tag> updateTag(@PathVariable Long id, @RequestBody TagDto tagDto) {
       Optional<es.rest.tarea.models.Tag>updatedTag = tagService.update(id, tagDto);
       return updatedTag.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @Operation(summary = "Elimina una etiqueta por su ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Etiqueta eliminada exitosamente"),
            @ApiResponse(responseCode = "404", description = "Etiqueta no encontrada")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTag(@PathVariable Long id) {
        if(tagService.findById(id).isPresent()) {
            tagService.delete(id);
            return ResponseEntity.noContent().build();
        }else{
            return ResponseEntity.notFound().build();
        }
    }
}

