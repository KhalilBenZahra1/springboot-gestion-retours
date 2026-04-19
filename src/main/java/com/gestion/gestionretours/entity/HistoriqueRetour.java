package com.gestion.gestionretours.entity;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;

@Entity
@Table(name = "historique_retour")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class HistoriqueRetour {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String retour;

    @Column(nullable = false)
    private String action;

    @Column(nullable = false)
    private String employe;

    @Column(nullable = false)
    private LocalDate date;
}