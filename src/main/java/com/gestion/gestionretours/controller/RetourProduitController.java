package com.gestion.gestionretours.controller;

import com.gestion.gestionretours.entity.RetourProduit;
import com.gestion.gestionretours.service.RetourProduitService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/retours")
@CrossOrigin("*")
public class RetourProduitController {

    private final RetourProduitService retourProduitService;

    public RetourProduitController(RetourProduitService retourProduitService) {
        this.retourProduitService = retourProduitService;
    }

    @GetMapping
    public List<RetourProduit> getAllRetours() {
        return retourProduitService.getAllRetours();
    }

    @GetMapping("/{id}")
    public Optional<RetourProduit> getRetourById(@PathVariable Long id) {
        return retourProduitService.getRetourById(id);
    }

    @PostMapping
    public RetourProduit createRetour(@RequestBody RetourProduit retourProduit) {
        return retourProduitService.createRetour(retourProduit);
    }

    @PutMapping("/{id}")
    public RetourProduit updateRetour(@PathVariable Long id,
                                      @RequestBody RetourProduit retourProduit) {
        return retourProduitService.updateRetour(id, retourProduit);
    }

    @DeleteMapping("/{id}")
    public void deleteRetour(@PathVariable Long id) {
        retourProduitService.deleteRetour(id);
    }
    
    @PutMapping("/{id}/valider")
    public RetourProduit validerRetour(@PathVariable Long id, @RequestParam String employe) {
        return retourProduitService.validerRetour(id, employe);
    }

    @PutMapping("/{id}/traiter")
    public RetourProduit traiterRetour(@PathVariable Long id, @RequestParam String employe) {
        return retourProduitService.traiterRetour(id, employe);
    }
}