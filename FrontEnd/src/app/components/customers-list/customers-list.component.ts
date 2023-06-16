import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { Customer } from 'src/app/models/customer';
import { CustomerService } from 'src/app/services/customer.service';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-customers-list',
  templateUrl: './customers-list.component.html',
  styleUrls: ['./customers-list.component.css']
})
export class CustomersListComponent {

  index: number = 0; //Indice en la izquiera de la tabla
  customer: Customer;
  customersList: Customer[];
  nameCustomerToSearch:String;

  constructor(private customerService: CustomerService, private router: Router) { }

  ngOnInit() {
    this.getCustomers();
  }

  private getCustomers() {
    this.customerService.getListCustomers().subscribe(
      customerFound => {
        this.customersList = customerFound;
      });
  }

  public deleteCustomer(id: Number) {
    Swal.fire({
      title: 'Estás seguro que quieres eliminar el cliente?',
      icon: 'warning',
      showCancelButton: true,
      confirmButtonColor: '#3085d6',
      cancelButtonColor: '#d33',
      cancelButtonText: 'Cancelar',
      confirmButtonText: 'Sí, eliminar!'
    }).then((result) => {
      if (result.isConfirmed) {
        this.customerService.deleteCustomer(id).subscribe(
          response => {
            Swal.fire(
              'Eliminado',
              'El cliente ha sido eliminado.',
              'success'
            )
            this.ngOnInit();
          },
          error => console.log(error))
        
      }
    })
  }

  public searchCustomerByName(){
    if (this.nameCustomerToSearch) {
      this.customerService.getCustomerByName(this.nameCustomerToSearch).subscribe(
        customerFound =>{
          this.customersList = customerFound;
        }
      )
    } else {
      this.getCustomers(); // Obtiene la lista completa de clientes
    }
  }

  public handleBlur(): void {
    if (!this.nameCustomerToSearch) {
      this.getCustomers(); // Obtiene la lista completa de clientes si el campo de búsqueda está vacío
    }
  }

  public updateCustomer(id: Number) {
    this.router.navigate(['clientes/actualizar-cliente', id]);
  }

  public redirectToUpdate(id: Number) {
    this.router.navigate(['clientes/actualizar', id]);
  }

  public redirectToDetails(id: Number) {
    this.router.navigate(['clientes/detalles/', id])
  }

  public redirectToAddJob(id: Number) {
    this.router.navigate(['clientes',id,'agregar-trabajo'])
  }
}
