package com.accenture.excusas.controller;

import com.accenture.excusas.dto.ConsecuenciaRequestDTO;
import com.accenture.excusas.dto.ConsecuenciaResponseDTO;
import com.accenture.excusas.service.ExcusaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/fragmentos/consecuencias")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
@Tag(name = "Fragmentos - Consecuencias", description = "CRUD para fragmentos de consecuencia")
public class ConsecuenciaController {
    private final ExcusaService excusaService;

    @PostMapping
    @Operation(summary = "Crear nueva consecuencia", 
               description = "Crea un nuevo fragmento de consecuencia para excusas")
    @ApiResponse(responseCode = "201", description = "Consecuencia creada exitosamente",
                 content = @Content(schema = @Schema(implementation = ConsecuenciaResponseDTO.class)))
    @ApiResponse(responseCode = "400", description = "Datos inválidos")
    public ResponseEntity<ConsecuenciaResponseDTO> crear(@Valid @RequestBody ConsecuenciaRequestDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(excusaService.crearConsecuencia(dto));
    }
}
