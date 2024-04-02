package es.rest.tarea.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@Tag(name = "PhoneController", description = "Controlador para operaciones relacionadas con teléfonos")
@RestController
@AllArgsConstructor
@CrossOrigin(origins = "*")
@RequestMapping("/api/v1")
public class PhoneController {

	@Operation(summary = "Obtiene todos los teléfonos")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Lista de teléfonos encontrada exitosamente", content = @Content(schema = @Schema(implementation = String.class))) })
	@GetMapping("/phones")
	public String getPhones() {
		return "List of phones";
	}

	@Operation(summary = "Obtiene un teléfono por su ID")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Teléfono encontrado exitosamente", content = @Content(schema = @Schema(implementation = String.class))),
			@ApiResponse(responseCode = "404", description = "Teléfono no encontrado") })
	@GetMapping("/phones/{id}")
	public String getPhone(@PathVariable Long id) {
		return "Phone";
	}

	@Operation(summary = "Crea un nuevo teléfono")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "201", description = "Teléfono creado exitosamente", content = @Content(schema = @Schema(implementation = String.class))) })
	@PostMapping("/phones")
	public String createPhone(@RequestBody String phone) {
		return phone;
	}

	@Operation(summary = "Actualiza un teléfono por su ID")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Teléfono actualizado exitosamente", content = @Content(schema = @Schema(implementation = String.class))) })
	@PutMapping("/phones/{id}")
	public String updatePhone(@PathVariable Long id) {
		return "Phone updated";
	}

	@Operation(summary = "Elimina un teléfono por su ID")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Teléfono eliminado exitosamente"),
			@ApiResponse(responseCode = "404", description = "Teléfono no encontrado") })
	@DeleteMapping("/phones/{id}")
	public String deletePhone(@PathVariable Long id) {
		return "Phone deleted";
	}

}