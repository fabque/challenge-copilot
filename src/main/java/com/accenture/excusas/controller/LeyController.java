package com.accenture.excusas.controller;

import com.accenture.excusas.dto.LeyRequestDTO;
import com.accenture.excusas.dto.LeyResponseDTO;
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
@RequestMapping("/api/fragmentos/leyes")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
@Tag(name = "Fragmentos - Leyes", description = "CRUD para fragmentos de leyes/axiomas")
public class LeyController {
    private final ExcusaService excusaService;

    @PostMapping
    @Operation(summary = "Crear nueva ley/axioma", 
               description = "Crea un nuevo fragmento de ley técnica (Murphy, Hofstadter, Dilbert, DevOps, etc.)")
    @ApiResponse(responseCode = "201", description = "Ley creada exitosamente",
                 content = @Content(schema = @Schema(implementation = LeyResponseDTO.class)))
    @ApiResponse(responseCode = "400", description = "Datos inválidos")
    public ResponseEntity<LeyResponseDTO> crear(@Valid @RequestBody LeyRequestDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(excusaService.crearLey(dto));
    }
}
