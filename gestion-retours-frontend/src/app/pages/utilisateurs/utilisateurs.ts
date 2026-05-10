import { Component, OnInit, ChangeDetectorRef } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { UtilisateurService } from '../../services/utilisateur';
import { Utilisateur } from '../../models/utilisateur';

@Component({
  selector: 'app-utilisateurs',
  standalone: true,
  imports: [CommonModule, FormsModule],
  templateUrl: './utilisateurs.html',
  styleUrl: './utilisateurs.css'
})
export class Utilisateurs implements OnInit {

  utilisateurs: Utilisateur[] = [];

  utilisateur: Utilisateur = {
    nom: '',
    email: '',
    role: ''
  };

  modeModification = false;
  idUtilisateurModification?: number;

  constructor(
    private utilisateurService: UtilisateurService,
    private cdr: ChangeDetectorRef
  ) {}

  ngOnInit(): void {
    this.getUtilisateurs();
  }

  getUtilisateurs(): void {
    this.utilisateurService.getAll().subscribe(data => {
      this.utilisateurs = data;
      this.cdr.detectChanges();
    });
  }

  enregistrerUtilisateur(): void {
  const utilisateurAEnvoyer = {
    nom: this.utilisateur.nom,
    email: this.utilisateur.email,
    role: this.utilisateur.role
  };

  if (this.modeModification && this.idUtilisateurModification) {
    this.utilisateurService.update(this.idUtilisateurModification, utilisateurAEnvoyer).subscribe({
      next: () => {
        this.getUtilisateurs();
        this.viderFormulaire();
      },
      error: (error) => {
        console.log(error);
        alert('Erreur lors de la modification');
      }
    });
  } else {
    this.utilisateurService.add(utilisateurAEnvoyer).subscribe({
      next: () => {
        this.getUtilisateurs();
        this.viderFormulaire();
      },
      error: (error) => {
        console.log(error);
        alert('Erreur lors de l’ajout');
      }
    });
  }
}

  modifierUtilisateur(utilisateur: Utilisateur): void {
    this.utilisateur = {
      nom: utilisateur.nom,
      email: utilisateur.email,
      role: utilisateur.role
    };

    this.idUtilisateurModification = utilisateur.id;
    this.modeModification = true;
  }

  supprimerUtilisateur(id?: number): void {
    if (id && confirm('Voulez-vous vraiment supprimer cet utilisateur ?')) {
      this.utilisateurService.delete(id).subscribe({
        next: () => {
          this.getUtilisateurs();
          alert('Utilisateur supprimé avec succès');
        },
        error: () => {
          alert('Impossible de supprimer cet utilisateur. Utilisateur lié à un historique de retour.');
        }
      });
    }
  }

  viderFormulaire(): void {
    this.utilisateur = {
      nom: '',
      email: '',
      role: ''
    };

    this.modeModification = false;
    this.idUtilisateurModification = undefined;
  }
}