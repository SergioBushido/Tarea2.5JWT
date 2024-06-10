package es.rest.tarea.models.dto;

import es.rest.tarea.models.Tag;
import lombok.Data;

import java.time.LocalDate;

@Data
public class TagDto {

    private String label;
    private LocalDate date;

    public static Tag toEntity(TagDto tagDto){
        Tag tag = new Tag();
        tag.setLabel(tagDto.getLabel());
        tag.setDate(tagDto.getDate());
        return tag;
    }

    public static TagDto toDto(Tag tag){
        TagDto tagDto = new TagDto();
        tagDto.setDate(tag.getDate());
        tagDto.setLabel(tag.getLabel());
        return tagDto;
    }
}
