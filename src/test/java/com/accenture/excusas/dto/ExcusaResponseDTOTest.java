package com.accenture.excusas.dto;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("ExcusaResponseDTO Tests")
class ExcusaResponseDTOTest {

    @Test
    @DisplayName("Debe crear ExcusaResponseDTO con builder")
    void testExcusaResponseDTOBuilder() {
        // Act
        ExcusaResponseDTO dto = ExcusaResponseDTO.builder()
                .contexto("Contexto test")
                .causa("Causa test")
                .consecuencia("Consecuencia test")
                .recomendacion("Recomendación test")
                .meme("Meme test")
                .ley("Ley test")
                .fuente("Murphy")
                .timestamp(System.currentTimeMillis())
                .modo("ULTRA_SHARK")
                .build();

        // Assert
        assertNotNull(dto);
        assertEquals("Contexto test", dto.getContexto());
        assertEquals("Causa test", dto.getCausa());
        assertEquals("Consecuencia test", dto.getConsecuencia());
        assertEquals("Recomendación test", dto.getRecomendacion());
        assertEquals("Meme test", dto.getMeme());
        assertEquals("Ley test", dto.getLey());
        assertEquals("Murphy", dto.getFuente());
        assertEquals("ULTRA_SHARK", dto.getModo());
        assertNotNull(dto.getTimestamp());
    }

    @Test
    @DisplayName("Debe crear ExcusaResponseDTO modo SIMPLE sin meme ni ley")
    void testExcusaResponseDTOSimple() {
        // Act
        ExcusaResponseDTO dto = ExcusaResponseDTO.builder()
                .contexto("Contexto")
                .causa("Causa")
                .consecuencia("Consecuencia")
                .recomendacion("Recomendación")
                .modo("SIMPLE")
                .build();

        // Assert
        assertNotNull(dto);
        assertNull(dto.getMeme());
        assertNull(dto.getLey());
        assertEquals("SIMPLE", dto.getModo());
    }

    @Test
    @DisplayName("Debe crear ExcusaResponseDTO modo CON_MEME")
    void testExcusaResponseDTOConMeme() {
        // Act
        ExcusaResponseDTO dto = ExcusaResponseDTO.builder()
                .meme("Meme test")
                .modo("CON_MEME")
                .build();

        // Assert
        assertEquals("Meme test", dto.getMeme());
        assertNull(dto.getLey());
        assertEquals("CON_MEME", dto.getModo());
    }

    @Test
    @DisplayName("Debe crear ExcusaResponseDTO modo CON_LEY")
    void testExcusaResponseDTOConLey() {
        // Act
        ExcusaResponseDTO dto = ExcusaResponseDTO.builder()
                .ley("Ley test")
                .fuente("Dilbert")
                .modo("CON_LEY")
                .build();

        // Assert
        assertNull(dto.getMeme());
        assertEquals("Ley test", dto.getLey());
        assertEquals("Dilbert", dto.getFuente());
        assertEquals("CON_LEY", dto.getModo());
    }

    @Test
    @DisplayName("Debe crear ExcusaResponseDTO con constructor vacío")
    void testExcusaResponseDTOEmptyConstructor() {
        ExcusaResponseDTO dto = new ExcusaResponseDTO();
        assertNotNull(dto);
        assertNull(dto.getContexto());
    }
}
