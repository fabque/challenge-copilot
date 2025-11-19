package com.accenture.excusas.controller;

import com.accenture.excusas.dto.MemeRequestDTO;
import com.accenture.excusas.dto.MemeResponseDTO;
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
@RequestMapping("/api/fragmentos/memes")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
@Tag(name = "Fragmentos - Memes", description = "CRUD para fragmentos de memes")
public class MemeController {
    private final ExcusaService excusaService;

    @PostMapping
    @Operation(summary = "Crear nuevo meme", 
               description = "Crea un nuevo fragmento de meme tech argentino")
    @ApiResponse(responseCode = "201", description = "Meme creado exitosamente",
                 content = @Content(schema = @Schema(implementation = MemeResponseDTO.class)))
    @ApiResponse(responseCode = "400", description = "Datos inválidos")
    public ResponseEntity<MemeResponseDTO> crear(@Valid @RequestBody MemeRequestDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(excusaService.crearMeme(dto));
    }
}
