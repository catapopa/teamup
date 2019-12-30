import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { TechAreaComponent } from './tech-area.component';

describe('TechAreaComponent', () => {
  let component: TechAreaComponent;
  let fixture: ComponentFixture<TechAreaComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ TechAreaComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(TechAreaComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
