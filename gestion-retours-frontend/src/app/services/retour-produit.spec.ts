import { TestBed } from '@angular/core/testing';

import { RetourProduit } from './retour-produit';

describe('RetourProduit', () => {
  let service: RetourProduit;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(RetourProduit);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
