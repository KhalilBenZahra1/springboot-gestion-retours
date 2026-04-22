package com.gestion.gestionretours.service;

import com.gestion.gestionretours.entity.HistoriqueRetour;
import com.gestion.gestionretours.entity.StockProduit;
import com.gestion.gestionretours.repository.StockProduitRepository;
import com.gestion.gestionretours.entity.RetourProduit;
import com.gestion.gestionretours.repository.HistoriqueRetourRepository;
import com.gestion.gestionretours.repository.RetourProduitRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class RetourProduitService {

    private final RetourProduitRepository retourProduitRepository;
    private final HistoriqueRetourRepository historiqueRetourRepository;
    private final StockProduitRepository stockProduitRepository;

    public RetourProduitService(RetourProduitRepository retourProduitRepository,
                                HistoriqueRetourRepository historiqueRetourRepository,
                                StockProduitRepository stockProduitRepository) {
        this.retourProduitRepository = retourProduitRepository;
        this.historiqueRetourRepository = historiqueRetourRepository;
        this.stockProduitRepository = stockProduitRepository;
    }

    public List<RetourProduit> getAllRetours() {
        return retourProduitRepository.findAll();
    }

    public Optional<RetourProduit> getRetourById(Long id) {
        return retourProduitRepository.findById(id);
    }

    public RetourProduit createRetour(RetourProduit retourProduit) {

        retourProduit.setDate(LocalDate.now());
        retourProduit.setEtatTraitement("En attente");

        RetourProduit saved = retourProduitRepository.save(retourProduit);

        HistoriqueRetour historique = new HistoriqueRetour();
        historique.setRetour("Retour ID : " + saved.getId());
        historique.setAction("Création du retour");
        historique.setEmploye("Système");
        historique.setDate(LocalDate.now());

        historiqueRetourRepository.save(historique);

        return saved;
    }

    public RetourProduit updateRetour(Long id, RetourProduit details) {
        RetourProduit retour = retourProduitRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Retour produit introuvable avec l'id : " + id));

        retour.setProduit(details.getProduit());
        retour.setClient(details.getClient());
        retour.setRaison(details.getRaison());

        RetourProduit updated = retourProduitRepository.save(retour);

        HistoriqueRetour historique = new HistoriqueRetour();
        historique.setRetour("Retour ID : " + updated.getId());
        historique.setAction("Modification du retour");
        historique.setEmploye("Système");
        historique.setDate(LocalDate.now());
        historiqueRetourRepository.save(historique);

        return updated;
    }

    public void deleteRetour(Long id) {
        retourProduitRepository.deleteById(id);

        HistoriqueRetour historique = new HistoriqueRetour();
        historique.setRetour("Retour ID : " + id);
        historique.setAction("Suppression du retour");
        historique.setEmploye("Système");
        historique.setDate(LocalDate.now());
        historiqueRetourRepository.save(historique);
    }

    public RetourProduit validerRetour(Long id, String employe) {
        RetourProduit retour = retourProduitRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Retour produit introuvable avec l'id : " + id));

        if ("Traité".equals(retour.getEtatTraitement())) {
            throw new RuntimeException("Un retour traité ne peut pas être revalidé.");
        }

        if ("Validé".equals(retour.getEtatTraitement())) {
            throw new RuntimeException("Ce retour est déjà validé.");
        }

        retour.setEtatTraitement("Validé");
        RetourProduit saved = retourProduitRepository.save(retour);

        HistoriqueRetour historique = new HistoriqueRetour();
        historique.setRetour("Retour ID : " + saved.getId());
        historique.setAction("Validation du retour");
        historique.setEmploye(employe);
        historique.setDate(LocalDate.now());
        historiqueRetourRepository.save(historique);

        return saved;
    }

    public RetourProduit traiterRetour(Long id, String employe) {
        RetourProduit retour = retourProduitRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Retour produit introuvable avec l'id : " + id));

        if ("Traité".equals(retour.getEtatTraitement())) {
            throw new RuntimeException("Ce retour est déjà traité.");
        }

        if (!"Validé".equals(retour.getEtatTraitement())) {
            throw new RuntimeException("Seul un retour validé peut être traité.");
        }

        StockProduit stock = stockProduitRepository.findByProduit(retour.getProduit())
                .orElseThrow(() -> new RuntimeException("Aucun stock trouvé pour le produit : " + retour.getProduit()));

        retour.setEtatTraitement("Traité");
        RetourProduit saved = retourProduitRepository.save(retour);

        stock.setQuantite(stock.getQuantite() + 1);
        stockProduitRepository.save(stock);

        HistoriqueRetour historique = new HistoriqueRetour();
        historique.setRetour("Retour ID : " + saved.getId());
        historique.setAction("Traitement du retour et mise à jour du stock");
        historique.setEmploye(employe);
        historique.setDate(LocalDate.now());
        historiqueRetourRepository.save(historique);

        return saved;
    }
}