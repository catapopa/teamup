import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { BasicUserInfoComponent } from './basic-user-info.component';

describe('BasicUserInfoComponent', () => {
  let component: BasicUserInfoComponent;
  let fixture: ComponentFixture<BasicUserInfoComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ BasicUserInfoComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(BasicUserInfoComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
