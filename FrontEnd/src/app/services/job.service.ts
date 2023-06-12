import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class JobService {

  private URL = "http://localhost:8080/jobs";

  constructor(private httpClient:HttpClient) { }

  getJobById(id:Number):Observable<any>{
    return this.httpClient.get(this.URL+"/"+id);
  }
}
