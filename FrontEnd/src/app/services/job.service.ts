import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Job } from '../models/job';

@Injectable({
  providedIn: 'root'
})
export class JobService {

  private URL = "http://localhost:8080/jobs";

  constructor(private httpClient:HttpClient) { }

  getJobById(id:Number):Observable<any>{
    return this.httpClient.get(this.URL+"/"+id);
  }

  saveJob(job:Job):Observable<any>{
    return this.httpClient.post(this.URL,job);
  }
}
