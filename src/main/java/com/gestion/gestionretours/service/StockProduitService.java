package com.gestion.gestionretours.service;

import com.gestion.gestionretours.entity.StockProduit;
import com.gestion.gestionretours.repository.StockProduitRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StockProduitService {

    private final StockProduitRepository stockProduitRepository;

    public StockProduitService(StockProduitRepository stockProduitRepository) {
        this.stockProduitRepository = stockProduitRepository;
    }

    public List<StockProduit> getAllStocks() {
        return stockProduitRepository.findAll();
    }

    public Optional<StockProduit> getStockById(Long id) {
        return stockProduitRepository.findById(id);
    }

    public StockProduit createStock(StockProduit stockProduit) {
        return stockProduitRepository.save(stockProduit);
    }

    public StockProduit updateStock(Long id, StockProduit details) {
        StockProduit stock = stockProduitRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Stock introuvable avec l'id : " + id));

        stock.setProduit(details.getProduit());
        stock.setQuantite(details.getQuantite());

        return stockProduitRepository.save(stock);
    }

    public void deleteStock(Long id) {
        stockProduitRepository.deleteById(id);
    }
}