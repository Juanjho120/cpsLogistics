import { environment } from './../../environments/environment';
import { HttpClient } from '@angular/common/http';
import { GenericService } from './generic.service';
import { Injectable } from '@angular/core';
import { CuentaBancaria } from '../_model/cuentaBancaria';

@Injectable({
  providedIn: 'root'
})
export class CuentaBancariaService extends GenericService<CuentaBancaria> {

  constructor(protected http: HttpClient) {
    super(http, `${environment.HOST}/cuentas-bancarias`);
   }
}
