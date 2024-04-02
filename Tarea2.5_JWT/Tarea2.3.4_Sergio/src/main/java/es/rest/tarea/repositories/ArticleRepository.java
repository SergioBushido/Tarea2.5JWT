package es.rest.tarea.repositories;

import es.rest.tarea.models.Article;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ArticleRepository extends ListCrudRepository<Article, Long> {
}
