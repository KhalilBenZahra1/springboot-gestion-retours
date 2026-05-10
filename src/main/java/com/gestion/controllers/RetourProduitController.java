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

import com.gestion.dto.RetourProduitDTO;
import com.gestion.entities.RetourProduit;
import com.gestion.mapper.RetourProduitMapper;
import com.gestion.services.RetourProduitService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/retourProduit")
@CrossOrigin(origins = "http://localhost:4200")
public class RetourProduitController {

	@Autowired
	private RetourProduitService rpSER;

	@Autowired
	private RetourProduitMapper retourProduitMapper;

	@GetMapping("/tous")
	public List<RetourProduitDTO> getTousLesRetoursProduits() {
		return retourProduitMapper.toListDTO(rpSER.trouverTousLesRetoursProduits());
	}

	@GetMapping("/{idRetourProduit}")
	public RetourProduitDTO getRetourProduitParId(@PathVariable int idRetourProduit) {
		return retourProduitMapper.toDTO(rpSER.trouverRetourProduitParId(idRetourProduit));
	}

	@GetMapping("/produit/{produit}")
	public List<RetourProduitDTO> getRetourProduitParProduit(@PathVariable String produit) {
		return retourProduitMapper.toListDTO(rpSER.trouverRetourProduitParProduit(produit));
	}

	@GetMapping("/client/{client}")
	public List<RetourProduitDTO> getRetourProduitParClient(@PathVariable String client) {
		return retourProduitMapper.toListDTO(rpSER.trouverRetourProduitParClient(client));
	}

	@GetMapping("/etat/{etatTraitement}")
	public List<RetourProduitDTO> getRetourProduitParEtat(@PathVariable String etatTraitement) {
		return retourProduitMapper.toListDTO(rpSER.trouverRetourProduitParEtat(etatTraitement));
	}

	@PostMapping("/ajouter")
	public ResponseEntity<String> ajouterRetourProduit(
			@Valid @RequestBody RetourProduit retourProduit) {

		return rpSER.ajouterRetourProduit(retourProduit);
	}
	
	@PutMapping("/traiter/{idRetourProduit}/{idUtilisateur}/{decision}")
	public ResponseEntity<String> traiterRetourProduit(
			@PathVariable int idRetourProduit,
			@PathVariable int idUtilisateur,
			@PathVariable String decision) {

		return rpSER.traiterRetourProduit(idRetourProduit, idUtilisateur, decision);
	}

	@PutMapping("/mettreAJour/{idRetourProduit}")
	public ResponseEntity<String> mettreAJourRetourProduit(
			@PathVariable int idRetourProduit,
			@Valid @RequestBody RetourProduit retourProduit) {

		return rpSER.mettreAJourRetourProduit(idRetourProduit, retourProduit);
	}

	@DeleteMapping("/supprimer/{idRetourProduit}")
	public ResponseEntity<String> supprimerRetourProduit(
			@PathVariable int idRetourProduit) {

		return rpSER.supprimerRetourProduit(idRetourProduit);
	}
}