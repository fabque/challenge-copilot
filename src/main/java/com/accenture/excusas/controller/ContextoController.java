package com.accenture.excusas.controller;

import com.accenture.excusas.dto.ContextoRequestDTO;
import com.accenture.excusas.dto.ContextoResponseDTO;
import com.accenture.excusas.service.ExcusaService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/fragmentos/contextos")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class ContextoController {
    private final ExcusaService excusaService;

    @PostMapping
    public ResponseEntity<ContextoResponseDTO> crear(@Valid @RequestBody ContextoRequestDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(excusaService.crearContexto(dto));
    }
}
