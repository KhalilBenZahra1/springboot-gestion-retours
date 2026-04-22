package com.gestion.gestionretours.service;

import com.gestion.gestionretours.entity.NonConformite;
import com.gestion.gestionretours.repository.NonConformiteRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class NonConformiteService {

    private final NonConformiteRepository nonConformiteRepository;

    public NonConformiteService(NonConformiteRepository nonConformiteRepository) {
        this.nonConformiteRepository = nonConformiteRepository;
    }

    public List<NonConformite> getAllNonConformites() {
        return nonConformiteRepository.findAll();
    }

    public Optional<NonConformite> getNonConformiteById(Long id) {
        return nonConformiteRepository.findById(id);
    }

    public NonConformite createNonConformite(NonConformite nonConformite) {
        boolean existeDeja = nonConformiteRepository
                .existsByProduitAndDescriptionAndGraviteAndStatutNot(
                        nonConformite.getProduit(),
                        nonConformite.getDescription(),
                        nonConformite.getGravite(),
                        "Résolue"
                );

        if (existeDeja) {
            throw new RuntimeException("Une non-conformité active identique existe déjà pour ce produit.");
        }

        nonConformite.setDate(LocalDate.now());
        nonConformite.setStatut("Ouverte");

        return nonConformiteRepository.save(nonConformite);
    }

    public NonConformite updateNonConformite(Long id, NonConformite details) {
        NonConformite nc = nonConformiteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Non-conformité introuvable avec l'id : " + id));

        nc.setDescription(details.getDescription());
        nc.setGravite(details.getGravite());
        nc.setProduit(details.getProduit());

        if (details.getStatut() != null && !details.getStatut().isBlank()) {
            String ancienStatut = nc.getStatut();
            String nouveauStatut = details.getStatut();

            boolean transitionValide =
                    (ancienStatut.equals("Ouverte") && (
                            nouveauStatut.equals("Ouverte")
                                    || nouveauStatut.equals("En cours")
                                    || nouveauStatut.equals("Résolue")
                    ))
                    || (ancienStatut.equals("En cours") && (
                            nouveauStatut.equals("En cours")
                                    || nouveauStatut.equals("Résolue")
                    ))
                    || (ancienStatut.equals("Résolue") && nouveauStatut.equals("Résolue"));

            if (!transitionValide) {
                throw new RuntimeException("Transition de statut non autorisée.");
            }

            nc.setStatut(nouveauStatut);
        }

        return nonConformiteRepository.save(nc);
    }

    public void deleteNonConformite(Long id) {
        nonConformiteRepository.deleteById(id);
    }
}