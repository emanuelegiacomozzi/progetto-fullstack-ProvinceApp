import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ListaProvince } from './lista-province';

describe('ListaProvince', () => {
  let component: ListaProvince;
  let fixture: ComponentFixture<ListaProvince>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [ListaProvince]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ListaProvince);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
