package com.gestion.gestionretours.controller;

import com.gestion.gestionretours.entity.RetourProduit;
import com.gestion.gestionretours.service.RetourProduitService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api/retours")
@CrossOrigin("*")
@Tag(name = "Retours Produits", description = "Gestion des retours produits")
public class RetourProduitController {

    private final RetourProduitService retourProduitService;

    public RetourProduitController(RetourProduitService retourProduitService) {
        this.retourProduitService = retourProduitService;
    }

    @GetMapping
    @Operation(summary = "Lister tous les retours")
    public List<RetourProduit> getAllRetours() {
        return retourProduitService.getAllRetours();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Récupérer un retour par ID")
    public Optional<RetourProduit> getRetourById(@PathVariable Long id) {
        return retourProduitService.getRetourById(id);
    }

    @PostMapping
    @Operation(summary = "Créer un nouveau retour")
    public RetourProduit createRetour(@Valid @RequestBody RetourProduit retourProduit) {
        return retourProduitService.createRetour(retourProduit);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Modifier un retour")
    public RetourProduit updateRetour(@PathVariable Long id,
                                      @Valid @RequestBody RetourProduit retourProduit) {
        return retourProduitService.updateRetour(id, retourProduit);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Supprimer un retour")
    public void deleteRetour(@PathVariable Long id) {
        retourProduitService.deleteRetour(id);
    }

    @PutMapping("/{id}/valider")
    @Operation(summary = "Valider un retour")
    public RetourProduit validerRetour(@PathVariable Long id, @RequestParam String employe) {
        return retourProduitService.validerRetour(id, employe);
    }

    @PutMapping("/{id}/traiter")
    @Operation(summary = "Traiter un retour")
    public RetourProduit traiterRetour(@PathVariable Long id, @RequestParam String employe) {
        return retourProduitService.traiterRetour(id, employe);
    }
}