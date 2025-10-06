import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ModificaComune } from './modifica-comune';

describe('ModificaComune', () => {
  let component: ModificaComune;
  let fixture: ComponentFixture<ModificaComune>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [ModificaComune]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ModificaComune);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
