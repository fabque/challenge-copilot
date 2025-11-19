package com.accenture.excusas.controller;

import com.accenture.excusas.dto.LeyRequestDTO;
import com.accenture.excusas.dto.LeyResponseDTO;
import com.accenture.excusas.service.ExcusaService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/fragmentos/leyes")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class LeyController {
    private final ExcusaService excusaService;

    @PostMapping
    public ResponseEntity<LeyResponseDTO> crear(@Valid @RequestBody LeyRequestDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(excusaService.crearLey(dto));
    }
}
