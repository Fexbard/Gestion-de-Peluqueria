import { DatePipe } from '@angular/common';
import { Component } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import * as moment from 'moment';
import { Expense } from 'src/app/models/expense';
import { ExpenseService } from 'src/app/services/expense.service';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-update-expense',
  templateUrl: './update-expense.component.html',
  styleUrls: ['./update-expense.component.css'],
  providers: [DatePipe]
})
export class UpdateExpenseComponent {

  expense: Expense = new Expense();

  constructor(private expenseService: ExpenseService, private activatedRoute: ActivatedRoute, private datePipe: DatePipe) { }

  ngOnInit() {
    this.getExpenseById();
  }

  updateExpense() {
    const partesFecha = this.expense.date.split('-');
    this.expense.date = partesFecha[2] + '-' + partesFecha[1] + '-' + partesFecha[0] + ' 00:00:00';
    this.expenseService.updateExpense(this.expense).subscribe(
      response => {
        this.expense = response;
        const formattedDate = moment(response.date, 'DD-MM-YYYY HH:mm:ss').format('YYYY-MM-DD');
        if (formattedDate) {
          this.expense.date = formattedDate;
          console.log(this.expense);
        }
        Swal.fire(
          'Actualizado!',
          'Se actualizó correctamente el gasto!',
          'success'
        )
      },
      error => {
        console.log(error);
      })
  }

  getExpenseById() {
    this.expense.id = this.activatedRoute.snapshot.params['id'];
    this.expenseService.getExpenseById(this.expense.id).subscribe(
      expenseFound => {
        this.expense = expenseFound;
        const formattedDate = moment(expenseFound.date, 'DD-MM-YYYY HH:mm:ss').format('YYYY-MM-DD');
        if (formattedDate) {
          this.expense.date = formattedDate;
        }
      },
      error => {
        console.log(error);
      })
  }

}
