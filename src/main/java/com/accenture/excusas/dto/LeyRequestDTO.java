package com.accenture.excusas.dto;

import lombok.*;
import jakarta.validation.constraints.NotBlank;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LeyRequestDTO {
    @NotBlank(message = "El texto es requerido")
    private String texto;

    @NotBlank(message = "La fuente es requerida")
    private String fuente;

    private String role;
    private String tags;
}
