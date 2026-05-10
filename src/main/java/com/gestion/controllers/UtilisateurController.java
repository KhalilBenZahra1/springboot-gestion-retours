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

import com.gestion.dto.UtilisateurDTO;
import com.gestion.entities.Utilisateur;
import com.gestion.mapper.UtilisateurMapper;
import com.gestion.services.UtilisateurService;

import jakarta.validation.Valid;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/utilisateur")
public class UtilisateurController {

	@Autowired
	private UtilisateurService uSER;

	@Autowired
	private UtilisateurMapper utilisateurMapper;

	@GetMapping("/tous")
	public List<UtilisateurDTO> getTousLesUtilisateurs() {
		return utilisateurMapper.toListDTO(uSER.trouverTousLesUtilisateurs());
	}

	@GetMapping("/{idUtilisateur}")
	public UtilisateurDTO getUtilisateurParId(@PathVariable int idUtilisateur) {
		return utilisateurMapper.toDTO(uSER.trouverUtilisateurParId(idUtilisateur));
	}

	@GetMapping("/nom/{nom}")
	public List<UtilisateurDTO> getUtilisateurParNom(@PathVariable String nom) {
		return utilisateurMapper.toListDTO(uSER.trouverUtilisateurParNom(nom));
	}

	@GetMapping("/role/{role}")
	public List<UtilisateurDTO> getUtilisateurParRole(@PathVariable String role) {
		return utilisateurMapper.toListDTO(uSER.trouverUtilisateurParRole(role));
	}

	@PostMapping("/ajouter")
	public ResponseEntity<String> ajouterUtilisateur(@Valid @RequestBody Utilisateur utilisateur) {
		return uSER.ajouterUtilisateur(utilisateur);
	}

	@PutMapping("/mettreAJour/{idUtilisateur}")
	public ResponseEntity<String> mettreAJourUtilisateur(@PathVariable int idUtilisateur,
			@Valid @RequestBody Utilisateur utilisateur) {
		return uSER.mettreAJourUtilisateur(idUtilisateur, utilisateur);
	}

	@DeleteMapping("/supprimer/{idUtilisateur}")
	public ResponseEntity<String> supprimerUtilisateur(@PathVariable int idUtilisateur) {
		return uSER.supprimerUtilisateur(idUtilisateur);
	}
}