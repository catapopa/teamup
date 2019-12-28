import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { UserSkillLevelComponent } from './user-skill-level.component';

describe('UserSkillLevelComponent', () => {
  let component: UserSkillLevelComponent;
  let fixture: ComponentFixture<UserSkillLevelComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ UserSkillLevelComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(UserSkillLevelComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
