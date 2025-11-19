package com.accenture.excusas.controller;

import com.accenture.excusas.dto.CausaRequestDTO;
import com.accenture.excusas.dto.CausaResponseDTO;
import com.accenture.excusas.service.ExcusaService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/fragmentos/causas")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class CausaController {
    private final ExcusaService excusaService;

    @PostMapping
    public ResponseEntity<CausaResponseDTO> crear(@Valid @RequestBody CausaRequestDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(excusaService.crearCausa(dto));
    }
}
