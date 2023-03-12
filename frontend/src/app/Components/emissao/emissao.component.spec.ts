import { ComponentFixture, TestBed } from '@angular/core/testing';

import { EmissaoComponent } from './emissao.component';

describe('EmissaoComponent', () => {
  let component: EmissaoComponent;
  let fixture: ComponentFixture<EmissaoComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ EmissaoComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(EmissaoComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
