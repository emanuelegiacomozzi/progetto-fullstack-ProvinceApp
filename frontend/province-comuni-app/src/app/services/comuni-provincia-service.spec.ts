import { TestBed } from '@angular/core/testing';

import { ComuniProvinciaService } from './comuni-provincia-service';

describe('ComuniProvinciaService', () => {
  let service: ComuniProvinciaService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(ComuniProvinciaService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
