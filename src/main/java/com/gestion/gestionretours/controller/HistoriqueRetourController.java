package com.gestion.gestionretours.controller;

import com.gestion.gestionretours.entity.HistoriqueRetour;
import com.gestion.gestionretours.service.HistoriqueRetourService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api/historiques")
@CrossOrigin("*")
@Tag(name = "Historiques Retours", description = "Gestion des historiques des retours")
public class HistoriqueRetourController {

    private final HistoriqueRetourService historiqueRetourService;

    public HistoriqueRetourController(HistoriqueRetourService historiqueRetourService) {
        this.historiqueRetourService = historiqueRetourService;
    }

    @GetMapping
    @Operation(summary = "Lister tous les historiques")
    public List<HistoriqueRetour> getAllHistoriques() {
        return historiqueRetourService.getAllHistoriques();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Récupérer un historique par ID")
    public Optional<HistoriqueRetour> getHistoriqueById(@PathVariable Long id) {
        return historiqueRetourService.getHistoriqueById(id);
    }

    @PostMapping
    @Operation(summary = "Créer un historique")
    public HistoriqueRetour createHistorique(@RequestBody HistoriqueRetour historiqueRetour) {
        return historiqueRetourService.createHistorique(historiqueRetour);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Modifier un historique")
    public HistoriqueRetour updateHistorique(@PathVariable Long id, @RequestBody HistoriqueRetour historiqueRetour) {
        return historiqueRetourService.updateHistorique(id, historiqueRetour);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Supprimer un historique")
    public void deleteHistorique(@PathVariable Long id) {
        historiqueRetourService.deleteHistorique(id);
    }
}