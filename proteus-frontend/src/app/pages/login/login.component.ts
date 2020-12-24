import { VentanaService } from './../../_service/ventana.service';
import { environment } from './../../../environments/environment';
import { MatSnackBar } from '@angular/material/snack-bar';
import { LoginService } from './../../_service/login.service';
import { Router } from '@angular/router';
import { FormGroup, FormControl, Validators } from '@angular/forms';
import { Component, OnInit } from '@angular/core';
import { JwtHelperService } from '@auth0/angular-jwt';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  formLogin : FormGroup;

  constructor(
    private router : Router,
    private loginService : LoginService,
    private ventanaService : VentanaService,
    private snackBar : MatSnackBar
  ) { }

  ngOnInit(): void {
    this.formLogin = new FormGroup({
      'username' : new FormControl('', Validators.required),
      'password' : new FormControl('', Validators.required)
    });
  }

  login() {
    this.loginService.login(this.formLogin.value['username'], this.formLogin.value['password']).subscribe(data => {
      sessionStorage.setItem(environment.TOKEN_NAME, data.access_token);

      const helper = new JwtHelperService();

      let decodedToken = helper.decodeToken(data.access_token);

      this.ventanaService.getByUsername(decodedToken.user_name).subscribe(data => {
        this.loginService.setVentanaCambio(data);
        this.router.navigate(['inicio']);
      });
    });
  }

  enviarCorreoPassword() {
    this.snackBar.open('Se ha enviado un password a su correo', 'AVISO', {duration : 2000});
  }

}
