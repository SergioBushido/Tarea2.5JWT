package es.rest.tarea.controllers;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;


@Tag(name = "BlogController", description = "Controlador para operaciones relacionadas con blog")
@RestController
@AllArgsConstructor
@CrossOrigin(origins = "*")
@RequestMapping("/api/v1")
public class BlogController {


    @Operation(summary = "Obtiene todos los artículos")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de blogs encontrada exitosamente",
                    content = @Content(schema = @Schema(implementation = String.class)))
    })
    @GetMapping("/blog")
    public String getBlogs() {
        return "List of blogs";
    }

    @Operation(summary = "Obtiene un blog por su ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Blog encontrado exitosamente",
                    content = @Content(schema = @Schema(implementation = String.class))),
            @ApiResponse(responseCode = "404", description = "Blog no encontrado")
    })
    @GetMapping("/blog/{id}")
    public String getBlog(@PathVariable Long id) {
        return "Blog";
    }

    @Operation(summary = "Crea un nuevo blog")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Blog creado exitosamente",
                    content = @Content(schema = @Schema(implementation = String.class)))
    })
    @PostMapping("/blog")
    public String createBlog(@RequestBody String blog) {
        return blog;
    }

    @Operation(summary = "Actualiza un blog por su ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Blog actualizado exitosamente",
                    content = @Content(schema = @Schema(implementation = String.class)))
    })
    @PutMapping("/blog/{id}")
    public String updateBlog(@PathVariable Long id) {
        return "Blog updated";
    }

    @Operation(summary = "Elimina un blog por su ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Blog eliminado exitosamente"),
            @ApiResponse(responseCode = "404", description = "Blog no encontrado")
    })
    @DeleteMapping("/blog/{id}")
    public String deleteBlog(@PathVariable Long id) {
        return "Blog deleted";
    }

}
