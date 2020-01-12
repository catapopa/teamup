import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ApproveProfileComponent } from './approve-profile.component';

describe('ApproveProfileComponent', () => {
  let component: ApproveProfileComponent;
  let fixture: ComponentFixture<ApproveProfileComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ApproveProfileComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ApproveProfileComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
