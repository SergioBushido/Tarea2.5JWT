package es.rest.tarea.services;

import es.rest.tarea.models.Project;
import es.rest.tarea.models.dto.ProjectDto;
import es.rest.tarea.repositories.ProjectRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ProjectService {

    private final ProjectRepository projectRepository;

    public List<Project> findAll() {
        return projectRepository.findAll();
    }

    public Optional<Project> findById(Long id) {
        return projectRepository.findById(id);
    }

    public Project save(Project project) {
        return projectRepository.save(project);
    }

    public void delete(Long id) {
        projectRepository.deleteById(id);
    }

    public Project create(ProjectDto projectDto) {
        Project project = ProjectDto.toEntity(projectDto);
        return projectRepository.save(project);
    }

    @Transactional
    public Optional<Project> update(Long id, ProjectDto projectDto) {
        return projectRepository.findById(id)
 //map se utiliza para aplicar una función a ese valor si está presente (en toUpdate)
                   .map(toUpdate -> {
                    Project updatedProject = ProjectDto.toEntity(projectDto);
                    updatedProject.setId(toUpdate.getId());//esto asegura que se mantiene el id
                    return projectRepository.save(updatedProject);
                });
    }
}
