package com.gestion.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gestion.entities.StockProduit;

public interface StockProduitRepository extends JpaRepository<StockProduit, Integer> {

	StockProduit findByProduit(String produit);

	List<StockProduit> findByProduitContaining(String produit);
}