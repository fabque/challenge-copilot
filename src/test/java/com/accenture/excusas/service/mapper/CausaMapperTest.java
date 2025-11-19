package com.accenture.excusas.service.mapper;

import com.accenture.excusas.dto.CausaRequestDTO;
import com.accenture.excusas.dto.CausaResponseDTO;
import com.accenture.excusas.model.Causa;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("CausaMapper Tests")
class CausaMapperTest {

    @Test
    @DisplayName("Debe mapear CausaRequestDTO a Causa entity")
    void testToEntity() {
        // Arrange
        CausaRequestDTO dto = CausaRequestDTO.builder()
                .texto("Causa de prueba")
                .role("qa")
                .tags("bug,qa")
                .build();

        // Act
        Causa entity = CausaMapper.toEntity(dto);

        // Assert
        assertNotNull(entity);
        assertEquals("Causa de prueba", entity.getTexto());
        assertEquals("qa", entity.getRole());
        assertEquals("bug,qa", entity.getTags());
    }

    @Test
    @DisplayName("Debe retornar null cuando CausaRequestDTO es null")
    void testToEntityNull() {
        Causa entity = CausaMapper.toEntity(null);
        assertNull(entity);
    }

    @Test
    @DisplayName("Debe mapear Causa entity a CausaResponseDTO")
    void testToResponse() {
        // Arrange
        Causa entity = Causa.builder()
                .id(1L)
                .texto("Causa de prueba")
                .role("qa")
                .tags("bug,qa")
                .build();

        // Act
        CausaResponseDTO response = CausaMapper.toResponse(entity);

        // Assert
        assertNotNull(response);
        assertEquals(1L, response.getId());
        assertEquals("Causa de prueba", response.getTexto());
        assertEquals("qa", response.getRole());
    }

    @Test
    @DisplayName("Debe retornar null cuando Causa entity es null")
    void testToResponseNull() {
        CausaResponseDTO response = CausaMapper.toResponse(null);
        assertNull(response);
    }
}
