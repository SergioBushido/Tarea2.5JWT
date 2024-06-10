package es.rest.tarea.services;


import es.rest.tarea.models.Tag;
import es.rest.tarea.models.dto.TagDto;
import es.rest.tarea.repositories.TagRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class TagService {

    private TagRepository tagRepository;

    public List<Tag> findAll() {
        return tagRepository.findAll();
    }

   public Optional<Tag> findById(Long id) {
        return tagRepository.findById(id);
   }

   public Tag save(Tag tag) {
       return tagRepository.save(tag);
   }

   public void delete(Long id) {
        tagRepository.deleteById(id);
   }

   public Tag create(TagDto tagDto) {
        Tag tag = TagDto.toEntity(tagDto);
        return tagRepository.save(tag);

   }

   @Transactional
   public Optional<Tag> update(Long id, TagDto tagDto) {

        return tagRepository.findById(id)
                .map(toUpdate->{
                    Tag updateTag  = TagDto.toEntity(tagDto);
                    updateTag.setId(toUpdate.getId());
                    return tagRepository.save(updateTag);
                });
   }
}
