package com.accenture.excusas.controller;

import com.accenture.excusas.dto.ContextoRequestDTO;
import com.accenture.excusas.dto.ContextoResponseDTO;
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
@DisplayName("ContextoController Tests")
class ContextoControllerTest {

    @Mock
    private ExcusaService excusaService;

    @InjectMocks
    private ContextoController contextoController;

    @Test
    @DisplayName("Debe crear contexto y retornar 201 CREATED")
    void testCrearContexto() {
        // Arrange
        ContextoRequestDTO request = ContextoRequestDTO.builder()
                .texto("Nuevo contexto")
                .role("dev")
                .tags("test")
                .build();

        ContextoResponseDTO response = ContextoResponseDTO.builder()
                .id(1L)
                .texto("Nuevo contexto")
                .role("dev")
                .tags("test")
                .build();

        when(excusaService.crearContexto(any(ContextoRequestDTO.class))).thenReturn(response);

        // Act
        ResponseEntity<ContextoResponseDTO> result = contextoController.crear(request);

        // Assert
        assertEquals(HttpStatus.CREATED, result.getStatusCode());
        assertNotNull(result.getBody());
        assertEquals(1L, result.getBody().getId());
        assertEquals("Nuevo contexto", result.getBody().getTexto());
        verify(excusaService, times(1)).crearContexto(any(ContextoRequestDTO.class));
    }

    @Test
    @DisplayName("Debe llamar al servicio con los datos correctos")
    void testCrearContextoConDatos() {
        // Arrange
        ContextoRequestDTO request = ContextoRequestDTO.builder()
                .texto("Contexto con detalles")
                .role("qa")
                .tags("testing,qa")
                .build();

        ContextoResponseDTO response = ContextoResponseDTO.builder()
                .id(2L)
                .texto("Contexto con detalles")
                .role("qa")
                .tags("testing,qa")
                .build();

        when(excusaService.crearContexto(any(ContextoRequestDTO.class))).thenReturn(response);

        // Act
        contextoController.crear(request);

        // Assert
        verify(excusaService).crearContexto(request);
    }
}
