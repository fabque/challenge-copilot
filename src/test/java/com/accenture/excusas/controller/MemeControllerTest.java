package com.accenture.excusas.controller;

import com.accenture.excusas.dto.MemeRequestDTO;
import com.accenture.excusas.dto.MemeResponseDTO;
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
@DisplayName("MemeController Tests")
class MemeControllerTest {

    @Mock
    private ExcusaService excusaService;

    @InjectMocks
    private MemeController memeController;

    @Test
    @DisplayName("Debe crear meme y retornar 201 CREATED")
    void testCrearMeme() {
        // Arrange
        MemeRequestDTO request = MemeRequestDTO.builder()
                .texto("Nuevo meme")
                .fuente("dev-memes")
                .tags("humor")
                .build();

        MemeResponseDTO response = MemeResponseDTO.builder()
                .id(1L)
                .texto("Nuevo meme")
                .fuente("dev-memes")
                .tags("humor")
                .build();

        when(excusaService.crearMeme(any(MemeRequestDTO.class))).thenReturn(response);

        // Act
        ResponseEntity<MemeResponseDTO> result = memeController.crear(request);

        // Assert
        assertEquals(HttpStatus.CREATED, result.getStatusCode());
        assertNotNull(result.getBody());
        assertEquals("Nuevo meme", result.getBody().getTexto());
        verify(excusaService, times(1)).crearMeme(any(MemeRequestDTO.class));
    }
}
