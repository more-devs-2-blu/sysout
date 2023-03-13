import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ModelosNotasComponent } from './modelos-notas.component';

describe('ModelosNotasComponent', () => {
  let component: ModelosNotasComponent;
  let fixture: ComponentFixture<ModelosNotasComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ModelosNotasComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ModelosNotasComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
