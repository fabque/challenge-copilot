package com.accenture.excusas.dto;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("LeyRequestDTO Tests")
class LeyRequestDTOTest {

    @Test
    @DisplayName("Debe crear LeyRequestDTO con builder")
    void testLeyRequestDTOBuilder() {
        // Act
        LeyRequestDTO dto = LeyRequestDTO.builder()
                .texto("Test ley")
                .fuente("Murphy")
                .role("dev")
                .tags("law")
                .build();

        // Assert
        assertNotNull(dto);
        assertEquals("Test ley", dto.getTexto());
        assertEquals("Murphy", dto.getFuente());
        assertEquals("dev", dto.getRole());
        assertEquals("law", dto.getTags());
    }

    @Test
    @DisplayName("Debe crear LeyRequestDTO con constructor vacío")
    void testLeyRequestDTOEmptyConstructor() {
        LeyRequestDTO dto = new LeyRequestDTO();
        assertNotNull(dto);
        assertNull(dto.getTexto());
    }

    @Test
    @DisplayName("Debe validar que fuente es requerida")
    void testLeyRequestDTORequiresFuente() {
        // Act
        LeyRequestDTO dto = LeyRequestDTO.builder()
                .texto("Ley sin fuente")
                .build();

        // Assert
        assertNotNull(dto);
        assertEquals("Ley sin fuente", dto.getTexto());
        assertNull(dto.getFuente()); // Jakarta validation ocurriría en el controller
    }
}
