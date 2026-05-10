package com.gestion.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gestion.dto.StockProduitDTO;
import com.gestion.entities.StockProduit;
import com.gestion.mapper.StockProduitMapper;
import com.gestion.services.StockProduitService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/stockProduit")
@CrossOrigin(origins = "http://localhost:4200")
public class StockProduitController {

	@Autowired
	private StockProduitService spSER;

	@Autowired
	private StockProduitMapper stockProduitMapper;

	@GetMapping("/tous")
	public List<StockProduitDTO> getTousLesStocksProduits() {
		return stockProduitMapper.toListDTO(spSER.trouverTousLesStocksProduits());
	}

	@GetMapping("/{idStockProduit}")
	public StockProduitDTO getStockProduitParId(@PathVariable int idStockProduit) {
		return stockProduitMapper.toDTO(spSER.trouverStockProduitParId(idStockProduit));
	}

	@GetMapping("/produit/{produit}")
	public List<StockProduitDTO> getStockProduitParProduit(@PathVariable String produit) {
		return stockProduitMapper.toListDTO(spSER.trouverStockProduitParProduit(produit));
	}

	@PostMapping("/ajouter")
	public ResponseEntity<String> ajouterStockProduit(
			@Valid @RequestBody StockProduit stockProduit) {

		return spSER.ajouterStockProduit(stockProduit);
	}

	@PutMapping("/mettreAJour/{idStockProduit}")
	public ResponseEntity<String> mettreAJourStockProduit(
			@PathVariable int idStockProduit,
			@Valid @RequestBody StockProduit stockProduit) {

		return spSER.mettreAJourStockProduit(idStockProduit, stockProduit);
	}

	@DeleteMapping("/supprimer/{idStockProduit}")
	public ResponseEntity<String> supprimerStockProduit(
			@PathVariable int idStockProduit) {

		return spSER.supprimerStockProduit(idStockProduit);
	}
}