package com.accenture.excusas.dto;

import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LeyResponseDTO {
    private Long id;
    private String texto;
    private String fuente;
    private String role;
    private String tags;
}
