import { Component } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { format } from 'date-fns';
import { Customer } from 'src/app/models/customer';
import { Job } from 'src/app/models/job';
import { Subjob } from 'src/app/models/subjob';
import { JobService } from 'src/app/services/job.service';


@Component({
  selector: 'app-job-register',
  templateUrl: './job-register.component.html',
  styleUrls: ['./job-register.component.css']
})
export class JobRegisterComponent {

  customer:Customer = new Customer();
  job:Job = new Job();
  items: Subjob[] = [];

  constructor(
    private jobService:JobService, 
    private activatedRoute:ActivatedRoute, 
    private router:Router){}
  

  agregarItem() {
    this.items.push({ subJobTitle: '', subJobAmount: 0 });
  }

  eliminarItem(index: number) {
    this.items.splice(index, 1);
    this.calcularTotal();
  }

  calcularTotal(): number {
    let total = 0;
    for (let item of this.items) {
      total += item.subJobAmount;
    }
    this.job.totalAmount = total;
    return total;
  }

  borrarCero(item: any) {
    if (item.subJobAmount === 0) {
      item.subJobAmount = null;
    }
  }

  addJob(){
    this.job.idClient=this.activatedRoute.snapshot.params['id'];
    this.job.subJobs=this.items;
    const partesFecha = this.job.date.split('-');
    this.job.date = partesFecha[2] + '-' + partesFecha[1] + '-' + partesFecha[0]+' 00:00:00';
    this.jobService.saveJob(this.job).subscribe(
      response => {console.log("Se registró correctamente el trabajo al cliente", response)},
      error => {console.log(error)})
    this.router.navigate(['clientes']);
  }
}
