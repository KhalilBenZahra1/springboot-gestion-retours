package com.gestion.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.gestion.entities.HistoriqueRetour;
import com.gestion.entities.RetourProduit;
import com.gestion.entities.Utilisateur;
import com.gestion.repositories.HistoriqueRetourRepository;
import com.gestion.repositories.RetourProduitRepository;
import com.gestion.repositories.UtilisateurRepository;

@Service
public class HistoriqueRetourService {

	@Autowired
	private HistoriqueRetourRepository hREP;

	@Autowired
	private RetourProduitRepository rpREP;

	@Autowired
	private UtilisateurRepository uREP;

	public List<HistoriqueRetour> trouverTousLesHistoriquesRetours() {
		return hREP.findAll();
	}

	public HistoriqueRetour trouverHistoriqueRetourParId(int idHistoriqueRetour) {
		return hREP.findById(idHistoriqueRetour).orElseThrow(
				() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Historique retour non trouvé avec cet ID"));
	}

	public List<HistoriqueRetour> trouverHistoriqueRetourParRetour(int idRetourProduit) {
		return hREP.findByRetourId(idRetourProduit);
	}

	public List<HistoriqueRetour> trouverHistoriqueRetourParEmploye(int idUtilisateur) {
		return hREP.findByEmployeId(idUtilisateur);
	}

	public List<HistoriqueRetour> trouverHistoriqueRetourParAction(String action) {
		return hREP.findByActionContaining(action);
	}

	public ResponseEntity<String> ajouterHistoriqueRetour(HistoriqueRetour historiqueRetour,
			int idRetourProduit, int idUtilisateur) {

		RetourProduit retourProduit = rpREP.findById(idRetourProduit).orElseThrow(
				() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Retour produit non trouvé avec cet ID"));

		Utilisateur utilisateur = uREP.findById(idUtilisateur).orElseThrow(
				() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Utilisateur non trouvé avec cet ID"));

		historiqueRetour.setRetour(retourProduit);
		historiqueRetour.setEmploye(utilisateur);
		hREP.save(historiqueRetour);

		return ResponseEntity.ok("Historique retour ajouté avec succès");
	}

	public ResponseEntity<String> mettreAJourHistoriqueRetour(int idHistoriqueRetour,
			HistoriqueRetour historiqueRetourModifie) {

		hREP.findById(idHistoriqueRetour).ifPresentOrElse(
				historiqueRetour -> {
					historiqueRetour.setAction(historiqueRetourModifie.getAction());
					historiqueRetour.setDate(historiqueRetourModifie.getDate());
					hREP.save(historiqueRetour);
				},
				() -> {
					throw new ResponseStatusException(HttpStatus.NOT_FOUND,
							"Historique retour non trouvé avec cet ID");
				});

		return ResponseEntity.ok("Historique retour mis à jour avec succès");
	}

	public ResponseEntity<String> supprimerHistoriqueRetour(int idHistoriqueRetour) {

		hREP.findById(idHistoriqueRetour).ifPresentOrElse(
				historiqueRetour -> hREP.deleteById(idHistoriqueRetour),
				() -> {
					throw new ResponseStatusException(HttpStatus.NOT_FOUND,
							"Historique retour non trouvé avec cet ID");
				});

		return ResponseEntity.ok("Historique retour supprimé avec succès");
	}
}