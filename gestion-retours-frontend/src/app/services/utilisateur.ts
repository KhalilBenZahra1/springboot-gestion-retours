import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Utilisateur } from '../models/utilisateur';

@Injectable({
  providedIn: 'root'
})
export class UtilisateurService {

  private apiUrl = 'http://localhost:8080/utilisateur';

  constructor(private http: HttpClient) {}

  getAll(): Observable<Utilisateur[]> {
    return this.http.get<Utilisateur[]>(`${this.apiUrl}/tous`);
  }

  getById(id: number): Observable<Utilisateur> {
    return this.http.get<Utilisateur>(`${this.apiUrl}/${id}`);
  }

  add(utilisateur: Utilisateur): Observable<any> {
    return this.http.post(`${this.apiUrl}/ajouter`, utilisateur, {
      responseType: 'text'
    });
  }

  update(id: number, utilisateur: Utilisateur): Observable<any> {
    return this.http.put(`${this.apiUrl}/mettreAJour/${id}`, utilisateur, {
      responseType: 'text'
    });
  }

  delete(id: number): Observable<any> {
    return this.http.delete(`${this.apiUrl}/supprimer/${id}`, {
      responseType: 'text'
    });
  }

}