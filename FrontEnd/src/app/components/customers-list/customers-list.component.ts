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

  confirmDelete(id:Number): void {
    
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
}
