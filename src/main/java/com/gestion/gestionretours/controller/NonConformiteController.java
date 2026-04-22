package com.gestion.gestionretours.controller;

import com.gestion.gestionretours.entity.NonConformite;
import com.gestion.gestionretours.service.NonConformiteService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api/nonconformites")
@CrossOrigin("*")
@Tag(name = "Non-Conformités", description = "Gestion des non-conformités")
public class NonConformiteController {

    private final NonConformiteService nonConformiteService;

    public NonConformiteController(NonConformiteService nonConformiteService) {
        this.nonConformiteService = nonConformiteService;
    }

    @GetMapping
    @Operation(summary = "Lister toutes les non-conformités")
    public List<NonConformite> getAllNonConformites() {
        return nonConformiteService.getAllNonConformites();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Récupérer une non-conformité par ID")
    public Optional<NonConformite> getNonConformiteById(@PathVariable Long id) {
        return nonConformiteService.getNonConformiteById(id);
    }

    @PostMapping
    @Operation(summary = "Créer une nouvelle non-conformité")
    public NonConformite createNonConformite(@Valid @RequestBody NonConformite nonConformite) {
        return nonConformiteService.createNonConformite(nonConformite);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Modifier une non-conformité")
    public NonConformite updateNonConformite(@PathVariable Long id, @Valid @RequestBody NonConformite nonConformite) {
        return nonConformiteService.updateNonConformite(id, nonConformite);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Supprimer une non-conformité")
    public void deleteNonConformite(@PathVariable Long id) {
        nonConformiteService.deleteNonConformite(id);
    }
}