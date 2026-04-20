package com.gestion.gestionretours.repository;

import com.gestion.gestionretours.entity.NonConformite;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NonConformiteRepository extends JpaRepository<NonConformite, Long> {
}