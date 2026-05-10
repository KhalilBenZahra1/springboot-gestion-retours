import { TestBed } from '@angular/core/testing';

import { StockProduit } from './stock-produit';

describe('StockProduit', () => {
  let service: StockProduit;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(StockProduit);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
