package com.accenture.excusas.controller;

import com.accenture.excusas.dto.CausaRequestDTO;
import com.accenture.excusas.dto.CausaResponseDTO;
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
@RequestMapping("/api/fragmentos/causas")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
@Tag(name = "Fragmentos - Causas", description = "CRUD para fragmentos de causa")
public class CausaController {
    private final ExcusaService excusaService;

    @PostMapping
    @Operation(summary = "Crear nueva causa", 
               description = "Crea un nuevo fragmento de causa para excusas")
    @ApiResponse(responseCode = "201", description = "Causa creada exitosamente",
                 content = @Content(schema = @Schema(implementation = CausaResponseDTO.class)))
    @ApiResponse(responseCode = "400", description = "Datos inválidos")
    public ResponseEntity<CausaResponseDTO> crear(@Valid @RequestBody CausaRequestDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(excusaService.crearCausa(dto));
    }
}
