import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CancelNfseComponent } from './cancel-nfse.component';

describe('CancelNfseComponent', () => {
  let component: CancelNfseComponent;
  let fixture: ComponentFixture<CancelNfseComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ CancelNfseComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(CancelNfseComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
