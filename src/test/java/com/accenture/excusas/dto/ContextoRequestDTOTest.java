package com.accenture.excusas.dto;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("ContextoRequestDTO Tests")
class ContextoRequestDTOTest {

    @Test
    @DisplayName("Debe crear ContextoRequestDTO con builder")
    void testContextoRequestDTOBuilder() {
        // Act
        ContextoRequestDTO dto = ContextoRequestDTO.builder()
                .texto("Test contexto")
                .role("dev")
                .tags("test,dev")
                .build();

        // Assert
        assertNotNull(dto);
        assertEquals("Test contexto", dto.getTexto());
        assertEquals("dev", dto.getRole());
        assertEquals("test,dev", dto.getTags());
    }

    @Test
    @DisplayName("Debe crear ContextoRequestDTO con constructor vacío")
    void testContextoRequestDTOEmptyConstructor() {
        ContextoRequestDTO dto = new ContextoRequestDTO();
        assertNotNull(dto);
        assertNull(dto.getTexto());
    }

    @Test
    @DisplayName("Debe establecer y obtener propiedades")
    void testContextoRequestDTOGettersSetters() {
        // Arrange
        ContextoRequestDTO dto = new ContextoRequestDTO();

        // Act
        dto.setTexto("Nuevo texto");
        dto.setRole("qa");
        dto.setTags("testing");

        // Assert
        assertEquals("Nuevo texto", dto.getTexto());
        assertEquals("qa", dto.getRole());
        assertEquals("testing", dto.getTags());
    }
}
