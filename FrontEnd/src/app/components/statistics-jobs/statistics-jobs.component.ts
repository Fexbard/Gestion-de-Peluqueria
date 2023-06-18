import { Component } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Customer } from 'src/app/models/customer';
import { Job } from 'src/app/models/job';
import { JobService } from 'src/app/services/job.service';

@Component({
  selector: 'app-statistics-jobs',
  templateUrl: './statistics-jobs.component.html',
  styleUrls: ['./statistics-jobs.component.css']
})
export class StatisticsJobsComponent {

  customer: Customer = new Customer();

  job: Job;
  jobsList: Job[];
  dateFrom: string;
  dateTo: string;
  size: number = 10;
  page: number = 0;
  isFirstPage: boolean = true;

  constructor(private jobService: JobService, private router: Router, private activatedRoute: ActivatedRoute) { }

  ngOnInit() {
    const today = new Date();
    const firstDayOfMonth = new Date(today.getFullYear(), today.getMonth(), 1);

    this.dateFrom = this.formatDateAsString(firstDayOfMonth);
    this.dateTo = this.formatDateAsString(today);
    this.getJobsByDates();
    // Obtener los trabajos de la primera página al inicializar el componente
    this.getJobsByPage(this.page);
  }

  viewJobDetails(id: Number) {
    this.jobService.getJobById(id).subscribe(
      jobFound => {
        this.job = jobFound;
      },
      error => console.log(error))
  }

  getJobsByDates() {
    const formattedDateFrom = this.formatDate(this.dateFrom);
    const formattedDateTo = this.formatDate(this.dateTo);

    this.jobService.getJobsFromDateToDate(this.page, this.size, formattedDateFrom, formattedDateTo).subscribe(
      jobsFound => {
        this.jobsList = jobsFound.content;
      },
      error => {
        console.log(error);
      });
  }

  private formatDate(dateString: string): string {
    const parts = dateString.split('-');
    const day = parts[2];
    const month = parts[1];
    const year = parts[0];

    const formattedDate = `${day}-${month}-${year} 00:00:00`;

    return formattedDate;
  }

  private formatDateAsString(date: Date): string {
    const year = date.getFullYear().toString();
    const month = (date.getMonth() + 1).toString().padStart(2, '0');
    const day = date.getDate().toString().padStart(2, '0');

    return `${year}-${month}-${day}`;
  }

  private getJobsByPage(pageNumber: number, fromDate?: string, toDate?: string): void {
    if (fromDate && toDate) {
      this.jobService.getJobsFromDateToDate(pageNumber, this.size, fromDate, toDate).subscribe(
        jobsFound => {
          this.jobsList = jobsFound.content;
        },
        error => {
          console.log(error);
        }
      );
    }
  }

  goToPage(pageNumber: number) {
    this.page = pageNumber;
    this.isFirstPage = (pageNumber === 1);
    this.getJobsByPage(this.page, this.dateFrom, this.dateTo);
  }

}
