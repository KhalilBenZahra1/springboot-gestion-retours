import { TestBed } from '@angular/core/testing';

import { HistoriqueRetour } from './historique-retour';

describe('HistoriqueRetour', () => {
  let service: HistoriqueRetour;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(HistoriqueRetour);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
