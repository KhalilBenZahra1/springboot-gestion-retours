import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

import { HistoriqueRetour } from '../models/historique-retour';

@Injectable({
  providedIn: 'root'
})
export class HistoriqueRetourService {

  private apiUrl = '/api/historiqueRetour';

  constructor(private http: HttpClient) {}

  getAll(): Observable<HistoriqueRetour[]> {
    return this.http.get<HistoriqueRetour[]>(`${this.apiUrl}/tous`);
  }

  getById(id: number): Observable<HistoriqueRetour> {
    return this.http.get<HistoriqueRetour>(`${this.apiUrl}/${id}`);
  }

  delete(id: number): Observable<any> {
    return this.http.delete(`${this.apiUrl}/supprimer/${id}`, {
      responseType: 'text'
    });
  }
}