import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ModelsNfseComponent } from './models-nfse.component';

describe('ModelsNfseComponent', () => {
  let component: ModelsNfseComponent;
  let fixture: ComponentFixture<ModelsNfseComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ModelsNfseComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ModelsNfseComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
