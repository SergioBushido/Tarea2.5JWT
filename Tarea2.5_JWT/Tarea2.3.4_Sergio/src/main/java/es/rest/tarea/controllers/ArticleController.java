package es.rest.tarea.controllers;

import es.rest.tarea.models.Article;
import es.rest.tarea.models.dto.ArticleDto;
import es.rest.tarea.repositories.ArticleRepository;
import es.rest.tarea.services.ArticleService;
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

@Tag(name = "ArticleController", description = "Controlador para operaciones relacionadas con artículos")
@RestController
@AllArgsConstructor
@CrossOrigin(origins = "*")
@RequestMapping("/api/v1/articles")
public class ArticleController {

    private final ArticleService articleService;
    private final ArticleRepository articleRepository;

    @Operation(summary = "Obtiene todos los artículos")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de artículos encontrada exitosamente",
                    content = @Content(schema = @Schema(implementation = Article.class)))
    })
    @GetMapping
    public List<Article> getArticles() {

        return articleService.findAllArticles();
    }

    @Operation(summary = "Obtiene un artículo por su ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Artículo encontrado exitosamente",
                    content = @Content(schema = @Schema(implementation = Article.class))),
            @ApiResponse(responseCode = "404", description = "Artículo no encontrado")
    })
    @GetMapping("/{id}")
    public ResponseEntity<Article> getArticle(@PathVariable Long id) {
        Optional<Article> article = articleService.findArticleById(id);
        return article.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @Operation(summary = "Crea un nuevo artículo")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Artículo creado exitosamente",
                    content = @Content(schema = @Schema(implementation = Article.class)))
    })
    @PostMapping
    public ResponseEntity<Article> createArticle(@RequestBody ArticleDto articleDTO) {
        Article article = new Article();
        article.setTitle(articleDTO.getTitle());
        article.setContent(articleDTO.getContent());

        Article savedArticle = articleService.saveArticle(article);
        return new ResponseEntity<>(savedArticle, HttpStatus.CREATED);
    }

    @Operation(summary = "Actualiza un artículo por su ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Artículo actualizado exitosamente",
                    content = @Content(schema = @Schema(implementation = Article.class)))
    })
    @PutMapping("/{id}")
    public ResponseEntity<Article> updateArticle(@PathVariable Long id, @RequestBody Article article) {
        Article updatedArticle = articleService.updateArticle(id, article);
        return ResponseEntity.ok(updatedArticle);
    }

    @Operation(summary = "Elimina un artículo por su ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Artículo eliminado exitosamente"),
            @ApiResponse(responseCode = "404", description = "Artículo no encontrado")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteArticle(@PathVariable Long id) {
        if (articleService.deleteArticle(id)) {
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
}
