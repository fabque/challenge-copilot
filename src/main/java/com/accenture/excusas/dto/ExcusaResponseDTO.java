package com.accenture.excusas.dto;

import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ExcusaResponseDTO {
    private String contexto;
    private String causa;
    private String consecuencia;
    private String recomendacion;
    private String meme;
    private String ley;
    private String fuente;
    private Long timestamp;
    private String modo; // SIMPLE, CON_MEME, CON_LEY, ULTRA_SHARK
}
