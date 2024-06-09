package es.rest.tarea.services;

import es.rest.tarea.models.Phone;
import es.rest.tarea.models.Project;
import es.rest.tarea.models.dto.PhoneDto;
import es.rest.tarea.models.dto.ProjectDto;
import es.rest.tarea.repositories.ProjectRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Service
@AllArgsConstructor
public class ProjectService {

    private final ProjectRepository projectRepository;

    public List<Project> findAll() {
        return projectRepository.findAll();
    }

    public Project findById(Long id) {
        return projectRepository.findById(id).get();
    }

    public Project save(Project project) {
        return projectRepository.save(project);
    }

    public void delete(Project project) {
        projectRepository.delete(project);
    }

    public Project create(Project project) {
        return projectRepository.save(project);

    }


    @Transactional
    public Project update(Long id, ProjectDto projectDto) {
        Project toUpdate = projectRepository.findById(id).orElse(null);

        if (toUpdate != null) {
            toUpdate.setDescription(projectDto.getDescription());
            toUpdate.setOpen(projectDto.isOpen());
            toUpdate.setLanguage(projectDto.getLanguage());

            projectRepository.save(toUpdate);
        }

        return toUpdate;
    }
}