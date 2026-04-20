package com.gestion.gestionretours.repository;

import com.gestion.gestionretours.entity.RetourProduit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RetourProduitRepository extends JpaRepository<RetourProduit, Long> {
}