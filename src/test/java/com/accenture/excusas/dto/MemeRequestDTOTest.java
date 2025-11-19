package com.accenture.excusas.dto;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("MemeRequestDTO Tests")
class MemeRequestDTOTest {

    @Test
    @DisplayName("Debe crear MemeRequestDTO con builder")
    void testMemeRequestDTOBuilder() {
        // Act
        MemeRequestDTO dto = MemeRequestDTO.builder()
                .texto("Test meme")
                .fuente("dev-memes")
                .tags("humor")
                .build();

        // Assert
        assertNotNull(dto);
        assertEquals("Test meme", dto.getTexto());
        assertEquals("dev-memes", dto.getFuente());
        assertEquals("humor", dto.getTags());
    }

    @Test
    @DisplayName("Debe crear MemeRequestDTO con constructor vacío")
    void testMemeRequestDTOEmptyConstructor() {
        MemeRequestDTO dto = new MemeRequestDTO();
        assertNotNull(dto);
        assertNull(dto.getTexto());
    }
}
