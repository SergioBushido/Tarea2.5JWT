package es.rest.tarea.controllers;

import es.rest.tarea.models.Phone;
import es.rest.tarea.models.dto.PhoneDto;
import es.rest.tarea.models.dto.UserDto;
import es.rest.tarea.services.PhoneService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Tag(name = "PhoneController", description = "Controlador para operaciones relacionadas con teléfonos")
@RestController
@AllArgsConstructor
@CrossOrigin(origins = "*")
@RequestMapping("/api/v1")
public class PhoneController {

	private final PhoneService phoneService;

	@Operation(summary = "Obtiene todos los teléfonos")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Lista de teléfonos encontrada exitosamente", content = @Content(schema = @Schema(implementation = String.class))) })
	@GetMapping("/phones")
	public ResponseEntity<List<Phone>> getPhones() {

		List<Phone> phone = phoneService.getAllPhones();
		return ResponseEntity.ok(phone);
	}

	@Operation(summary = "Obtiene un teléfono por su ID")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Teléfono encontrado exitosamente", content = @Content(schema = @Schema(implementation = String.class))),
			@ApiResponse(responseCode = "404", description = "Teléfono no encontrado") })
	@GetMapping("/phones/{id}")
	public ResponseEntity<Phone> getPhone(@PathVariable int id) {

		Optional<Phone> phone = phoneService.getPhoneById(id);
		return phone.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
	}

	@Operation(summary = "Crea un nuevo teléfono")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "201", description = "Teléfono creado exitosamente", content = @Content(schema = @Schema(implementation = String.class))) })
	@PostMapping("/phones")
	public ResponseEntity<PhoneDto>createPhone(@RequestBody PhoneDto phone) {
		PhoneDto phoneDto = phoneService.addPhone(phone);
		return ResponseEntity.status(201).body(phoneDto);

	}

	@Operation(summary = "Actualiza un teléfono por su ID")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Teléfono actualizado exitosamente", content = @Content(schema = @Schema(implementation = String.class))) })
	@PutMapping("/phones/{id}")
	public ResponseEntity<PhoneDto> updatePhone(@PathVariable Long id, @RequestBody PhoneDto phone) {

		try {
			PhoneDto updatedPhone = phoneService.updatePhone(id, phone);
			return new ResponseEntity<>(updatedPhone, HttpStatus.OK);
		} catch (RuntimeException e) {
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		}
	}

	@Operation(summary = "Elimina un teléfono por su ID")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Teléfono eliminado exitosamente"),
			@ApiResponse(responseCode = "404", description = "Teléfono no encontrado") })
	@DeleteMapping("/phones/{id}")
	public ResponseEntity<String> deletePhone(@PathVariable int id) {
		phoneService.deletePhone(id);
		return ResponseEntity.ok("Borrado exitosamente");
	}

}