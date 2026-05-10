import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

import { StockProduit } from '../models/stock-produit';

@Injectable({
  providedIn: 'root'
})
export class StockProduitService {

  private apiUrl = '/api/stockProduit';

  constructor(private http: HttpClient) {}

  getAll(): Observable<StockProduit[]> {
    return this.http.get<StockProduit[]>(`${this.apiUrl}/tous`);
  }

  getById(id: number): Observable<StockProduit> {
    return this.http.get<StockProduit>(`${this.apiUrl}/${id}`);
  }

  add(stockProduit: StockProduit): Observable<any> {
    return this.http.post(`${this.apiUrl}/ajouter`, stockProduit, {
      responseType: 'text'
    });
  }

  update(id: number, stockProduit: StockProduit): Observable<any> {
    return this.http.put(`${this.apiUrl}/mettreAJour/${id}`, stockProduit, {
      responseType: 'text'
    });
  }

  delete(id: number): Observable<any> {
    return this.http.delete(`${this.apiUrl}/supprimer/${id}`, {
      responseType: 'text'
    });
  }

}