import { Component, OnInit, ChangeDetectorRef } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';

import { StockProduit } from '../../models/stock-produit';
import { StockProduitService } from '../../services/stock-produit';

@Component({
  selector: 'app-stock',
  standalone: true,
  imports: [CommonModule, FormsModule],
  templateUrl: './stock.html',
  styleUrl: './stock.css'
})
export class Stock implements OnInit {

  stocks: StockProduit[] = [];

  stockProduit: StockProduit = {
    produit: '',
    quantite: 0
  };

  modeModification = false;
  idStockModification?: number;

  constructor(
    private stockProduitService: StockProduitService,
    private cdr: ChangeDetectorRef
  ) {}

  ngOnInit(): void {
    this.getStocks();
  }

  getStocks(): void {
    this.stockProduitService.getAll().subscribe(data => {
      this.stocks = data;
      this.cdr.detectChanges();
    });
  }

  enregistrerStock(): void {
    const stockAEnvoyer = {
      produit: this.stockProduit.produit,
      quantite: this.stockProduit.quantite
    };

    if (this.modeModification && this.idStockModification) {
      this.stockProduitService.update(this.idStockModification, stockAEnvoyer).subscribe(() => {
        this.getStocks();
        this.viderFormulaire();
      });
    } else {
      this.stockProduitService.add(stockAEnvoyer).subscribe(() => {
        this.getStocks();
        this.viderFormulaire();
      });
    }
  }

  modifierStock(stock: StockProduit): void {
    this.stockProduit = {
      produit: stock.produit,
      quantite: stock.quantite
    };

    this.idStockModification = stock.id;
    this.modeModification = true;
  }

  // supprimerStock(id?: number): void {
  //   if (id && confirm('Voulez-vous vraiment supprimer ce produit du stock ?')) {
  //     this.stockProduitService.delete(id).subscribe({
  //       next: () => {
  //         this.getStocks();
  //         alert('Produit supprimé du stock avec succès');
  //       },
  //       error: () => {
  //         alert('Impossible de supprimer ce produit du stock');
  //       }
  //     });
  //   }
  // }

  viderFormulaire(): void {
    this.stockProduit = {
      produit: '',
      quantite: 0
    };

    this.modeModification = false;
    this.idStockModification = undefined;
  }
}