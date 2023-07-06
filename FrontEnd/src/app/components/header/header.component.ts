import { Component, OnInit, Inject } from '@angular/core';
import { DOCUMENT } from '@angular/common'
import { LoginService } from 'src/app/services/login.service';
import { Router } from '@angular/router';
import { AppComponent } from 'src/app/app.component';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css']
})
export class HeaderComponent implements OnInit {

  constructor(@Inject(DOCUMENT) private document: Document, private loginService:LoginService, private router:Router, private app:AppComponent) { }

  ngOnInit(): void {

  }
  sidebarToggle() {
    this.document.body.classList.toggle('toggle-sidebar');
  }


  logout() {
    this.app.isLoggedIn = false;
    this.loginService.logout();
    this.router.navigate(['login']);
  }

}
