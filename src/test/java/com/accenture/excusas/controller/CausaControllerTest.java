package com.accenture.excusas.controller;

import com.accenture.excusas.dto.CausaRequestDTO;
import com.accenture.excusas.dto.CausaResponseDTO;
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
@DisplayName("CausaController Tests")
class CausaControllerTest {

    @Mock
    private ExcusaService excusaService;

    @InjectMocks
    private CausaController causaController;

    @Test
    @DisplayName("Debe crear causa y retornar 201 CREATED")
    void testCrearCausa() {
        // Arrange
        CausaRequestDTO request = CausaRequestDTO.builder()
                .texto("Nueva causa")
                .role("dev")
                .tags("bugs")
                .build();

        CausaResponseDTO response = CausaResponseDTO.builder()
                .id(1L)
                .texto("Nueva causa")
                .role("dev")
                .tags("bugs")
                .build();

        when(excusaService.crearCausa(any(CausaRequestDTO.class))).thenReturn(response);

        // Act
        ResponseEntity<CausaResponseDTO> result = causaController.crear(request);

        // Assert
        assertEquals(HttpStatus.CREATED, result.getStatusCode());
        assertNotNull(result.getBody());
        assertEquals(1L, result.getBody().getId());
        verify(excusaService, times(1)).crearCausa(any(CausaRequestDTO.class));
    }
}
