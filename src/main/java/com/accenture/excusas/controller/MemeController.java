package com.accenture.excusas.controller;

import com.accenture.excusas.dto.MemeRequestDTO;
import com.accenture.excusas.dto.MemeResponseDTO;
import com.accenture.excusas.service.ExcusaService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/fragmentos/memes")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class MemeController {
    private final ExcusaService excusaService;

    @PostMapping
    public ResponseEntity<MemeResponseDTO> crear(@Valid @RequestBody MemeRequestDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(excusaService.crearMeme(dto));
    }
}
