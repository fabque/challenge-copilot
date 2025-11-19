package com.accenture.excusas.service.mapper;

import com.accenture.excusas.dto.CausaRequestDTO;
import com.accenture.excusas.dto.CausaResponseDTO;
import com.accenture.excusas.model.Causa;

public class CausaMapper {
    private CausaMapper() {}

    public static Causa toEntity(CausaRequestDTO dto) {
        if (dto == null) return null;
        return Causa.builder()
                .texto(dto.getTexto())
                .role(dto.getRole())
                .tags(dto.getTags())
                .build();
    }

    public static CausaResponseDTO toResponse(Causa entity) {
        if (entity == null) return null;
        return CausaResponseDTO.builder()
                .id(entity.getId())
                .texto(entity.getTexto())
                .role(entity.getRole())
                .tags(entity.getTags())
                .build();
    }
}
