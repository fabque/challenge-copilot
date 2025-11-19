package com.accenture.excusas.controller;

import com.accenture.excusas.dto.RecomendacionRequestDTO;
import com.accenture.excusas.dto.RecomendacionResponseDTO;
import com.accenture.excusas.service.ExcusaService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/fragmentos/recomendaciones")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class RecomendacionController {
    private final ExcusaService excusaService;

    @PostMapping
    public ResponseEntity<RecomendacionResponseDTO> crear(@Valid @RequestBody RecomendacionRequestDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(excusaService.crearRecomendacion(dto));
    }
}
