import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { Customer } from 'src/app/models/customer';
import { CustomerService } from 'src/app/services/customer.service';

@Component({
  selector: 'app-customer-register',
  templateUrl: './customer-register.component.html',
  styleUrls: ['./customer-register.component.css']
})
export class CustomerRegisterComponent {

  customer: Customer = new Customer;

  constructor(private customerService:CustomerService, private router: Router) { }

  public registerCustomer(){
    console.log(this.customer);
    this.customerService.saveCustomer(this.customer).subscribe(
      response=> {
        console.log("El cliente se ha registrado correctamente.", response);
      },
      error => {
        console.error("Ocurrió un error al registrar el cliente.", error);
      }); 
      this.router.navigate(['clientes']);
  }

}
