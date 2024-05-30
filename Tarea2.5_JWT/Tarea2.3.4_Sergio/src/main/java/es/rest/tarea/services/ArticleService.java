package es.rest.tarea.services;

import es.rest.tarea.models.Article;
import es.rest.tarea.repositories.ArticleRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ArticleService {

    private final ArticleRepository articleRepository;

    public List<Article> findAllArticles() {
        return articleRepository.findAll();
    }

    public Optional<Article> findArticleById(Long id) {
        return articleRepository.findById(id);
    }

    public Article saveArticle(Article article) {
        return articleRepository.save(article);
    }

    public Article updateArticle(Long id, Article article) {
        Article existingArticle = articleRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Article not found with id: " + id));

        BeanUtils.copyProperties(article, existingArticle, "id");

        return articleRepository.save(existingArticle);
    }



    public void deleteArticle(Long id) {
        articleRepository.deleteById(id);
    }

}
