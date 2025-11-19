package com.accenture.excusas.controller;

import com.accenture.excusas.dto.RecomendacionRequestDTO;
import com.accenture.excusas.dto.RecomendacionResponseDTO;
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
@RequestMapping("/api/fragmentos/recomendaciones")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
@Tag(name = "Fragmentos - Recomendaciones", description = "CRUD para fragmentos de recomendación")
public class RecomendacionController {
    private final ExcusaService excusaService;

    @PostMapping
    @Operation(summary = "Crear nueva recomendación", 
               description = "Crea un nuevo fragmento de recomendación para excusas")
    @ApiResponse(responseCode = "201", description = "Recomendación creada exitosamente",
                 content = @Content(schema = @Schema(implementation = RecomendacionResponseDTO.class)))
    @ApiResponse(responseCode = "400", description = "Datos inválidos")
    public ResponseEntity<RecomendacionResponseDTO> crear(@Valid @RequestBody RecomendacionRequestDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(excusaService.crearRecomendacion(dto));
    }
}
