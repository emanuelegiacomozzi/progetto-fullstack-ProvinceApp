import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ListaComuni } from './lista-comuni';

describe('ListaComuni', () => {
  let component: ListaComuni;
  let fixture: ComponentFixture<ListaComuni>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [ListaComuni]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ListaComuni);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
