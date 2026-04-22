package com.gestion.gestionretours.controller;

import com.gestion.gestionretours.entity.StockProduit;
import com.gestion.gestionretours.service.StockProduitService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api/stocks")
@CrossOrigin("*")
@Tag(name = "Stock Produits", description = "Gestion du stock des produits")
public class StockProduitController {

    private final StockProduitService stockProduitService;

    public StockProduitController(StockProduitService stockProduitService) {
        this.stockProduitService = stockProduitService;
    }

    @GetMapping
    @Operation(summary = "Lister tout le stock")
    public List<StockProduit> getAllStocks() {
        return stockProduitService.getAllStocks();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Récupérer un stock par ID")
    public Optional<StockProduit> getStockById(@PathVariable Long id) {
        return stockProduitService.getStockById(id);
    }

    @PostMapping
    @Operation(summary = "Créer une nouvelle entrée de stock")
    public StockProduit createStock(@Valid @RequestBody StockProduit stockProduit) {
        return stockProduitService.createStock(stockProduit);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Modifier une entrée de stock")
    public StockProduit updateStock(@PathVariable Long id, @Valid @RequestBody StockProduit stockProduit) {
        return stockProduitService.updateStock(id, stockProduit);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Supprimer une entrée de stock")
    public void deleteStock(@PathVariable Long id) {
        stockProduitService.deleteStock(id);
    }
}