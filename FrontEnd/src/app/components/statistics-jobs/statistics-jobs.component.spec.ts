import { ComponentFixture, TestBed } from '@angular/core/testing';

import { StatisticsJobsComponent } from './statistics-jobs.component';

describe('StatisticsJobsComponent', () => {
  let component: StatisticsJobsComponent;
  let fixture: ComponentFixture<StatisticsJobsComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [StatisticsJobsComponent]
    });
    fixture = TestBed.createComponent(StatisticsJobsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
