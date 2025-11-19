package com.accenture.excusas.service;

import com.accenture.excusas.dto.ContextoRequestDTO;
import com.accenture.excusas.dto.ContextoResponseDTO;
import com.accenture.excusas.model.Contexto;

public class ContextoMapper {
    private ContextoMapper() {}

    public static Contexto toEntity(ContextoRequestDTO dto) {
        if (dto == null) return null;
        return Contexto.builder()
                .texto(dto.getTexto())
                .role(dto.getRole())
                .tags(dto.getTags())
                .build();
    }

    public static ContextoResponseDTO toResponse(Contexto entity) {
        if (entity == null) return null;
        return ContextoResponseDTO.builder()
                .id(entity.getId())
                .texto(entity.getTexto())
                .role(entity.getRole())
                .tags(entity.getTags())
                .build();
    }
}
