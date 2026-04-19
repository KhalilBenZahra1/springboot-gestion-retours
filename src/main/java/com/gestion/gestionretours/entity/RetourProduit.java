package com.gestion.gestionretours.entity;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;

@Entity
@Table(name = "retour_produit")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RetourProduit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String produit;

    @Column(nullable = false)
    private String client;

    @Column(nullable = false)
    private String raison;

    @Column(name = "etat_traitement", nullable = false)
    private String etatTraitement;

    @Column(nullable = false)
    private LocalDate date;
}