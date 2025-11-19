package com.accenture.excusas.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "leyes")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Ley {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String texto;

    @Column(nullable = false)
    private String fuente; // murphy, hofstadter, dilbert, etc.

    @Column
    private String role;

    @Column
    private String tags;
}
