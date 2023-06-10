import { Component } from '@angular/core';
import { Customer } from 'src/app/models/customer';
import { CustomerService } from 'src/app/services/customer.service';

@Component({
  selector: 'app-customers-list',
  templateUrl: './customers-list.component.html',
  styleUrls: ['./customers-list.component.css']
})
export class CustomersListComponent {

  index:number = 0; //Indice en la izquiera de la tabla
  customer: Customer;
  customersList: Customer[];

  constructor(private customerService: CustomerService) { }

  ngOnInit() {
    this.getCustomers();
  }

  private getCustomers() {
    this.customerService.getListCustomers().subscribe(
      customerFromBack => {
        this.customersList = customerFromBack;
      });
  }

}
