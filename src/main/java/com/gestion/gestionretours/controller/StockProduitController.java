package com.gestion.gestionretours.controller;

import com.gestion.gestionretours.entity.StockProduit;
import com.gestion.gestionretours.service.StockProduitService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/stocks")
@CrossOrigin("*")
public class StockProduitController {

    private final StockProduitService stockProduitService;

    public StockProduitController(StockProduitService stockProduitService) {
        this.stockProduitService = stockProduitService;
    }

    @GetMapping
    public List<StockProduit> getAllStocks() {
        return stockProduitService.getAllStocks();
    }

    @GetMapping("/{id}")
    public Optional<StockProduit> getStockById(@PathVariable Long id) {
        return stockProduitService.getStockById(id);
    }

    @PostMapping
    public StockProduit createStock(@RequestBody StockProduit stockProduit) {
        return stockProduitService.createStock(stockProduit);
    }

    @PutMapping("/{id}")
    public StockProduit updateStock(@PathVariable Long id, @RequestBody StockProduit stockProduit) {
        return stockProduitService.updateStock(id, stockProduit);
    }

    @DeleteMapping("/{id}")
    public void deleteStock(@PathVariable Long id) {
        stockProduitService.deleteStock(id);
    }
}