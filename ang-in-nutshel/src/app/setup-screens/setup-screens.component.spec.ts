import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { SetupScreensComponent } from './setup-screens.component';

describe('SetupScreensComponent', () => {
  let component: SetupScreensComponent;
  let fixture: ComponentFixture<SetupScreensComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ SetupScreensComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(SetupScreensComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
