package es.rest.tarea.models.dto;

import es.rest.tarea.models.Project;
import lombok.Data;

@Data
public class ProjectDto {
    private String description;
    private String language;
    private boolean isOpen;

    public static ProjectDto fromEntity(Project project) {
        ProjectDto projectDto = new ProjectDto();
        projectDto.setDescription(project.getDescription());
        projectDto.setOpen(project.isOpen());
        projectDto.setLanguage(project.getLanguage());
        return projectDto;
    }

    public static Project toEntity(ProjectDto projectDto) {
        Project project = new Project();
        project.setDescription(projectDto.getDescription());
        project.setOpen(projectDto.isOpen());
        project.setLanguage(projectDto.getLanguage());
        return project;
    }
}
