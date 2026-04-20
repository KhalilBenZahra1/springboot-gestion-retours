package com.gestion.gestionretours.repository;

import com.gestion.gestionretours.entity.HistoriqueRetour;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HistoriqueRetourRepository extends JpaRepository<HistoriqueRetour, Long> {
}