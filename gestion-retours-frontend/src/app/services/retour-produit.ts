import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { RetourProduit } from '../models/retour-produit';

@Injectable({
  providedIn: 'root'
})
export class RetourProduitService {

  private apiUrl = '/api/retourProduit';

  constructor(private http: HttpClient) {}

  getAll(): Observable<RetourProduit[]> {
    return this.http.get<RetourProduit[]>(`${this.apiUrl}/tous`);
  }

  getById(id: number): Observable<RetourProduit> {
    return this.http.get<RetourProduit>(`${this.apiUrl}/${id}`);
  }

  add(retour: RetourProduit): Observable<any> {
    return this.http.post(`${this.apiUrl}/ajouter`, retour, {
      responseType: 'text'
    });
  }

  update(id: number, retour: RetourProduit): Observable<any> {
    return this.http.put(`${this.apiUrl}/mettreAJour/${id}`, retour, {
      responseType: 'text'
    });
  }

  delete(id: number): Observable<any> {
    return this.http.delete(`${this.apiUrl}/supprimer/${id}`, {
      responseType: 'text'
    });
  }

  traiterRetour(idRetourProduit: number, idUtilisateur: number, decision: string): Observable<any> {
    return this.http.put(
      `${this.apiUrl}/traiter/${idRetourProduit}/${idUtilisateur}/${decision}`,
      null,
      { responseType: 'text' }
    );
  }
}