import { Component } from '@angular/core';
import { LoginService } from './services/login.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title = 'FrontEnd';
  isUserLoggedIn: boolean = true;
  

  constructor(private loginService:LoginService){}

  ngDoCheck() {
    this.isUserLoggedIn = this.loginService.isLoggedIn();
  }
}
