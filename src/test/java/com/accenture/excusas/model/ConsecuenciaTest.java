package com.accenture.excusas.model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Consecuencia Entity Tests")
class ConsecuenciaTest {

    @Test
    @DisplayName("Debe crear Consecuencia con builder")
    void testConsecuenciaBuilder() {
        // Act
        Consecuencia consecuencia = Consecuencia.builder()
                .id(1L)
                .texto("Test consecuencia")
                .role("devops")
                .tags("incident")
                .build();

        // Assert
        assertNotNull(consecuencia);
        assertEquals(1L, consecuencia.getId());
        assertEquals("Test consecuencia", consecuencia.getTexto());
        assertEquals("devops", consecuencia.getRole());
    }

    @Test
    @DisplayName("Debe crear Consecuencia con constructor vacío")
    void testConsecuenciaEmptyConstructor() {
        Consecuencia consecuencia = new Consecuencia();
        assertNotNull(consecuencia);
        assertNull(consecuencia.getId());
    }

    @Test
    @DisplayName("Debe establecer y obtener propiedades")
    void testConsecuenciaGettersSetters() {
        Consecuencia consecuencia = new Consecuencia();
        consecuencia.setId(3L);
        consecuencia.setTexto("Nueva consecuencia");

        assertEquals(3L, consecuencia.getId());
        assertEquals("Nueva consecuencia", consecuencia.getTexto());
    }
}
