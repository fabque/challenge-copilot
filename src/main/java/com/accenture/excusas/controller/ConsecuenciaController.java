package com.accenture.excusas.controller;

import com.accenture.excusas.dto.ConsecuenciaRequestDTO;
import com.accenture.excusas.dto.ConsecuenciaResponseDTO;
import com.accenture.excusas.service.ExcusaService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/fragmentos/consecuencias")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class ConsecuenciaController {
    private final ExcusaService excusaService;

    @PostMapping
    public ResponseEntity<ConsecuenciaResponseDTO> crear(@Valid @RequestBody ConsecuenciaRequestDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(excusaService.crearConsecuencia(dto));
    }
}
