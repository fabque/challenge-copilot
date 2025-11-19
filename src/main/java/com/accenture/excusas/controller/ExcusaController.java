package com.accenture.excusas.controller;

import com.accenture.excusas.dto.ExcusaResponseDTO;
import com.accenture.excusas.service.ExcusaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/excusas")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class ExcusaController {
    private final ExcusaService excusaService;

    @GetMapping("/simple")
    public ResponseEntity<ExcusaResponseDTO> generarExcusaSimple() {
        return ResponseEntity.ok(excusaService.generarExcusaSimple());
    }

    @GetMapping("/con-meme")
    public ResponseEntity<ExcusaResponseDTO> generarExcusaConMeme() {
        return ResponseEntity.ok(excusaService.generarExcusaConMeme());
    }

    @GetMapping("/con-ley")
    public ResponseEntity<ExcusaResponseDTO> generarExcusaConLey() {
        return ResponseEntity.ok(excusaService.generarExcusaConLey());
    }

    @GetMapping("/ultra-shark")
    public ResponseEntity<ExcusaResponseDTO> generarExcusaUltraShark() {
        return ResponseEntity.ok(excusaService.generarExcusaUltraShark());
    }

    @GetMapping("/por-rol/{rol}")
    public ResponseEntity<ExcusaResponseDTO> generarExcusaPorRol(@PathVariable String rol) {
        return ResponseEntity.ok(excusaService.generarExcusaPorRol(rol));
    }
}
