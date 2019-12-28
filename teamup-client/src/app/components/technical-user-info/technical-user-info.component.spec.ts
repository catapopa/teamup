import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { TechnicalUserInfoComponent } from './technical-user-info.component';

describe('TechnicalUserInfoComponent', () => {
  let component: TechnicalUserInfoComponent;
  let fixture: ComponentFixture<TechnicalUserInfoComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ TechnicalUserInfoComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(TechnicalUserInfoComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
