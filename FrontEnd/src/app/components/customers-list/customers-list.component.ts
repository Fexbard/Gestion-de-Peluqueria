import { Component } from '@angular/core';
import { Router } from '@angular/router';
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

  constructor(private customerService: CustomerService, private router:Router) { }

  ngOnInit() {
    this.getCustomers();
  }

  private getCustomers() {
    this.customerService.getListCustomers().subscribe(
      customerFound => {
        this.customersList = customerFound;
      });
  }

  public updateCustomer(id:Number) {
    this.router.navigate(['clientes/actualizar-cliente',id]);
  }

  public redirectToUpdate(id:Number) {
    this.router.navigate(['clientes/actualizar',id]);
  }
}
