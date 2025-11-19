package com.accenture.excusas.dto;

import lombok.*;
import jakarta.validation.constraints.NotBlank;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ConsecuenciaRequestDTO {
    @NotBlank(message = "El texto es requerido")
    private String texto;

    private String role;
    private String tags;
}
