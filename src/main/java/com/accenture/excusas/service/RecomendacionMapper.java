package com.accenture.excusas.service;

import com.accenture.excusas.dto.RecomendacionRequestDTO;
import com.accenture.excusas.dto.RecomendacionResponseDTO;
import com.accenture.excusas.model.Recomendacion;

public class RecomendacionMapper {
    private RecomendacionMapper() {}

    public static Recomendacion toEntity(RecomendacionRequestDTO dto) {
        if (dto == null) return null;
        return Recomendacion.builder()
                .texto(dto.getTexto())
                .role(dto.getRole())
                .tags(dto.getTags())
                .build();
    }

    public static RecomendacionResponseDTO toResponse(Recomendacion entity) {
        if (entity == null) return null;
        return RecomendacionResponseDTO.builder()
                .id(entity.getId())
                .texto(entity.getTexto())
                .role(entity.getRole())
                .tags(entity.getTags())
                .build();
    }
}
