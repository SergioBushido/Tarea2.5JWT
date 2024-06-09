package es.rest.tarea.controllers;

import es.rest.tarea.models.Code;
import es.rest.tarea.models.dto.CodeDto;
import es.rest.tarea.repositories.CodeRepository;
import es.rest.tarea.services.CodeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Tag(name = "CodeController", description = "Controlador para operaciones relacionadas con códigos")
@RestController
@AllArgsConstructor
@CrossOrigin(origins = "*")
@RequestMapping("/api/v1/codes")
public class CodeController {

    private final CodeService codeService;
    private final CodeRepository codeRepository;

    @Operation(summary = "Obtiene todos los códigos")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de códigos encontrada exitosamente",
                    content = @Content(schema = @Schema(implementation = Code.class)))
    })
    @GetMapping
    public List<Code> getCodes() {
        return codeService.findAll();
    }

    @Operation(summary = "Obtiene un código por su ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Código encontrado exitosamente",
                    content = @Content(schema = @Schema(implementation = Code.class))),
            @ApiResponse(responseCode = "404", description = "Código no encontrado")
    })
    @GetMapping("/{id}")
    public ResponseEntity<Code> getCodeById(@PathVariable Long id) {
        Optional<Code> code = codeRepository.findById(id);
        return code.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound()
                        .build());

    }

    //POR AQUI
    @Operation(summary = "Crea un nuevo código")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Código creado exitosamente",
                    content = @Content(schema = @Schema(implementation = Code.class)))
    })
    @PostMapping
    public ResponseEntity<Code> createCode(@Valid @RequestBody CodeDto codeDto) {
        Code code = new Code();
        code.setFile(codeDto.getFile());
        code.setPackageName(codeDto.getPackageName());

        Code savedCode = codeService.save(code);


        return new ResponseEntity<>(savedCode, HttpStatus.CREATED);
    }

    @Operation(summary = "Actualiza un código por su ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Código actualizado exitosamente",
                    content = @Content(schema = @Schema(implementation = Code.class)))
    })
    @PutMapping("/{id}")
    public Code updateCode(@PathVariable Long id, @RequestBody CodeDto codeDto) {
        Code codeUpdated = codeService.update(id, codeDto);
        BeanUtils.copyProperties(codeDto, codeUpdated, "id");
        return codeRepository.save(codeUpdated);

    }

    @Operation(summary = "Elimina un código por su ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Código eliminado exitosamente"),
            @ApiResponse(responseCode = "404", description = "Código no encontrado")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCode(@PathVariable Long id) {
        if (codeService.delete(id)) {
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
}