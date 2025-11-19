package com.accenture.excusas.service.mapper;

import com.accenture.excusas.dto.LeyRequestDTO;
import com.accenture.excusas.dto.LeyResponseDTO;
import com.accenture.excusas.model.Ley;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("LeyMapper Tests")
class LeyMapperTest {

    @Test
    @DisplayName("Debe mapear LeyRequestDTO a Ley entity")
    void testToEntity() {
        // Arrange
        LeyRequestDTO dto = LeyRequestDTO.builder()
                .texto("Ley de prueba")
                .fuente("Murphy")
                .role("dev")
                .tags("ley,prueba")
                .build();

        // Act
        Ley entity = LeyMapper.toEntity(dto);

        // Assert
        assertNotNull(entity);
        assertEquals("Ley de prueba", entity.getTexto());
        assertEquals("Murphy", entity.getFuente());
        assertEquals("dev", entity.getRole());
    }

    @Test
    @DisplayName("Debe retornar null cuando LeyRequestDTO es null")
    void testToEntityNull() {
        Ley entity = LeyMapper.toEntity(null);
        assertNull(entity);
    }

    @Test
    @DisplayName("Debe mapear Ley entity a LeyResponseDTO")
    void testToResponse() {
        // Arrange
        Ley entity = Ley.builder()
                .id(1L)
                .texto("Ley de prueba")
                .fuente("Murphy")
                .role("dev")
                .tags("ley,prueba")
                .build();

        // Act
        LeyResponseDTO response = LeyMapper.toResponse(entity);

        // Assert
        assertNotNull(response);
        assertEquals(1L, response.getId());
        assertEquals("Ley de prueba", response.getTexto());
        assertEquals("Murphy", response.getFuente());
    }

    @Test
    @DisplayName("Debe retornar null cuando Ley entity es null")
    void testToResponseNull() {
        LeyResponseDTO response = LeyMapper.toResponse(null);
        assertNull(response);
    }
}
