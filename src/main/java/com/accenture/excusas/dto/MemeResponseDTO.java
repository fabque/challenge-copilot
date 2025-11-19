package com.accenture.excusas.dto;

import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MemeResponseDTO {
    private Long id;
    private String texto;
    private String fuente;
    private String tags;
}
