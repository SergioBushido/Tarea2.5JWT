package es.rest.tarea.repositories;

import es.rest.tarea.models.Blog;
import org.springframework.data.repository.CrudRepository;

public interface BlogRepository extends CrudRepository<Blog, Integer> {
}
