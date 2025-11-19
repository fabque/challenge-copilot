package com.accenture.excusas.service.mapper;

import com.accenture.excusas.dto.MemeRequestDTO;
import com.accenture.excusas.dto.MemeResponseDTO;
import com.accenture.excusas.model.Meme;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("MemeMapper Tests")
class MemeMapperTest {

    @Test
    @DisplayName("Debe mapear MemeRequestDTO a Meme entity")
    void testToEntity() {
        // Arrange
        MemeRequestDTO dto = MemeRequestDTO.builder()
                .texto("Meme de prueba")
                .fuente("dev-memes")
                .tags("humor,dev")
                .build();

        // Act
        Meme entity = MemeMapper.toEntity(dto);

        // Assert
        assertNotNull(entity);
        assertEquals("Meme de prueba", entity.getTexto());
        assertEquals("dev-memes", entity.getFuente());
        assertEquals("humor,dev", entity.getTags());
    }

    @Test
    @DisplayName("Debe retornar null cuando MemeRequestDTO es null")
    void testToEntityNull() {
        Meme entity = MemeMapper.toEntity(null);
        assertNull(entity);
    }

    @Test
    @DisplayName("Debe mapear Meme entity a MemeResponseDTO")
    void testToResponse() {
        // Arrange
        Meme entity = Meme.builder()
                .id(1L)
                .texto("Meme de prueba")
                .fuente("dev-memes")
                .tags("humor,dev")
                .build();

        // Act
        MemeResponseDTO response = MemeMapper.toResponse(entity);

        // Assert
        assertNotNull(response);
        assertEquals(1L, response.getId());
        assertEquals("Meme de prueba", response.getTexto());
        assertEquals("dev-memes", response.getFuente());
    }

    @Test
    @DisplayName("Debe retornar null cuando Meme entity es null")
    void testToResponseNull() {
        MemeResponseDTO response = MemeMapper.toResponse(null);
        assertNull(response);
    }
}
