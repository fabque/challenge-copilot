package com.accenture.excusas.model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Recomendacion Entity Tests")
class RecomendacionTest {

    @Test
    @DisplayName("Debe crear Recomendacion con builder")
    void testRecomendacionBuilder() {
        // Act
        Recomendacion recomendacion = Recomendacion.builder()
                .id(1L)
                .texto("Test recomendación")
                .role("pm")
                .tags("advice")
                .build();

        // Assert
        assertNotNull(recomendacion);
        assertEquals(1L, recomendacion.getId());
        assertEquals("Test recomendación", recomendacion.getTexto());
        assertEquals("pm", recomendacion.getRole());
    }

    @Test
    @DisplayName("Debe crear Recomendacion con constructor vacío")
    void testRecomendacionEmptyConstructor() {
        Recomendacion recomendacion = new Recomendacion();
        assertNotNull(recomendacion);
        assertNull(recomendacion.getId());
    }

    @Test
    @DisplayName("Debe establecer y obtener propiedades")
    void testRecomendacionGettersSetters() {
        Recomendacion recomendacion = new Recomendacion();
        recomendacion.setId(4L);
        recomendacion.setTexto("Nueva recomendación");

        assertEquals(4L, recomendacion.getId());
        assertEquals("Nueva recomendación", recomendacion.getTexto());
    }
}
