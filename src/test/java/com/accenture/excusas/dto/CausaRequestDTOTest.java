package com.accenture.excusas.dto;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("CausaRequestDTO Tests")
class CausaRequestDTOTest {

    @Test
    @DisplayName("Debe crear CausaRequestDTO con builder")
    void testCausaRequestDTOBuilder() {
        // Act
        CausaRequestDTO dto = CausaRequestDTO.builder()
                .texto("Test causa")
                .role("qa")
                .tags("bug")
                .build();

        // Assert
        assertNotNull(dto);
        assertEquals("Test causa", dto.getTexto());
        assertEquals("qa", dto.getRole());
    }

    @Test
    @DisplayName("Debe crear CausaRequestDTO con constructor vacío")
    void testCausaRequestDTOEmptyConstructor() {
        CausaRequestDTO dto = new CausaRequestDTO();
        assertNotNull(dto);
        assertNull(dto.getTexto());
    }
}
