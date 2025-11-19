package com.accenture.excusas.service.mapper;

import com.accenture.excusas.dto.LeyRequestDTO;
import com.accenture.excusas.dto.LeyResponseDTO;
import com.accenture.excusas.model.Ley;

public class LeyMapper {
    private LeyMapper() {}

    public static Ley toEntity(LeyRequestDTO dto) {
        if (dto == null) return null;
        return Ley.builder()
                .texto(dto.getTexto())
                .fuente(dto.getFuente())
                .role(dto.getRole())
                .tags(dto.getTags())
                .build();
    }

    public static LeyResponseDTO toResponse(Ley entity) {
        if (entity == null) return null;
        return LeyResponseDTO.builder()
                .id(entity.getId())
                .texto(entity.getTexto())
                .fuente(entity.getFuente())
                .role(entity.getRole())
                .tags(entity.getTags())
                .build();
    }
}
