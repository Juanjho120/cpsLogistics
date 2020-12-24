import { Ventana } from './_model/ventana';
import { LoginService } from './_service/login.service';
import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit {

  ventanas : Ventana[] = []
  title = 'proteus-frontend';
  opened = false;

  constructor(
    public loginService : LoginService
  ) {}

  ngOnInit() {
    this.loginService.getVentanaCambio().subscribe(data => {
      if(data.length>0) {
        this.opened = true;
      } else {
        this.opened = false;
      }
      
      this.ventanas = data;
    });
  }

}
