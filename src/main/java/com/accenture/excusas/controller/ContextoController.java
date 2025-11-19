package com.accenture.excusas.controller;

import com.accenture.excusas.dto.ContextoRequestDTO;
import com.accenture.excusas.dto.ContextoResponseDTO;
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
@RequestMapping("/api/fragmentos/contextos")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
@Tag(name = "Fragmentos - Contextos", description = "CRUD para fragmentos de contexto")
public class ContextoController {
    private final ExcusaService excusaService;

    @PostMapping
    @Operation(summary = "Crear nuevo contexto", 
               description = "Crea un nuevo fragmento de contexto para excusas")
    @ApiResponse(responseCode = "201", description = "Contexto creado exitosamente",
                 content = @Content(schema = @Schema(implementation = ContextoResponseDTO.class)))
    @ApiResponse(responseCode = "400", description = "Datos inválidos")
    public ResponseEntity<ContextoResponseDTO> crear(@Valid @RequestBody ContextoRequestDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(excusaService.crearContexto(dto));
    }
}
