import { ComponentFixture, TestBed } from '@angular/core/testing';

import { InserimentoProvincia } from './inserimento-provincia';

describe('InserimentoProvincia', () => {
  let component: InserimentoProvincia;
  let fixture: ComponentFixture<InserimentoProvincia>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [InserimentoProvincia]
    })
    .compileComponents();

    fixture = TestBed.createComponent(InserimentoProvincia);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
