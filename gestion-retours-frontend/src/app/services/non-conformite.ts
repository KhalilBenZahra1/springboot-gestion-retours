import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { NonConformite } from '../models/non-conformite';

@Injectable({
  providedIn: 'root'
})
export class NonConformiteService {

  private apiUrl = 'http://localhost:8080/nonConformite';

  constructor(private http: HttpClient) {}

  getAll(): Observable<NonConformite[]> {
    return this.http.get<NonConformite[]>(`${this.apiUrl}/toutes`);
  }

  getById(id: number): Observable<NonConformite> {
    return this.http.get<NonConformite>(`${this.apiUrl}/${id}`);
  }

  add(idRetourProduit: number, nonConformite: NonConformite): Observable<any> {
    return this.http.post(`${this.apiUrl}/ajouter/${idRetourProduit}`, nonConformite, {
      responseType: 'text'
    });
  }

  update(id: number, nonConformite: NonConformite): Observable<any> {
    return this.http.put(`${this.apiUrl}/mettreAJour/${id}`, nonConformite, {
      responseType: 'text'
    });
  }

  delete(id: number): Observable<any> {
    return this.http.delete(`${this.apiUrl}/supprimer/${id}`, {
      responseType: 'text'
    });
  }
}