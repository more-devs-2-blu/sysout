import { TestBed } from '@angular/core/testing';

import { NfseService } from './nfse.service';

describe('NfseService', () => {
  let service: NfseService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(NfseService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
