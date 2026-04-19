package com.gestion.gestionretours.entity;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;

@Entity
@Table(name = "non_conformite")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class NonConformite {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String description;

    @Column(nullable = false)
    private String gravite;

    @Column(nullable = false)
    private LocalDate date;

    @Column(nullable = false)
    private String produit;
}