import { DatePipe } from '@angular/common';
import { Component } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Expense } from 'src/app/models/expense';
import { ExpenseService } from 'src/app/services/expense.service';

@Component({
  selector: 'app-update-expense',
  templateUrl: './update-expense.component.html',
  styleUrls: ['./update-expense.component.css'],
  providers: [DatePipe]
})
export class UpdateExpenseComponent {

  expense: Expense = new Expense();

  constructor(private expenseService: ExpenseService, private activatedRoute: ActivatedRoute, private datePipe:DatePipe) { }

  ngOnInit() {
    this.getExpenseById();
  }

  updateExpense() {

  }

  //VERIFICAR ERROR EN LA FECHA, NO CARGA BIEN
  getExpenseById() {
    this.expense.id = this.activatedRoute.snapshot.params['id'];
    this.expenseService.getExpenseById(this.expense.id).subscribe(
      expenseFound => {
        this.expense = expenseFound;
        const formattedDate = this.datePipe.transform(expenseFound.date, 'yyyy-MM-dd');
        if (formattedDate) {
          this.expense.date = formattedDate;
          console.log(this.expense);
        }
      },
      error => {
        console.log(error);
      })
  }

}
