package com.gestion.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.gestion.entities.Utilisateur;
import com.gestion.repositories.UtilisateurRepository;

@Service
public class UtilisateurService {

	@Autowired
	private UtilisateurRepository uREP;

	public List<Utilisateur> trouverTousLesUtilisateurs() {
		return uREP.findAll();
	}

	public Utilisateur trouverUtilisateurParId(int idUtilisateur) {
		return uREP.findById(idUtilisateur).orElseThrow(
				() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Utilisateur non trouvé avec cet ID"));
	}

	public List<Utilisateur> trouverUtilisateurParNom(String nom) {
		return uREP.findByNomContaining(nom);
	}

	public List<Utilisateur> trouverUtilisateurParRole(String role) {
		return uREP.findByRole(role);
	}

	public ResponseEntity<String> ajouterUtilisateur(Utilisateur utilisateur) {
		uREP.save(utilisateur);
		return ResponseEntity.ok("Utilisateur ajouté avec succès");
	}

	public ResponseEntity<String> mettreAJourUtilisateur(int idUtilisateur, Utilisateur utilisateurModifie) {
		uREP.findById(idUtilisateur).ifPresentOrElse(
				utilisateur -> {
					utilisateur.setNom(utilisateurModifie.getNom());
					utilisateur.setEmail(utilisateurModifie.getEmail());
					utilisateur.setRole(utilisateurModifie.getRole());
					uREP.save(utilisateur);
				},
				() -> {
					throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Utilisateur non trouvé avec cet ID");
				});
		return ResponseEntity.ok("Utilisateur mis à jour avec succès");
	}

	public ResponseEntity<String> supprimerUtilisateur(int idUtilisateur) {
		uREP.findById(idUtilisateur).ifPresentOrElse(
				utilisateur -> uREP.deleteById(idUtilisateur),
				() -> {
					throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Utilisateur non trouvé avec cet ID");
				});
		return ResponseEntity.ok("Utilisateur supprimé avec succès");
	}
}