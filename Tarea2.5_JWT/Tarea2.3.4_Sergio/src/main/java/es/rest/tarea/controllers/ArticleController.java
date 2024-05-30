package es.rest.tarea.controllers;

import es.rest.tarea.models.Article;
import es.rest.tarea.models.dto.ArticleDTO;
import es.rest.tarea.services.ArticleService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;


@Tag(name = "ArticleController", description = "Controlador para operaciones relacionadas con artículos")
@RestController
@AllArgsConstructor
@CrossOrigin(origins = "*")
@RequestMapping("/api/v1")
public class ArticleController {

    private final ArticleService articleService;

    @Operation(summary = "Obtiene todos los artículos")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de artículos encontrada exitosamente",
                    content = @Content(schema = @Schema(implementation = String.class)))
    })
    @GetMapping("/articles")
    public List<Article> getArticles() {
        return articleService.findAllArticles();
    }

    @Operation(summary = "Obtiene un artículo por su ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Artículo encontrado exitosamente",
                    content = @Content(schema = @Schema(implementation = String.class))),
            @ApiResponse(responseCode = "404", description = "Artículo no encontrado")
    })
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/articles/{id}")
    public Optional<Article> getArticle(@PathVariable Long id) {
        return articleService.findArticleById(id);
    }

    @Operation(summary = "Crea un nuevo artículo")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Artículo creado exitosamente",
                    content = @Content(schema = @Schema(implementation = String.class)))
    })
    @PostMapping
    public ResponseEntity<Article> createArticle(@RequestBody ArticleDTO articleDTO) {
        Article article = new Article();
        article.setTitle(articleDTO.getTitle());
        article.setContent(articleDTO.getContent());

        Article savedArticle = articleService.saveArticle(article);
        return new ResponseEntity<>(savedArticle, HttpStatus.CREATED);
    }
    @Operation(summary = "Actualiza un artículo por su ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Artículo actualizado exitosamente",
                    content = @Content(schema = @Schema(implementation = String.class)))
    })
    @PutMapping("/articles/{id}")
    public Article updateArticle(@PathVariable Long id, @RequestBody Article article) {
        return articleService.updateArticle(id, article);
    }

    @Operation(summary = "Elimina un artículo por su ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Artículo eliminado exitosamente"),
            @ApiResponse(responseCode = "404", description = "Artículo no encontrado")
    })

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @DeleteMapping("/articles/{id}")
    public void deleteArticle(@PathVariable Long id) {
        articleService.deleteArticle(id);
    }

}