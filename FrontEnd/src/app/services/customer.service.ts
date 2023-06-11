import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Customer } from '../models/customer';

@Injectable({
  providedIn: 'root'
})
export class CustomerService {

private URL = "http://localhost:8080/customers";

  constructor(private HttpClient : HttpClient) { }

  getCustomerById(id:Number):Observable<any> {
    return this.HttpClient.get(this.URL+'/'+id);
  }

  public getListCustomers():Observable<any>{
    return this.HttpClient.get(this.URL);
  }

  public saveCustomer(customer:Customer):Observable<any>{
    return this.HttpClient.post(this.URL,customer);
  }

  public updateCustomer(customer:Customer):Observable<any>{
    return this.HttpClient.put(this.URL,customer);
  }

}
