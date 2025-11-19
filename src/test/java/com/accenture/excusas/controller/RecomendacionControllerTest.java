package com.accenture.excusas.controller;

import com.accenture.excusas.dto.RecomendacionRequestDTO;
import com.accenture.excusas.dto.RecomendacionResponseDTO;
import com.accenture.excusas.service.ExcusaService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@DisplayName("RecomendacionController Tests")
class RecomendacionControllerTest {

    @Mock
    private ExcusaService excusaService;

    @InjectMocks
    private RecomendacionController recomendacionController;

    @Test
    @DisplayName("Debe crear recomendación y retornar 201 CREATED")
    void testCrearRecomendacion() {
        // Arrange
        RecomendacionRequestDTO request = RecomendacionRequestDTO.builder()
                .texto("Nueva recomendación")
                .role("pm")
                .tags("advice")
                .build();

        RecomendacionResponseDTO response = RecomendacionResponseDTO.builder()
                .id(1L)
                .texto("Nueva recomendación")
                .role("pm")
                .tags("advice")
                .build();

        when(excusaService.crearRecomendacion(any(RecomendacionRequestDTO.class))).thenReturn(response);

        // Act
        ResponseEntity<RecomendacionResponseDTO> result = recomendacionController.crear(request);

        // Assert
        assertEquals(HttpStatus.CREATED, result.getStatusCode());
        assertNotNull(result.getBody());
        verify(excusaService, times(1)).crearRecomendacion(any(RecomendacionRequestDTO.class));
    }
}
