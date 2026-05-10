import { RetourProduit } from './retour-produit';
import { Utilisateur } from './utilisateur';

export interface HistoriqueRetour {
  id?: number;
  action: string;
  date: string;
  retour?: RetourProduit;
  employe?: Utilisateur;
}