package com.accenture.excusas.model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Meme Entity Tests")
class MemeTest {

    @Test
    @DisplayName("Debe crear Meme con builder")
    void testMemeBuilder() {
        // Act
        Meme meme = Meme.builder()
                .id(1L)
                .texto("Test meme")
                .fuente("dev-memes")
                .tags("humor,dev")
                .build();

        // Assert
        assertNotNull(meme);
        assertEquals(1L, meme.getId());
        assertEquals("Test meme", meme.getTexto());
        assertEquals("dev-memes", meme.getFuente());
        assertEquals("humor,dev", meme.getTags());
    }

    @Test
    @DisplayName("Debe crear Meme con constructor vacío")
    void testMemeEmptyConstructor() {
        Meme meme = new Meme();
        assertNotNull(meme);
        assertNull(meme.getId());
    }

    @Test
    @DisplayName("Debe establecer y obtener propiedades")
    void testMemeGettersSetters() {
        // Arrange
        Meme meme = new Meme();

        // Act
        meme.setId(5L);
        meme.setTexto("Nuevo meme");
        meme.setFuente("argento-memes");

        // Assert
        assertEquals(5L, meme.getId());
        assertEquals("Nuevo meme", meme.getTexto());
        assertEquals("argento-memes", meme.getFuente());
    }
}
