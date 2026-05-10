package com.gestion.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.gestion.entities.NonConformite;
import com.gestion.entities.RetourProduit;
import com.gestion.repositories.NonConformiteRepository;
import com.gestion.repositories.RetourProduitRepository;

@Service
public class NonConformiteService {

	@Autowired
	private NonConformiteRepository ncREP;

	@Autowired
	private RetourProduitRepository rpREP;

	public List<NonConformite> trouverToutesLesNonConformites() {
		return ncREP.findAll();
	}

	public NonConformite trouverNonConformiteParId(int idNonConformite) {
		return ncREP.findById(idNonConformite).orElseThrow(
				() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Non-conformité non trouvée avec cet ID"));
	}

	public List<NonConformite> trouverNonConformiteParGravite(String gravite) {
		return ncREP.findByGravite(gravite);
	}

	public List<NonConformite> trouverNonConformiteParDescription(String description) {
		return ncREP.findByDescriptionContaining(description);
	}

	public List<NonConformite> trouverNonConformiteParProduit(int idRetourProduit) {
		return ncREP.findByProduitId(idRetourProduit);
	}

	public ResponseEntity<String> ajouterNonConformite(NonConformite nonConformite, int idRetourProduit) {

		RetourProduit retourProduit = rpREP.findById(idRetourProduit).orElseThrow(
				() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Retour produit non trouvé avec cet ID"));

		nonConformite.setProduit(retourProduit);
		ncREP.save(nonConformite);

		return ResponseEntity.ok("Non-conformité ajoutée avec succès");
	}

	public ResponseEntity<String> mettreAJourNonConformite(int idNonConformite, NonConformite nonConformiteModifiee) {
		ncREP.findById(idNonConformite).ifPresentOrElse(
				nonConformite -> {
					nonConformite.setDescription(nonConformiteModifiee.getDescription());
					nonConformite.setGravite(nonConformiteModifiee.getGravite());
					nonConformite.setDate(nonConformiteModifiee.getDate());
					ncREP.save(nonConformite);
				},
				() -> {
					throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Non-conformité non trouvée avec cet ID");
				});
		return ResponseEntity.ok("Non-conformité mise à jour avec succès");
	}

	public ResponseEntity<String> supprimerNonConformite(int idNonConformite) {
		ncREP.findById(idNonConformite).ifPresentOrElse(
				nonConformite -> ncREP.deleteById(idNonConformite),
				() -> {
					throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Non-conformité non trouvée avec cet ID");
				});
		return ResponseEntity.ok("Non-conformité supprimée avec succès");
	}
}