package es.rest.tarea.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Phone {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO) // Puedes usar GenerationType.AUTO, IDENTITY, SEQUENCE, o TABLE según tu necesidad
    private Long id;
	private int price;
	private String type;


}
