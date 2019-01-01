import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { Welcome.ComponentComponent } from './welcome.component.component';

describe('Welcome.ComponentComponent', () => {
  let component: Welcome.ComponentComponent;
  let fixture: ComponentFixture<Welcome.ComponentComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ Welcome.ComponentComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(Welcome.ComponentComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
