package com.accenture.excusas.model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Causa Entity Tests")
class CausaTest {

    @Test
    @DisplayName("Debe crear Causa con builder")
    void testCausaBuilder() {
        // Act
        Causa causa = Causa.builder()
                .id(1L)
                .texto("Test causa")
                .role("qa")
                .tags("bug,qa")
                .build();

        // Assert
        assertNotNull(causa);
        assertEquals(1L, causa.getId());
        assertEquals("Test causa", causa.getTexto());
        assertEquals("qa", causa.getRole());
    }

    @Test
    @DisplayName("Debe crear Causa con constructor vacío")
    void testCausaEmptyConstructor() {
        Causa causa = new Causa();
        assertNotNull(causa);
        assertNull(causa.getId());
    }

    @Test
    @DisplayName("Debe establecer y obtener propiedades")
    void testCausaGettersSetters() {
        // Arrange
        Causa causa = new Causa();

        // Act
        causa.setId(2L);
        causa.setTexto("Texto causa");
        causa.setRole("devops");

        // Assert
        assertEquals(2L, causa.getId());
        assertEquals("Texto causa", causa.getTexto());
        assertEquals("devops", causa.getRole());
    }
}
