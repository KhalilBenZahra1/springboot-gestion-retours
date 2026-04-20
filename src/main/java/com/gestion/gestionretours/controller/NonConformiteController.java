package com.gestion.gestionretours.controller;

import com.gestion.gestionretours.entity.NonConformite;
import com.gestion.gestionretours.service.NonConformiteService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/nonconformites")
@CrossOrigin("*")
public class NonConformiteController {

    private final NonConformiteService nonConformiteService;

    public NonConformiteController(NonConformiteService nonConformiteService) {
        this.nonConformiteService = nonConformiteService;
    }

    @GetMapping
    public List<NonConformite> getAllNonConformites() {
        return nonConformiteService.getAllNonConformites();
    }

    @GetMapping("/{id}")
    public Optional<NonConformite> getNonConformiteById(@PathVariable Long id) {
        return nonConformiteService.getNonConformiteById(id);
    }

    @PostMapping
    public NonConformite createNonConformite(@RequestBody NonConformite nonConformite) {
        return nonConformiteService.createNonConformite(nonConformite);
    }

    @PutMapping("/{id}")
    public NonConformite updateNonConformite(@PathVariable Long id, @RequestBody NonConformite nonConformite) {
        return nonConformiteService.updateNonConformite(id, nonConformite);
    }

    @DeleteMapping("/{id}")
    public void deleteNonConformite(@PathVariable Long id) {
        nonConformiteService.deleteNonConformite(id);
    }
}