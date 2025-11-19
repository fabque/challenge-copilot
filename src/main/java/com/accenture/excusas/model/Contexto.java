package com.accenture.excusas.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "contextos")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Contexto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String texto;

    @Column
    private String role; // dev, qa, devops, pm, etc.

    @Column
    private String tags;
}
