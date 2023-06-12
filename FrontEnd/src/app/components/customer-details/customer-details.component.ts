import { Component } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Customer } from 'src/app/models/customer';
import { Job } from 'src/app/models/job';
import { CustomerService } from 'src/app/services/customer.service';
import { JobService } from 'src/app/services/job.service';

@Component({
  selector: 'app-customer-details',
  templateUrl: './customer-details.component.html',
  styleUrls: ['./customer-details.component.css']
})
export class CustomerDetailsComponent {

  customer:Customer = new Customer();
  job:Job = new Job;

  constructor(private customerService:CustomerService, private jobService:JobService,private router:Router, private activatedRoute:ActivatedRoute){}

  ngOnInit(){
    this.customer.id=this.activatedRoute.snapshot.params['id'];
    this.customerService.getCustomerDetails(this.customer.id).subscribe(
      customerFound => {
        this.customer.name=customerFound.name;
        this.customer.surname=customerFound.surname;
        this.customer.cellphone=customerFound.cellphone;
      },
      error => console.log(error))
      this.getCustomer();
  }

  private getCustomer(){
    this.customerService.getCustomerById(this.customer.id).subscribe(
      customerFound => {
        this.customer=customerFound;
      },
      error=> console.log(error))
  }

  public viewJobDetails(id:Number){
    this.jobService.getJobById(id).subscribe(
      jobFound => {
        this.job=jobFound;
      },
      error => console.log(error))
  }

}
