package es.rest.tarea.services;

import es.rest.tarea.models.Blog;
import es.rest.tarea.models.dto.BlogDto;
import es.rest.tarea.repositories.BlogRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class BlogService {
    private BlogRepository blogRepository;

    public List<Blog> findAll() {
        return (List<Blog>) blogRepository.findAll();
    }

    public Optional<Blog> findById(int id) {
        return blogRepository.findById(id);
    }

    public Blog save(Blog blog) {
        return blogRepository.save(blog);
    }

    public Blog update(int id, BlogDto blog) {
        Blog existingBlog = blogRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Blog not found with id: " + id));
        BeanUtils.copyProperties(blog, existingBlog, "id");
        /*
         BeanUtils sustituye esto
         existingBlog.setTitle(blog.getTitle());
    existingBlog.setContent(blog.getContent());*/
        return blogRepository.save(existingBlog);

    }

    public boolean delete(int id) {
        if(blogRepository.existsById(id)) {
            blogRepository.deleteById(id);
            return true;
        }else {
            return false;
        }

    }
}
