import { RetourProduit } from './retour-produit';

export interface NonConformite {
  id?: number;
  description: string;
  gravite: string;
  date?: string;
  produit?: RetourProduit;
}