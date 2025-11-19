package com.accenture.excusas.model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Contexto Entity Tests")
class ContextoTest {

    @Test
    @DisplayName("Debe crear Contexto con builder")
    void testContextoBuilder() {
        // Act
        Contexto contexto = Contexto.builder()
                .id(1L)
                .texto("Test contexto")
                .role("dev")
                .tags("test,dev")
                .build();

        // Assert
        assertNotNull(contexto);
        assertEquals(1L, contexto.getId());
        assertEquals("Test contexto", contexto.getTexto());
        assertEquals("dev", contexto.getRole());
        assertEquals("test,dev", contexto.getTags());
    }

    @Test
    @DisplayName("Debe crear Contexto con constructor vacío")
    void testContextoEmptyConstructor() {
        // Act
        Contexto contexto = new Contexto();

        // Assert
        assertNotNull(contexto);
        assertNull(contexto.getId());
        assertNull(contexto.getTexto());
    }

    @Test
    @DisplayName("Debe establecer y obtener propiedades")
    void testContextoGettersSetters() {
        // Arrange
        Contexto contexto = new Contexto();

        // Act
        contexto.setId(2L);
        contexto.setTexto("Nuevo texto");
        contexto.setRole("qa");
        contexto.setTags("testing");

        // Assert
        assertEquals(2L, contexto.getId());
        assertEquals("Nuevo texto", contexto.getTexto());
        assertEquals("qa", contexto.getRole());
        assertEquals("testing", contexto.getTags());
    }
}
