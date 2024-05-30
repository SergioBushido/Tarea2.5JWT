package es.rest.tarea.repositories;

import es.rest.tarea.models.Phone;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PhoneRepository extends CrudRepository<Phone, Long> {
}
