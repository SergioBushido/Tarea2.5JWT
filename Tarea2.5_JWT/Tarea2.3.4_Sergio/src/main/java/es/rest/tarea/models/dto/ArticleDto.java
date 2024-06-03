package es.rest.tarea.models.dto;

import lombok.Data;

@Data
public class ArticleDto {
    private String title;
    private String content;
    // Otros campos según la estructura del artículo
}