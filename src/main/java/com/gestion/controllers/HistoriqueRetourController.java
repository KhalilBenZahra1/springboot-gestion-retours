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

import com.gestion.dto.HistoriqueRetourDTO;
import com.gestion.entities.HistoriqueRetour;
import com.gestion.mapper.HistoriqueRetourMapper;
import com.gestion.services.HistoriqueRetourService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/historiqueRetour")
public class HistoriqueRetourController {

	@Autowired
	private HistoriqueRetourService hSER;

	@Autowired
	private HistoriqueRetourMapper historiqueRetourMapper;

	@GetMapping("/tous")
	public List<HistoriqueRetourDTO> getTousLesHistoriquesRetours() {
		return historiqueRetourMapper.toListDTO(hSER.trouverTousLesHistoriquesRetours());
	}

	@GetMapping("/{idHistoriqueRetour}")
	public HistoriqueRetourDTO getHistoriqueRetourParId(@PathVariable int idHistoriqueRetour) {
		return historiqueRetourMapper.toDTO(hSER.trouverHistoriqueRetourParId(idHistoriqueRetour));
	}

	@GetMapping("/retour/{idRetourProduit}")
	public List<HistoriqueRetourDTO> getHistoriqueRetourParRetour(@PathVariable int idRetourProduit) {
		return historiqueRetourMapper.toListDTO(hSER.trouverHistoriqueRetourParRetour(idRetourProduit));
	}

	@GetMapping("/employe/{idUtilisateur}")
	public List<HistoriqueRetourDTO> getHistoriqueRetourParEmploye(@PathVariable int idUtilisateur) {
		return historiqueRetourMapper.toListDTO(hSER.trouverHistoriqueRetourParEmploye(idUtilisateur));
	}

	@GetMapping("/action/{action}")
	public List<HistoriqueRetourDTO> getHistoriqueRetourParAction(@PathVariable String action) {
		return historiqueRetourMapper.toListDTO(hSER.trouverHistoriqueRetourParAction(action));
	}

	@PostMapping("/ajouter/{idRetourProduit}/{idUtilisateur}")
	public ResponseEntity<String> ajouterHistoriqueRetour(@Valid @RequestBody HistoriqueRetour historiqueRetour,
			@PathVariable int idRetourProduit, @PathVariable int idUtilisateur) {
		return hSER.ajouterHistoriqueRetour(historiqueRetour, idRetourProduit, idUtilisateur);
	}

	@PutMapping("/mettreAJour/{idHistoriqueRetour}")
	public ResponseEntity<String> mettreAJourHistoriqueRetour(@PathVariable int idHistoriqueRetour,
			@Valid @RequestBody HistoriqueRetour historiqueRetour) {
		return hSER.mettreAJourHistoriqueRetour(idHistoriqueRetour, historiqueRetour);
	}

	@DeleteMapping("/supprimer/{idHistoriqueRetour}")
	public ResponseEntity<String> supprimerHistoriqueRetour(@PathVariable int idHistoriqueRetour) {
		return hSER.supprimerHistoriqueRetour(idHistoriqueRetour);
	}
}