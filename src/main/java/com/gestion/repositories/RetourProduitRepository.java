package com.gestion.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gestion.entities.RetourProduit;

public interface RetourProduitRepository extends JpaRepository<RetourProduit, Integer> {

	List<RetourProduit> findByProduitContaining(String produit);

	List<RetourProduit> findByClientContaining(String client);

	List<RetourProduit> findByEtatTraitement(String etatTraitement);
}