package com.gestion.gestionretours.controller;

import com.gestion.gestionretours.entity.HistoriqueRetour;
import com.gestion.gestionretours.service.HistoriqueRetourService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/historiques")
@CrossOrigin("*")
public class HistoriqueRetourController {

    private final HistoriqueRetourService historiqueRetourService;

    public HistoriqueRetourController(HistoriqueRetourService historiqueRetourService) {
        this.historiqueRetourService = historiqueRetourService;
    }

    @GetMapping
    public List<HistoriqueRetour> getAllHistoriques() {
        return historiqueRetourService.getAllHistoriques();
    }

    @GetMapping("/{id}")
    public Optional<HistoriqueRetour> getHistoriqueById(@PathVariable Long id) {
        return historiqueRetourService.getHistoriqueById(id);
    }

    @PostMapping
    public HistoriqueRetour createHistorique(@RequestBody HistoriqueRetour historiqueRetour) {
        return historiqueRetourService.createHistorique(historiqueRetour);
    }

    @PutMapping("/{id}")
    public HistoriqueRetour updateHistorique(@PathVariable Long id, @RequestBody HistoriqueRetour historiqueRetour) {
        return historiqueRetourService.updateHistorique(id, historiqueRetour);
    }

    @DeleteMapping("/{id}")
    public void deleteHistorique(@PathVariable Long id) {
        historiqueRetourService.deleteHistorique(id);
    }
}