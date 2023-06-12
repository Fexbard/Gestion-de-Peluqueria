import { Component } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Customer } from 'src/app/models/customer';
import { CustomerService } from 'src/app/services/customer.service';

@Component({
  selector: 'app-customer-details',
  templateUrl: './customer-details.component.html',
  styleUrls: ['./customer-details.component.css']
})
export class CustomerDetailsComponent {

  customer:Customer = new Customer();

  constructor(private customerService:CustomerService, private router:Router, private activatedRoute:ActivatedRoute){}

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
      console.log(this.customer.jobs);
  }

  private getCustomer(){
    this.customerService.getCustomerById(this.customer.id).subscribe(
      customerFound => {
        this.customer=customerFound;
      },
      error=> console.log(error))
  }

}
