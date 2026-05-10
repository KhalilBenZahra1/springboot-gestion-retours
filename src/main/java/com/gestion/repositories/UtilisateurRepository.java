package com.gestion.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gestion.entities.Utilisateur;

public interface UtilisateurRepository extends JpaRepository<Utilisateur, Integer> {

	List<Utilisateur> findByNom(String nom);

	List<Utilisateur> findByNomContaining(String nom);

	List<Utilisateur> findByEmail(String email);

	List<Utilisateur> findByRole(String role);
}