import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { LoginData } from '../models/login-data';

@Injectable({
  providedIn: 'root'
})
export class LoginService {

  private URL = "http://localhost:8080/auth"

  constructor(private httpClient: HttpClient) { }

  public verifyUser(loginData:LoginData):Observable<any>{
    return this.httpClient.post(this.URL+'/signin',loginData);
  }

  public loginUser(token:any){
    localStorage.setItem('token',token);
  }

  public isLoggedIn(){
    let tokenStr = localStorage.getItem('token');
    if(tokenStr == undefined || tokenStr == '' || tokenStr == null) {
      return false;
    } else {
      return true;
    }
  }

  public logout(){
    localStorage.removeItem('token');
    localStorage.removeItem('user');
    return true;
  }

  public getToken(){
    return localStorage.getItem('token');
  }

  public setUser(user:any){
    localStorage.setItem('user',JSON.stringify(user));
  }

  public getUser(){
    let userStr = localStorage.getItem('user');
    if(userStr != null){
      return JSON.parse(userStr);
    } else {
      this.logout();
      return null;
    }
  }

}
