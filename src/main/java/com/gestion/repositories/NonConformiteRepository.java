package com.gestion.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gestion.entities.NonConformite;

public interface NonConformiteRepository extends JpaRepository<NonConformite, Integer> {

	List<NonConformite> findByGravite(String gravite);

	List<NonConformite> findByDescriptionContaining(String description);

	List<NonConformite> findByProduitId(int idRetourProduit);
}