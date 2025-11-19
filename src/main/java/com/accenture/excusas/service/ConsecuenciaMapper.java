package com.accenture.excusas.service;

import com.accenture.excusas.dto.ConsecuenciaRequestDTO;
import com.accenture.excusas.dto.ConsecuenciaResponseDTO;
import com.accenture.excusas.model.Consecuencia;

public class ConsecuenciaMapper {
    private ConsecuenciaMapper() {}

    public static Consecuencia toEntity(ConsecuenciaRequestDTO dto) {
        if (dto == null) return null;
        return Consecuencia.builder()
                .texto(dto.getTexto())
                .role(dto.getRole())
                .tags(dto.getTags())
                .build();
    }

    public static ConsecuenciaResponseDTO toResponse(Consecuencia entity) {
        if (entity == null) return null;
        return ConsecuenciaResponseDTO.builder()
                .id(entity.getId())
                .texto(entity.getTexto())
                .role(entity.getRole())
                .tags(entity.getTags())
                .build();
    }
}
