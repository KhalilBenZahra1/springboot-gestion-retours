package com.gestion.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.gestion.entities.RetourProduit;
import com.gestion.repositories.RetourProduitRepository;
import java.util.Date;

import com.gestion.entities.HistoriqueRetour;
import com.gestion.entities.StockProduit;
import com.gestion.entities.Utilisateur;
import com.gestion.repositories.HistoriqueRetourRepository;
import com.gestion.repositories.StockProduitRepository;
import com.gestion.repositories.UtilisateurRepository;

@Service
public class RetourProduitService {

	@Autowired
	private RetourProduitRepository rpREP;

	public List<RetourProduit> trouverTousLesRetoursProduits() {
		return rpREP.findAll();
	}

	public RetourProduit trouverRetourProduitParId(int idRetourProduit) {
		return rpREP.findById(idRetourProduit).orElseThrow(
				() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
						"Retour produit non trouvé avec cet ID"));
	}

	public List<RetourProduit> trouverRetourProduitParProduit(String produit) {
		return rpREP.findByProduitContaining(produit);
	}

	public List<RetourProduit> trouverRetourProduitParClient(String client) {
		return rpREP.findByClientContaining(client);
	}

	public List<RetourProduit> trouverRetourProduitParEtat(String etatTraitement) {
		return rpREP.findByEtatTraitement(etatTraitement);
	}

	public ResponseEntity<String> ajouterRetourProduit(RetourProduit retourProduit) {
		rpREP.save(retourProduit);
		return ResponseEntity.ok("Retour produit ajouté avec succès");
	}
	
	public ResponseEntity<String> traiterRetourProduit(int idRetourProduit, int idUtilisateur, String decision) {

		RetourProduit retourProduit = rpREP.findById(idRetourProduit).orElseThrow(
				() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
						"Retour produit non trouvé avec cet ID"));

		Utilisateur utilisateur = uREP.findById(idUtilisateur).orElseThrow(
				() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
						"Utilisateur non trouvé avec cet ID"));

		if (decision.equals("REINTEGRE_STOCK")) {

			StockProduit stockProduit = spREP.findByProduit(retourProduit.getProduit());

			if (stockProduit == null) {
				stockProduit = new StockProduit();
				stockProduit.setProduit(retourProduit.getProduit());
				stockProduit.setQuantite(1);
			} else {
				stockProduit.setQuantite(stockProduit.getQuantite() + 1);
			}

			spREP.save(stockProduit);
			retourProduit.setEtatTraitement("REINTEGRE_STOCK");

		} else if (decision.equals("REFUSE")) {

			retourProduit.setEtatTraitement("REFUSE");

		} else if (decision.equals("NON_REINTEGRE")) {

			retourProduit.setEtatTraitement("NON_REINTEGRE");

		} else {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
					"Décision invalide");
		}

		rpREP.save(retourProduit);

		HistoriqueRetour historiqueRetour = new HistoriqueRetour();
		historiqueRetour.setRetour(retourProduit);
		historiqueRetour.setEmploye(utilisateur);
		historiqueRetour.setAction("Traitement du retour : " + decision);
		historiqueRetour.setDate(new Date());

		hREP.save(historiqueRetour);

		return ResponseEntity.ok("Retour produit traité avec succès");
	}
	

	public ResponseEntity<String> mettreAJourRetourProduit(int idRetourProduit,
			RetourProduit retourProduitModifie) {

		rpREP.findById(idRetourProduit).ifPresentOrElse(
				retourProduit -> {
					retourProduit.setProduit(retourProduitModifie.getProduit());
					retourProduit.setClient(retourProduitModifie.getClient());
					retourProduit.setRaison(retourProduitModifie.getRaison());
					retourProduit.setEtatTraitement(retourProduitModifie.getEtatTraitement());
					retourProduit.setDate(retourProduitModifie.getDate());

					rpREP.save(retourProduit);
				},
				() -> {
					throw new ResponseStatusException(HttpStatus.NOT_FOUND,
							"Retour produit non trouvé avec cet ID");
				});

		return ResponseEntity.ok("Retour produit mis à jour avec succès");
	}

	public ResponseEntity<String> supprimerRetourProduit(int idRetourProduit) {

		rpREP.findById(idRetourProduit).ifPresentOrElse(
				retourProduit -> rpREP.deleteById(idRetourProduit),
				() -> {
					throw new ResponseStatusException(HttpStatus.NOT_FOUND,
							"Retour produit non trouvé avec cet ID");
				});

		return ResponseEntity.ok("Retour produit supprimé avec succès");
	}
	@Autowired
	private StockProduitRepository spREP;

	@Autowired
	private HistoriqueRetourRepository hREP;

	@Autowired
	private UtilisateurRepository uREP;
	
}