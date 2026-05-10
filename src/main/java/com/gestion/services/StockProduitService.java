package com.gestion.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.gestion.entities.StockProduit;
import com.gestion.repositories.StockProduitRepository;

@Service
public class StockProduitService {

	@Autowired
	private StockProduitRepository spREP;

	public List<StockProduit> trouverTousLesStocksProduits() {
		return spREP.findAll();
	}

	public StockProduit trouverStockProduitParId(int idStockProduit) {
		return spREP.findById(idStockProduit).orElseThrow(
				() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
						"Stock produit non trouvé avec cet ID"));
	}

	public List<StockProduit> trouverStockProduitParProduit(String produit) {
		return spREP.findByProduitContaining(produit);
	}

	public ResponseEntity<String> ajouterStockProduit(StockProduit stockProduit) {
		spREP.save(stockProduit);
		return ResponseEntity.ok("Stock produit ajouté avec succès");
	}

	public ResponseEntity<String> mettreAJourStockProduit(int idStockProduit,
			StockProduit stockProduitModifie) {

		spREP.findById(idStockProduit).ifPresentOrElse(
				stockProduit -> {
					stockProduit.setProduit(stockProduitModifie.getProduit());
					stockProduit.setQuantite(stockProduitModifie.getQuantite());
					spREP.save(stockProduit);
				},
				() -> {
					throw new ResponseStatusException(HttpStatus.NOT_FOUND,
							"Stock produit non trouvé avec cet ID");
				});

		return ResponseEntity.ok("Stock produit mis à jour avec succès");
	}

	public ResponseEntity<String> supprimerStockProduit(int idStockProduit) {

		spREP.findById(idStockProduit).ifPresentOrElse(
				stockProduit -> spREP.deleteById(idStockProduit),
				() -> {
					throw new ResponseStatusException(HttpStatus.NOT_FOUND,
							"Stock produit non trouvé avec cet ID");
				});

		return ResponseEntity.ok("Stock produit supprimé avec succès");
	}
}