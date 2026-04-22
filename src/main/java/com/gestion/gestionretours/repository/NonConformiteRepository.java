package com.gestion.gestionretours.repository;

import com.gestion.gestionretours.entity.NonConformite;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NonConformiteRepository extends JpaRepository<NonConformite, Long> {

    boolean existsByProduitAndDescriptionAndGraviteAndStatutNot(
            String produit,
            String description,
            String gravite,
            String statut
    );
}