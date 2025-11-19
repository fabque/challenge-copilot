package com.accenture.excusas.controller;

import com.accenture.excusas.dto.LeyRequestDTO;
import com.accenture.excusas.dto.LeyResponseDTO;
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
@DisplayName("LeyController Tests")
class LeyControllerTest {

    @Mock
    private ExcusaService excusaService;

    @InjectMocks
    private LeyController leyController;

    @Test
    @DisplayName("Debe crear ley y retornar 201 CREATED")
    void testCrearLey() {
        // Arrange
        LeyRequestDTO request = LeyRequestDTO.builder()
                .texto("Nueva ley")
                .fuente("Murphy")
                .role("dev")
                .tags("law")
                .build();

        LeyResponseDTO response = LeyResponseDTO.builder()
                .id(1L)
                .texto("Nueva ley")
                .fuente("Murphy")
                .role("dev")
                .tags("law")
                .build();

        when(excusaService.crearLey(any(LeyRequestDTO.class))).thenReturn(response);

        // Act
        ResponseEntity<LeyResponseDTO> result = leyController.crear(request);

        // Assert
        assertEquals(HttpStatus.CREATED, result.getStatusCode());
        assertNotNull(result.getBody());
        assertEquals("Murphy", result.getBody().getFuente());
        verify(excusaService, times(1)).crearLey(any(LeyRequestDTO.class));
    }
}
