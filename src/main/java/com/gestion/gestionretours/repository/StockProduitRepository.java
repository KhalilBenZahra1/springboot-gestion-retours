package com.gestion.gestionretours.repository;

import com.gestion.gestionretours.entity.StockProduit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface StockProduitRepository extends JpaRepository<StockProduit, Long> {
    Optional<StockProduit> findByProduit(String produit);
}