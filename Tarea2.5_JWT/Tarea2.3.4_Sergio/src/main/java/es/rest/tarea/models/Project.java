package es.rest.tarea.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Project {
	private Long id;
	private String description;
	private String language;
	private boolean isOpen;
}
