import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ListaComuniProvincia } from './lista-comuni-provincia';

describe('ListaComuniProvincia', () => {
  let component: ListaComuniProvincia;
  let fixture: ComponentFixture<ListaComuniProvincia>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [ListaComuniProvincia]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ListaComuniProvincia);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
