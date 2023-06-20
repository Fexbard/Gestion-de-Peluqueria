import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Expense } from '../models/expense';

@Injectable({
  providedIn: 'root'
})
export class ExpenseService {

  private URL = "http://localhost:8080/expenses";

  constructor(private httpClient:HttpClient) { }

  saveExpense(expense:Expense):Observable<any>{
    return this.httpClient.post(this.URL,expense);
  }

  getExpenses(page:number, size:number):Observable<any>{
    return this.httpClient.get(this.URL+'/paged?page='+page+'&size='+size);
  }

  getExpenseById(id:Number):Observable<any>{
    return this.httpClient.get(this.URL+'/'+id);
  }
  

}
