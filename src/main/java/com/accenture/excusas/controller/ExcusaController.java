package com.accenture.excusas.controller;

import com.accenture.excusas.dto.ExcusaResponseDTO;
import com.accenture.excusas.service.ExcusaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/excusas")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
@Tag(name = "Excusas", description = "Generación de excusas técnicas aleatorias en 5 modos diferentes")
public class ExcusaController {
    private final ExcusaService excusaService;

    @GetMapping("/simple")
    @Operation(summary = "Generar excusa simple", 
               description = "Retorna una excusa con contexto, causa, consecuencia y recomendación",
               tags = {"Excusas"})
    @ApiResponse(responseCode = "200", description = "Excusa generada exitosamente",
                 content = @Content(schema = @Schema(implementation = ExcusaResponseDTO.class)))
    public ResponseEntity<ExcusaResponseDTO> generarExcusaSimple() {
        return ResponseEntity.ok(excusaService.generarExcusaSimple());
    }

    @GetMapping("/con-meme")
    @Operation(summary = "Generar excusa con meme", 
               description = "Retorna una excusa simple + meme tech argentino",
               tags = {"Excusas"})
    @ApiResponse(responseCode = "200", description = "Excusa con meme generada exitosamente",
                 content = @Content(schema = @Schema(implementation = ExcusaResponseDTO.class)))
    public ResponseEntity<ExcusaResponseDTO> generarExcusaConMeme() {
        return ResponseEntity.ok(excusaService.generarExcusaConMeme());
    }

    @GetMapping("/con-ley")
    @Operation(summary = "Generar excusa con ley", 
               description = "Retorna una excusa simple + ley/axioma técnico (Murphy, Hofstadter, Dilbert, DevOps)",
               tags = {"Excusas"})
    @ApiResponse(responseCode = "200", description = "Excusa con ley generada exitosamente",
                 content = @Content(schema = @Schema(implementation = ExcusaResponseDTO.class)))
    public ResponseEntity<ExcusaResponseDTO> generarExcusaConLey() {
        return ResponseEntity.ok(excusaService.generarExcusaConLey());
    }

    @GetMapping("/ultra-shark")
    @Operation(summary = "Generar excusa ultra-shark (meme + ley)", 
               description = "Retorna una excusa simple + meme + ley (combinación completa)",
               tags = {"Excusas"})
    @ApiResponse(responseCode = "200", description = "Excusa ultra-shark generada exitosamente",
                 content = @Content(schema = @Schema(implementation = ExcusaResponseDTO.class)))
    public ResponseEntity<ExcusaResponseDTO> generarExcusaUltraShark() {
        return ResponseEntity.ok(excusaService.generarExcusaUltraShark());
    }

    @GetMapping("/por-rol/{rol}")
    @Operation(summary = "Generar excusa por rol", 
               description = "Retorna una excusa filtrada por rol (dev, qa, devops, pm, sre, etc.)",
               tags = {"Excusas"})
    @ApiResponse(responseCode = "200", description = "Excusa por rol generada exitosamente",
                 content = @Content(schema = @Schema(implementation = ExcusaResponseDTO.class)))
    public ResponseEntity<ExcusaResponseDTO> generarExcusaPorRol(
            @Parameter(description = "Rol del usuario (dev, qa, devops, pm, sre)")
            @PathVariable String rol) {
        return ResponseEntity.ok(excusaService.generarExcusaPorRol(rol));
    }
}

