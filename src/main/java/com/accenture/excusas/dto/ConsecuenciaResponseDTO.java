package com.accenture.excusas.dto;

import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ConsecuenciaResponseDTO {
    private Long id;
    private String texto;
    private String role;
    private String tags;
}
