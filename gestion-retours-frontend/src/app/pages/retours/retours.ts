import { Component, OnInit, ChangeDetectorRef } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';

import { RetourProduit } from '../../models/retour-produit';
import { RetourProduitService } from '../../services/retour-produit';

import { Utilisateur } from '../../models/utilisateur';
import { UtilisateurService } from '../../services/utilisateur';

@Component({
  selector: 'app-retours',
  standalone: true,
  imports: [CommonModule, FormsModule],
  templateUrl: './retours.html',
  styleUrl: './retours.css'
})
export class Retours implements OnInit {

  retours: RetourProduit[] = [];
  utilisateurs: Utilisateur[] = [];

  idUtilisateurSelectionne?: number;
  decisionSelectionnee: string = '';

  retour: RetourProduit = {
    produit: '',
    client: '',
    raison: '',
    etatTraitement: 'EN_ATTENTE'
  };

  modeModification = false;
  idRetourModification?: number;

  constructor(
    private retourProduitService: RetourProduitService,
    private utilisateurService: UtilisateurService,
    private cdr: ChangeDetectorRef
  ) {}

  ngOnInit(): void {
    this.getRetours();
    this.getUtilisateurs();
  }

  getRetours(): void {
    this.retourProduitService.getAll().subscribe(data => {
      this.retours = data.filter(retour => retour.etatTraitement !== 'NON_CONFORME');
      this.cdr.detectChanges();
    });
  }

  getUtilisateurs(): void {
    this.utilisateurService.getAll().subscribe(data => {
      this.utilisateurs = data.filter(utilisateur => utilisateur.role === 'QUALITE');
      this.cdr.detectChanges();
    });
  }

  enregistrerRetour(): void {
    const retourAEnvoyer = {
      produit: this.retour.produit,
      client: this.retour.client,
      raison: this.retour.raison,
      etatTraitement: this.retour.etatTraitement,
      date: new Date().toISOString()
    };

    if (this.modeModification && this.idRetourModification) {
      this.retourProduitService.update(this.idRetourModification, retourAEnvoyer).subscribe(() => {
        this.getRetours();
        this.viderFormulaire();
      });
    } else {
      this.retourProduitService.add(retourAEnvoyer).subscribe(() => {
        this.getRetours();
        this.viderFormulaire();
      });
    }
  }

  modifierRetour(retour: RetourProduit): void {
    this.retour = {
      produit: retour.produit,
      client: retour.client,
      raison: retour.raison,
      etatTraitement: retour.etatTraitement
    };

    this.idRetourModification = retour.id;
    this.modeModification = true;
  }

  supprimerRetour(id?: number): void {
    if (id && confirm('Voulez-vous vraiment supprimer ce retour ?')) {
      this.retourProduitService.delete(id).subscribe({
        next: () => {
          this.getRetours();
          alert('Retour supprimé avec succès');
        },
        error: () => {
          alert('Impossible de supprimer ce retour car il est peut-être lié à une non-conformité ou à un historique.');
        }
      });
    }
  }

  traiterRetour(idRetourProduit?: number): void {

    if (!idRetourProduit) {
      return;
    }

    if (!this.idUtilisateurSelectionne) {
      alert('Veuillez sélectionner un utilisateur');
      return;
    }

    if (!this.decisionSelectionnee) {
      alert('Veuillez sélectionner une décision');
      return;
    }

    this.retourProduitService.traiterRetour(
      idRetourProduit,
      this.idUtilisateurSelectionne,
      this.decisionSelectionnee
    ).subscribe({
      next: () => {
        alert('Retour traité avec succès');
        this.getRetours();
        this.idUtilisateurSelectionne = undefined;
        this.decisionSelectionnee = '';
      },
      error: () => {
        alert('Erreur lors du traitement du retour');
      }
    });
  }

  viderFormulaire(): void {
    this.retour = {
      produit: '',
      client: '',
      raison: '',
      etatTraitement: 'EN_ATTENTE'
    };

    this.modeModification = false;
    this.idRetourModification = undefined;
  }
}