package com.accenture.excusas.model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Ley Entity Tests")
class LeyTest {

    @Test
    @DisplayName("Debe crear Ley con builder")
    void testLeyBuilder() {
        // Act
        Ley ley = Ley.builder()
                .id(1L)
                .texto("Test ley")
                .fuente("Murphy")
                .role("dev")
                .tags("law,murphy")
                .build();

        // Assert
        assertNotNull(ley);
        assertEquals(1L, ley.getId());
        assertEquals("Test ley", ley.getTexto());
        assertEquals("Murphy", ley.getFuente());
        assertEquals("dev", ley.getRole());
    }

    @Test
    @DisplayName("Debe crear Ley con constructor vacío")
    void testLeyEmptyConstructor() {
        Ley ley = new Ley();
        assertNotNull(ley);
        assertNull(ley.getId());
    }

    @Test
    @DisplayName("Debe establecer y obtener propiedades")
    void testLeyGettersSetters() {
        // Arrange
        Ley ley = new Ley();

        // Act
        ley.setId(6L);
        ley.setTexto("Nueva ley");
        ley.setFuente("Dilbert");

        // Assert
        assertEquals(6L, ley.getId());
        assertEquals("Nueva ley", ley.getTexto());
        assertEquals("Dilbert", ley.getFuente());
    }

    @Test
    @DisplayName("Debe soportar múltiples fuentes")
    void testLeyMultiplesFuentes() {
        // Act & Assert
        Ley murphy = Ley.builder().fuente("Murphy").build();
        Ley hofstadter = Ley.builder().fuente("Hofstadter").build();
        Ley dilbert = Ley.builder().fuente("Dilbert").build();

        assertEquals("Murphy", murphy.getFuente());
        assertEquals("Hofstadter", hofstadter.getFuente());
        assertEquals("Dilbert", dilbert.getFuente());
    }
}
