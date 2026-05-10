import { Component, OnInit, ChangeDetectorRef } from '@angular/core';
import { CommonModule } from '@angular/common';

import { HistoriqueRetour } from '../../models/historique-retour';
import { HistoriqueRetourService } from '../../services/historique-retour';

@Component({
  selector: 'app-historique',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './historique.html',
  styleUrl: './historique.css'
})
export class Historique implements OnInit {

  historiques: HistoriqueRetour[] = [];

  constructor(
    private historiqueRetourService: HistoriqueRetourService,
    private cdr: ChangeDetectorRef
  ) {}

  ngOnInit(): void {
    this.getHistoriques();
  }

  getHistoriques(): void {
    this.historiqueRetourService.getAll().subscribe(data => {
      this.historiques = data;
      this.cdr.detectChanges();
    });
  }

  supprimerHistorique(id?: number): void {

    if (id && confirm('Voulez-vous vraiment supprimer cet historique ?')) {

      this.historiqueRetourService.delete(id).subscribe({
        next: () => {
          this.getHistoriques();
          alert('Historique supprimé avec succès');
        },
        error: () => {
          alert('Impossible de supprimer cet historique');
        }
      });

    }

  }

}