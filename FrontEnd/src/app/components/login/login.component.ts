import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { AppComponent } from 'src/app/app.component';
import { LoginData } from 'src/app/models/login-data';
import { LoginService } from 'src/app/services/login.service';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent {

  loginData:LoginData = new LoginData();

  constructor(private loginService:LoginService, private router:Router, private app:AppComponent){}

  ngOnInit(){
    this.isLoggedIn();
  }

  isLoggedIn(){
    if(this.loginService.isLoggedIn()){
      this.router.navigate(['clientes']);
    }
  }

  verifyUser(){
    this.loginService.verifyUser(this.loginData).subscribe(
      response => {
        const token = response.token;
        this.loginService.loginUser(token);
        this.router.navigate(['']);
      },
      error => {
        Swal.fire({
          icon: 'error',
          title: 'Error',
          text: 'Usuario y/o contraseña incorrecta!',
        })
        console.log(error);
      }
    )
  }

}
