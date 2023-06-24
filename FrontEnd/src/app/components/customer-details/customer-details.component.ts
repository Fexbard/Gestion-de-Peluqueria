import { Component } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Customer } from 'src/app/models/customer';
import { Job } from 'src/app/models/job';
import { CustomerService } from 'src/app/services/customer.service';
import { JobService } from 'src/app/services/job.service';
import { LoginService } from 'src/app/services/login.service';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-customer-details',
  templateUrl: './customer-details.component.html',
  styleUrls: ['./customer-details.component.css']
})
export class CustomerDetailsComponent {

  customer: Customer = new Customer();
  job: Job = new Job();

  constructor(private customerService: CustomerService, private jobService: JobService, private router: Router, private activatedRoute: ActivatedRoute, private loginService: LoginService) { }

  ngOnInit() {

    if (this.loginService.isLoggedIn()) {
      this.customer.id = this.activatedRoute.snapshot.params['id'];
      this.customerService.getCustomerDetails(this.customer.id).subscribe(
        customerFound => {
          this.customer.name = customerFound.name;
          this.customer.surname = customerFound.surname;
          this.customer.cellphone = customerFound.cellphone;
        },
        error => console.log(error))
      this.getCustomer();
    } else {
      this.router.navigate(['login']);
    }

  }

  private getCustomer() {
    this.customerService.getCustomerById(this.customer.id).subscribe(
      customerFound => {
        this.customer = customerFound;
      },
      error => console.log(error))
  }

  public viewJobDetails(id: Number) {
    this.jobService.getJobById(id).subscribe(
      jobFound => {
        this.job = jobFound;
      },
      error => console.log(error))
  }

  public redirectToUpdate(id: Number) {
    this.router.navigate(['clientes/actualizar', id]);
  }

  public redirectToUpdateJob(idJob: Number) {
    this.router.navigate(['trabajos/actualizar', idJob]);
  }

  deleteJob(idJob: Number) {
    Swal.fire({
      title: 'Estás seguro que quieres eliminar el trabajo?',
      icon: 'warning',
      showCancelButton: true,
      confirmButtonColor: '#3085d6',
      cancelButtonColor: '#d33',
      cancelButtonText: 'Cancelar',
      confirmButtonText: 'Sí, eliminar!'
    }).then((result) => {
      if (result.isConfirmed) {
        this.jobService.deleteJob(idJob).subscribe(
          response => {
            Swal.fire(
              'Eliminado',
              'El el trabajo ha sido eliminado.',
              'success'
            )
            this.ngOnInit();
          },
          error => console.log(error))

      }
    })
  }



}


