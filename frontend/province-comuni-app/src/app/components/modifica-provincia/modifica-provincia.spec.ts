import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ModificaProvincia } from './modifica-provincia';

describe('ModificaProvincia', () => {
  let component: ModificaProvincia;
  let fixture: ComponentFixture<ModificaProvincia>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [ModificaProvincia]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ModificaProvincia);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
