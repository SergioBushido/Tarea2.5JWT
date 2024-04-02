package es.rest.tarea.models;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Detail {
	private Long id;
	private LocalDate date;
	private String content;
	private String type;
	private Double budget;
}
