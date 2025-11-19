package com.accenture.excusas.service.mapper;

import com.accenture.excusas.dto.RecomendacionRequestDTO;
import com.accenture.excusas.dto.RecomendacionResponseDTO;
import com.accenture.excusas.model.Recomendacion;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("RecomendacionMapper Tests")
class RecomendacionMapperTest {

    @Test
    @DisplayName("Debe mapear RecomendacionRequestDTO a Recomendacion entity")
    void testToEntity() {
        // Arrange
        RecomendacionRequestDTO dto = RecomendacionRequestDTO.builder()
                .texto("Recomendación de prueba")
                .role("pm")
                .tags("recommendation,pm")
                .build();

        // Act
        Recomendacion entity = RecomendacionMapper.toEntity(dto);

        // Assert
        assertNotNull(entity);
        assertEquals("Recomendación de prueba", entity.getTexto());
        assertEquals("pm", entity.getRole());
    }

    @Test
    @DisplayName("Debe retornar null cuando RecomendacionRequestDTO es null")
    void testToEntityNull() {
        Recomendacion entity = RecomendacionMapper.toEntity(null);
        assertNull(entity);
    }

    @Test
    @DisplayName("Debe mapear Recomendacion entity a RecomendacionResponseDTO")
    void testToResponse() {
        // Arrange
        Recomendacion entity = Recomendacion.builder()
                .id(1L)
                .texto("Recomendación de prueba")
                .role("pm")
                .tags("recommendation,pm")
                .build();

        // Act
        RecomendacionResponseDTO response = RecomendacionMapper.toResponse(entity);

        // Assert
        assertNotNull(response);
        assertEquals(1L, response.getId());
        assertEquals("Recomendación de prueba", response.getTexto());
    }

    @Test
    @DisplayName("Debe retornar null cuando Recomendacion entity es null")
    void testToResponseNull() {
        RecomendacionResponseDTO response = RecomendacionMapper.toResponse(null);
        assertNull(response);
    }
}
