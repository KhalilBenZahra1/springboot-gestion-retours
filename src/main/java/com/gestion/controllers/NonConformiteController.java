package com.gestion.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gestion.dto.NonConformiteDTO;
import com.gestion.entities.NonConformite;
import com.gestion.mapper.NonConformiteMapper;
import com.gestion.services.NonConformiteService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/nonConformite")
public class NonConformiteController {

	@Autowired
	private NonConformiteService ncSER;

	@Autowired
	private NonConformiteMapper nonConformiteMapper;

	@GetMapping("/toutes")
	public List<NonConformiteDTO> getToutesLesNonConformites() {
		return nonConformiteMapper.toListDTO(ncSER.trouverToutesLesNonConformites());
	}

	@GetMapping("/{idNonConformite}")
	public NonConformiteDTO getNonConformiteParId(@PathVariable int idNonConformite) {
		return nonConformiteMapper.toDTO(ncSER.trouverNonConformiteParId(idNonConformite));
	}

	@GetMapping("/gravite/{gravite}")
	public List<NonConformiteDTO> getNonConformiteParGravite(@PathVariable String gravite) {
		return nonConformiteMapper.toListDTO(ncSER.trouverNonConformiteParGravite(gravite));
	}

	@GetMapping("/description/{description}")
	public List<NonConformiteDTO> getNonConformiteParDescription(@PathVariable String description) {
		return nonConformiteMapper.toListDTO(ncSER.trouverNonConformiteParDescription(description));
	}

	@GetMapping("/produit/{idRetourProduit}")
	public List<NonConformiteDTO> getNonConformiteParProduit(@PathVariable int idRetourProduit) {
		return nonConformiteMapper.toListDTO(ncSER.trouverNonConformiteParProduit(idRetourProduit));
	}

	@PostMapping("/ajouter/{idRetourProduit}")
	public ResponseEntity<String> ajouterNonConformite(@Valid @RequestBody NonConformite nonConformite,
			@PathVariable int idRetourProduit) {
		return ncSER.ajouterNonConformite(nonConformite, idRetourProduit);
	}

	@PutMapping("/mettreAJour/{idNonConformite}")
	public ResponseEntity<String> mettreAJourNonConformite(@PathVariable int idNonConformite,
			@Valid @RequestBody NonConformite nonConformite) {
		return ncSER.mettreAJourNonConformite(idNonConformite, nonConformite);
	}

	@DeleteMapping("/supprimer/{idNonConformite}")
	public ResponseEntity<String> supprimerNonConformite(@PathVariable int idNonConformite) {
		return ncSER.supprimerNonConformite(idNonConformite);
	}
}