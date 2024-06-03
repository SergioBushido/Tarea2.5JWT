package es.rest.tarea.controllers;


import es.rest.tarea.models.Blog;
import es.rest.tarea.models.dto.BlogDto;
import es.rest.tarea.services.BlogService;
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


@Tag(name = "BlogController", description = "Controlador para operaciones relacionadas con blog")
@RestController
@AllArgsConstructor
@CrossOrigin(origins = "*")
@RequestMapping("/api/v1/blog")
public class BlogController {


    private final BlogService blogService;

    @Operation(summary = "Obtiene todos los blogs")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de blogs encontrada exitosamente",
                    content = @Content(schema = @Schema(implementation = String.class)))
    })
    @GetMapping("/blog")
    public List<Blog> getBlogs() {

        return blogService.findAll();
    }

    @Operation(summary = "Obtiene un blog por su ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Blog encontrado exitosamente",
                    content = @Content(schema = @Schema(implementation = String.class))),
            @ApiResponse(responseCode = "404", description = "Blog no encontrado")
    })
    @GetMapping("/{id}")
    public ResponseEntity<Blog> getBlog(@PathVariable int id) {
        Optional<Blog> blog = blogService.findById(id);
        return blog.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @Operation(summary = "Crea un nuevo blog")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Blog creado exitosamente",
                    content = @Content(schema = @Schema(implementation = String.class)))
    })
    @PostMapping
    public ResponseEntity<Blog> createBlog(@RequestBody BlogDto blogDto) {
        Blog blog = new Blog();
        blog.setTitle(blogDto.getTitle());
        blog.setDate(blogDto.getDate());

        Blog savedBlog = blogService.save(blog);

        return new ResponseEntity<>(savedBlog, HttpStatus.CREATED);
    }

    @Operation(summary = "Actualiza un blog por su ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Blog actualizado exitosamente",
                    content = @Content(schema = @Schema(implementation = String.class)))
    })
    @PutMapping("/{id}")
    public ResponseEntity<Blog> updateBlog(@PathVariable int id,@RequestBody BlogDto blogDto) {
        Blog updatedBlog = blogService.update(id, blogDto);
        return ResponseEntity.ok(updatedBlog);
    }

    @Operation(summary = "Elimina un blog por su ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Blog eliminado exitosamente"),
            @ApiResponse(responseCode = "404", description = "Blog no encontrado")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void>  deleteBlog(@PathVariable int id) {
        if(blogService.delete(id)){
            return ResponseEntity.ok().build();
        }else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

    }

}
