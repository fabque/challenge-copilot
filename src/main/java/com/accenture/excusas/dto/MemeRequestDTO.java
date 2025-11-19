package com.accenture.excusas.dto;

import lombok.*;
import jakarta.validation.constraints.NotBlank;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MemeRequestDTO {
    @NotBlank(message = "El texto es requerido")
    private String texto;

    private String fuente;
    private String tags;
}
