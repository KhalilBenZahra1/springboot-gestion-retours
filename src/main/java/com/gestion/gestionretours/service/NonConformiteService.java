package com.gestion.gestionretours.service;

import com.gestion.gestionretours.entity.NonConformite;
import com.gestion.gestionretours.repository.NonConformiteRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.time.LocalDate;

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
        nonConformite.setDate(LocalDate.now());
        return nonConformiteRepository.save(nonConformite);
    }

    public NonConformite updateNonConformite(Long id, NonConformite details) {
        NonConformite nc = nonConformiteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Non-conformité introuvable avec l'id : " + id));

        nc.setDescription(details.getDescription());
        nc.setGravite(details.getGravite());
        nc.setProduit(details.getProduit());

        return nonConformiteRepository.save(nc);
    }
    
    public void deleteNonConformite(Long id) {
        nonConformiteRepository.deleteById(id);
    }
}