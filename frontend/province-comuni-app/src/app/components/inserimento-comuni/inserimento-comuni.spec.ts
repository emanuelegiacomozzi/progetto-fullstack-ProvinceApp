import { ComponentFixture, TestBed } from '@angular/core/testing';

import { InserimentoComuni } from './inserimento-comuni';

describe('InserimentoComuni', () => {
  let component: InserimentoComuni;
  let fixture: ComponentFixture<InserimentoComuni>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [InserimentoComuni]
    })
    .compileComponents();

    fixture = TestBed.createComponent(InserimentoComuni);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
