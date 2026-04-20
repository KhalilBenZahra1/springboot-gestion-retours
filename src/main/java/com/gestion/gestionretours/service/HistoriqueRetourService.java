package com.gestion.gestionretours.service;

import com.gestion.gestionretours.entity.HistoriqueRetour;
import com.gestion.gestionretours.repository.HistoriqueRetourRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class HistoriqueRetourService {

    private final HistoriqueRetourRepository historiqueRetourRepository;

    public HistoriqueRetourService(HistoriqueRetourRepository historiqueRetourRepository) {
        this.historiqueRetourRepository = historiqueRetourRepository;
    }

    public List<HistoriqueRetour> getAllHistoriques() {
        return historiqueRetourRepository.findAll();
    }

    public Optional<HistoriqueRetour> getHistoriqueById(Long id) {
        return historiqueRetourRepository.findById(id);
    }

    public HistoriqueRetour createHistorique(HistoriqueRetour historiqueRetour) {
        return historiqueRetourRepository.save(historiqueRetour);
    }

    public HistoriqueRetour updateHistorique(Long id, HistoriqueRetour details) {
        HistoriqueRetour historique = historiqueRetourRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Historique introuvable avec l'id : " + id));

        historique.setRetour(details.getRetour());
        historique.setAction(details.getAction());
        historique.setEmploye(details.getEmploye());
        historique.setDate(details.getDate());

        return historiqueRetourRepository.save(historique);
    }

    public void deleteHistorique(Long id) {
        historiqueRetourRepository.deleteById(id);
    }
}