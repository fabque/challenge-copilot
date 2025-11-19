package com.accenture.excusas.service.mapper;

import com.accenture.excusas.dto.ContextoRequestDTO;
import com.accenture.excusas.dto.ContextoResponseDTO;
import com.accenture.excusas.model.Contexto;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("ContextoMapper Tests")
class ContextoMapperTest {

    @Test
    @DisplayName("Debe mapear ContextoRequestDTO a Contexto entity")
    void testToEntity() {
        // Arrange
        ContextoRequestDTO dto = ContextoRequestDTO.builder()
                .texto("Contexto de prueba")
                .role("dev")
                .tags("test,dev")
                .build();

        // Act
        Contexto entity = ContextoMapper.toEntity(dto);

        // Assert
        assertNotNull(entity);
        assertEquals("Contexto de prueba", entity.getTexto());
        assertEquals("dev", entity.getRole());
        assertEquals("test,dev", entity.getTags());
        assertNull(entity.getId());
    }

    @Test
    @DisplayName("Debe retornar null cuando ContextoRequestDTO es null")
    void testToEntityNull() {
        // Act
        Contexto entity = ContextoMapper.toEntity(null);

        // Assert
        assertNull(entity);
    }

    @Test
    @DisplayName("Debe mapear Contexto entity a ContextoResponseDTO")
    void testToResponse() {
        // Arrange
        Contexto entity = Contexto.builder()
                .id(1L)
                .texto("Contexto de prueba")
                .role("dev")
                .tags("test,dev")
                .build();

        // Act
        ContextoResponseDTO response = ContextoMapper.toResponse(entity);

        // Assert
        assertNotNull(response);
        assertEquals(1L, response.getId());
        assertEquals("Contexto de prueba", response.getTexto());
        assertEquals("dev", response.getRole());
        assertEquals("test,dev", response.getTags());
    }

    @Test
    @DisplayName("Debe retornar null cuando Contexto entity es null")
    void testToResponseNull() {
        // Act
        ContextoResponseDTO response = ContextoMapper.toResponse(null);

        // Assert
        assertNull(response);
    }

    @Test
    @DisplayName("Debe mapear entity con campos nulos correctamente")
    void testToResponseWithNullFields() {
        // Arrange
        Contexto entity = Contexto.builder()
                .id(2L)
                .texto("Contexto sin role")
                .role(null)
                .tags(null)
                .build();

        // Act
        ContextoResponseDTO response = ContextoMapper.toResponse(entity);

        // Assert
        assertNotNull(response);
        assertEquals(2L, response.getId());
        assertEquals("Contexto sin role", response.getTexto());
        assertNull(response.getRole());
        assertNull(response.getTags());
    }
}
