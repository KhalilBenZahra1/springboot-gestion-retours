import { TestBed } from '@angular/core/testing';

import { NonConformite } from './non-conformite';

describe('NonConformite', () => {
  let service: NonConformite;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(NonConformite);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
