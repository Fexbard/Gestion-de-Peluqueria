import { Component } from '@angular/core';
import { Customer } from 'src/app/models/customer';
import { Job } from 'src/app/models/job';
import { Subjob } from 'src/app/models/subjob';


@Component({
  selector: 'app-job-register',
  templateUrl: './job-register.component.html',
  styleUrls: ['./job-register.component.css']
})
export class JobRegisterComponent {

  customer:Customer = new Customer();
  job:Job = new Job();
  items: Subjob[] = [];
  

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
//Continuar con este metodo, falta buscar cliente para saber el id
  addJob(){
    this.job.subJobs=this.items;
  }
}
