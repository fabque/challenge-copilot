package com.accenture.excusas.dto;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("ContextoResponseDTO Tests")
class ContextoResponseDTOTest {

    @Test
    @DisplayName("Debe crear ContextoResponseDTO con builder")
    void testContextoResponseDTOBuilder() {
        // Act
        ContextoResponseDTO dto = ContextoResponseDTO.builder()
                .id(1L)
                .texto("Test contexto")
                .role("dev")
                .tags("test")
                .build();

        // Assert
        assertNotNull(dto);
        assertEquals(1L, dto.getId());
        assertEquals("Test contexto", dto.getTexto());
        assertEquals("dev", dto.getRole());
    }

    @Test
    @DisplayName("Debe crear ContextoResponseDTO con constructor vacío")
    void testContextoResponseDTOEmptyConstructor() {
        ContextoResponseDTO dto = new ContextoResponseDTO();
        assertNotNull(dto);
        assertNull(dto.getId());
    }
}
