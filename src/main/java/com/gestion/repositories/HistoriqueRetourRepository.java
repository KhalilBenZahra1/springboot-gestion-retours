package com.gestion.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gestion.entities.HistoriqueRetour;

public interface HistoriqueRetourRepository extends JpaRepository<HistoriqueRetour, Integer> {

	List<HistoriqueRetour> findByRetourId(int idRetourProduit);

	List<HistoriqueRetour> findByEmployeId(int idUtilisateur);

	List<HistoriqueRetour> findByActionContaining(String action);
}