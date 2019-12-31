import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { UserSeniorityComponent } from './user-seniority.component';

describe('UserSeniorityComponent', () => {
  let component: UserSeniorityComponent;
  let fixture: ComponentFixture<UserSeniorityComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ UserSeniorityComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(UserSeniorityComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
