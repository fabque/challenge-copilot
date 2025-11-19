package com.accenture.excusas.service.mapper;

import com.accenture.excusas.dto.ConsecuenciaRequestDTO;
import com.accenture.excusas.dto.ConsecuenciaResponseDTO;
import com.accenture.excusas.model.Consecuencia;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("ConsecuenciaMapper Tests")
class ConsecuenciaMapperTest {

    @Test
    @DisplayName("Debe mapear ConsecuenciaRequestDTO a Consecuencia entity")
    void testToEntity() {
        // Arrange
        ConsecuenciaRequestDTO dto = ConsecuenciaRequestDTO.builder()
                .texto("Consecuencia de prueba")
                .role("devops")
                .tags("incident,devops")
                .build();

        // Act
        Consecuencia entity = ConsecuenciaMapper.toEntity(dto);

        // Assert
        assertNotNull(entity);
        assertEquals("Consecuencia de prueba", entity.getTexto());
        assertEquals("devops", entity.getRole());
    }

    @Test
    @DisplayName("Debe retornar null cuando ConsecuenciaRequestDTO es null")
    void testToEntityNull() {
        Consecuencia entity = ConsecuenciaMapper.toEntity(null);
        assertNull(entity);
    }

    @Test
    @DisplayName("Debe mapear Consecuencia entity a ConsecuenciaResponseDTO")
    void testToResponse() {
        // Arrange
        Consecuencia entity = Consecuencia.builder()
                .id(1L)
                .texto("Consecuencia de prueba")
                .role("devops")
                .tags("incident,devops")
                .build();

        // Act
        ConsecuenciaResponseDTO response = ConsecuenciaMapper.toResponse(entity);

        // Assert
        assertNotNull(response);
        assertEquals(1L, response.getId());
        assertEquals("Consecuencia de prueba", response.getTexto());
    }

    @Test
    @DisplayName("Debe retornar null cuando Consecuencia entity es null")
    void testToResponseNull() {
        ConsecuenciaResponseDTO response = ConsecuenciaMapper.toResponse(null);
        assertNull(response);
    }
}
