package com.accenture.excusas.service;

import com.accenture.excusas.dto.MemeRequestDTO;
import com.accenture.excusas.dto.MemeResponseDTO;
import com.accenture.excusas.model.Meme;

public class MemeMapper {
    private MemeMapper() {}

    public static Meme toEntity(MemeRequestDTO dto) {
        if (dto == null) return null;
        return Meme.builder()
                .texto(dto.getTexto())
                .fuente(dto.getFuente())
                .tags(dto.getTags())
                .build();
    }

    public static MemeResponseDTO toResponse(Meme entity) {
        if (entity == null) return null;
        return MemeResponseDTO.builder()
                .id(entity.getId())
                .texto(entity.getTexto())
                .fuente(entity.getFuente())
                .tags(entity.getTags())
                .build();
    }
}
