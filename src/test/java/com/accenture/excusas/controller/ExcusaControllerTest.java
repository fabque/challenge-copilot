package com.accenture.excusas.controller;

import com.accenture.excusas.dto.ExcusaResponseDTO;
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
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@DisplayName("ExcusaController Tests")
class ExcusaControllerTest {

    @Mock
    private ExcusaService excusaService;

    @InjectMocks
    private ExcusaController excusaController;

    private ExcusaResponseDTO crearExcusaTest() {
        return ExcusaResponseDTO.builder()
                .contexto("Contexto test")
                .causa("Causa test")
                .consecuencia("Consecuencia test")
                .recomendacion("Recomendación test")
                .timestamp(System.currentTimeMillis())
                .modo("SIMPLE")
                .build();
    }

    @Test
    @DisplayName("Debe retornar excusa simple con status 200")
    void testGenerarExcusaSimple() {
        // Arrange
        ExcusaResponseDTO excusa = crearExcusaTest();
        when(excusaService.generarExcusaSimple()).thenReturn(excusa);

        // Act
        ResponseEntity<ExcusaResponseDTO> response = excusaController.generarExcusaSimple();

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals("SIMPLE", response.getBody().getModo());
        verify(excusaService, times(1)).generarExcusaSimple();
    }

    @Test
    @DisplayName("Debe retornar excusa con meme")
    void testGenerarExcusaConMeme() {
        // Arrange
        ExcusaResponseDTO excusa = crearExcusaTest();
        excusa.setModo("CON_MEME");
        excusa.setMeme("Meme test");
        when(excusaService.generarExcusaConMeme()).thenReturn(excusa);

        // Act
        ResponseEntity<ExcusaResponseDTO> response = excusaController.generarExcusaConMeme();

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("CON_MEME", response.getBody().getModo());
        assertEquals("Meme test", response.getBody().getMeme());
        verify(excusaService, times(1)).generarExcusaConMeme();
    }

    @Test
    @DisplayName("Debe retornar excusa con ley")
    void testGenerarExcusaConLey() {
        // Arrange
        ExcusaResponseDTO excusa = crearExcusaTest();
        excusa.setModo("CON_LEY");
        excusa.setLey("Ley test");
        excusa.setFuente("Murphy");
        when(excusaService.generarExcusaConLey()).thenReturn(excusa);

        // Act
        ResponseEntity<ExcusaResponseDTO> response = excusaController.generarExcusaConLey();

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("CON_LEY", response.getBody().getModo());
        assertEquals("Ley test", response.getBody().getLey());
        verify(excusaService, times(1)).generarExcusaConLey();
    }

    @Test
    @DisplayName("Debe retornar excusa ULTRA_SHARK")
    void testGenerarExcusaUltraShark() {
        // Arrange
        ExcusaResponseDTO excusa = crearExcusaTest();
        excusa.setModo("ULTRA_SHARK");
        excusa.setMeme("Meme test");
        excusa.setLey("Ley test");
        excusa.setFuente("Dilbert");
        when(excusaService.generarExcusaUltraShark()).thenReturn(excusa);

        // Act
        ResponseEntity<ExcusaResponseDTO> response = excusaController.generarExcusaUltraShark();

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("ULTRA_SHARK", response.getBody().getModo());
        assertNotNull(response.getBody().getMeme());
        assertNotNull(response.getBody().getLey());
        verify(excusaService, times(1)).generarExcusaUltraShark();
    }

    @Test
    @DisplayName("Debe retornar excusa por rol 'dev'")
    void testGenerarExcusaPorRolDev() {
        // Arrange
        ExcusaResponseDTO excusa = crearExcusaTest();
        when(excusaService.generarExcusaPorRol("dev")).thenReturn(excusa);

        // Act
        ResponseEntity<ExcusaResponseDTO> response = excusaController.generarExcusaPorRol("dev");

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        verify(excusaService, times(1)).generarExcusaPorRol("dev");
    }

    @Test
    @DisplayName("Debe retornar excusa por rol 'qa'")
    void testGenerarExcusaPorRolQa() {
        // Arrange
        ExcusaResponseDTO excusa = crearExcusaTest();
        when(excusaService.generarExcusaPorRol("qa")).thenReturn(excusa);

        // Act
        ResponseEntity<ExcusaResponseDTO> response = excusaController.generarExcusaPorRol("qa");

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        verify(excusaService, times(1)).generarExcusaPorRol("qa");
    }

    @Test
    @DisplayName("Debe retornar excusa por rol 'devops'")
    void testGenerarExcusaPorRolDevops() {
        // Arrange
        ExcusaResponseDTO excusa = crearExcusaTest();
        when(excusaService.generarExcusaPorRol("devops")).thenReturn(excusa);

        // Act
        ResponseEntity<ExcusaResponseDTO> response = excusaController.generarExcusaPorRol("devops");

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        verify(excusaService, times(1)).generarExcusaPorRol("devops");
    }

    @Test
    @DisplayName("Debe retornar excusa por rol 'pm'")
    void testGenerarExcusaPorRolPm() {
        // Arrange
        ExcusaResponseDTO excusa = crearExcusaTest();
        when(excusaService.generarExcusaPorRol("pm")).thenReturn(excusa);

        // Act
        ResponseEntity<ExcusaResponseDTO> response = excusaController.generarExcusaPorRol("pm");

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        verify(excusaService, times(1)).generarExcusaPorRol("pm");
    }
}
