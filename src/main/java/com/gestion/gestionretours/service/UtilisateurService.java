package com.gestion.gestionretours.service;

import com.gestion.gestionretours.entity.Utilisateur;
import com.gestion.gestionretours.repository.UtilisateurRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UtilisateurService {

    private final UtilisateurRepository utilisateurRepository;

    public UtilisateurService(UtilisateurRepository utilisateurRepository) {
        this.utilisateurRepository = utilisateurRepository;
    }

    public List<Utilisateur> getAllUtilisateurs() {
        return utilisateurRepository.findAll();
    }

    public Optional<Utilisateur> getUtilisateurById(Long id) {
        return utilisateurRepository.findById(id);
    }

    public Utilisateur createUtilisateur(Utilisateur utilisateur) {
        return utilisateurRepository.save(utilisateur);
    }

    public Utilisateur updateUtilisateur(Long id, Utilisateur utilisateurDetails) {
        Utilisateur utilisateur = utilisateurRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Utilisateur introuvable avec l'id : " + id));

        utilisateur.setNom(utilisateurDetails.getNom());
        utilisateur.setEmail(utilisateurDetails.getEmail());
        utilisateur.setRole(utilisateurDetails.getRole());

        return utilisateurRepository.save(utilisateur);
    }

    public void deleteUtilisateur(Long id) {
        utilisateurRepository.deleteById(id);
    }
}	