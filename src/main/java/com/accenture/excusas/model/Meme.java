package com.accenture.excusas.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "memes")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Meme {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String texto;

    @Column
    private String fuente; // dev-memes, argento-memes, etc.

    @Column
    private String tags;
}
