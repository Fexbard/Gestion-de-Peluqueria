import { Component } from '@angular/core';
import { Customer } from 'src/app/models/customer';

interface Item {
  nombre: string;
  precio: number;
}

@Component({
  selector: 'app-job-register',
  templateUrl: './job-register.component.html',
  styleUrls: ['./job-register.component.css']
})
export class JobRegisterComponent {

  customer:Customer = new Customer();

  items: Item[] = [];

  agregarItem(): void {
    this.items.push({ nombre: '', precio: 0 });
  }

  eliminarItem(index: number): void {
    this.items.splice(index, 1);
  }

}
