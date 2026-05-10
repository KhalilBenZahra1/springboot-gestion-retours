import { Component, OnInit, ChangeDetectorRef } from '@angular/core';
import { CommonModule } from '@angular/common';

import { RetourProduit } from '../../models/retour-produit';
import { RetourProduitService } from '../../services/retour-produit';

@Component({
  selector: 'app-non-conformites',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './non-conformites.html',
  styleUrl: './non-conformites.css'
})
export class NonConformites implements OnInit {

  retoursNonConformes: RetourProduit[] = [];

  constructor(
    private retourProduitService: RetourProduitService,
    private cdr: ChangeDetectorRef
  ) {}

  ngOnInit(): void {
    this.getRetoursNonConformes();
  }

  getRetoursNonConformes(): void {
    this.retourProduitService.getAll().subscribe(data => {
      this.retoursNonConformes = data.filter(retour => retour.etatTraitement === 'NON_CONFORME');
      this.cdr.detectChanges();
    });
  }

  supprimerRetourNonConforme(id?: number): void {
    if (id && confirm('Voulez-vous vraiment supprimer ce retour non conforme ?')) {
      this.retourProduitService.delete(id).subscribe({
        next: () => {
          this.getRetoursNonConformes();
          alert('Retour non conforme supprimé avec succès');
        },
        error: () => {
          alert('Impossible de supprimer ce retour car il est peut-être lié à un historique.');
        }
      });
    }
  }
}