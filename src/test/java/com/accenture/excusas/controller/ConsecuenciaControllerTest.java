package com.accenture.excusas.controller;

import com.accenture.excusas.dto.ConsecuenciaRequestDTO;
import com.accenture.excusas.dto.ConsecuenciaResponseDTO;
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
@DisplayName("ConsecuenciaController Tests")
class ConsecuenciaControllerTest {

    @Mock
    private ExcusaService excusaService;

    @InjectMocks
    private ConsecuenciaController consecuenciaController;

    @Test
    @DisplayName("Debe crear consecuencia y retornar 201 CREATED")
    void testCrearConsecuencia() {
        // Arrange
        ConsecuenciaRequestDTO request = ConsecuenciaRequestDTO.builder()
                .texto("Nueva consecuencia")
                .role("devops")
                .tags("incident")
                .build();

        ConsecuenciaResponseDTO response = ConsecuenciaResponseDTO.builder()
                .id(1L)
                .texto("Nueva consecuencia")
                .role("devops")
                .tags("incident")
                .build();

        when(excusaService.crearConsecuencia(any(ConsecuenciaRequestDTO.class))).thenReturn(response);

        // Act
        ResponseEntity<ConsecuenciaResponseDTO> result = consecuenciaController.crear(request);

        // Assert
        assertEquals(HttpStatus.CREATED, result.getStatusCode());
        assertNotNull(result.getBody());
        verify(excusaService, times(1)).crearConsecuencia(any(ConsecuenciaRequestDTO.class));
    }
}
