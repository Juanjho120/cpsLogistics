import { environment } from './../../environments/environment';
import { HttpClient } from '@angular/common/http';
import { GenericService } from './generic.service';
import { Injectable } from '@angular/core';
import { CreditoProveedorDetalle } from '../_model/creditoProveedorDetalle';

@Injectable({
  providedIn: 'root'
})
export class CreditoProveedorDetalleService extends GenericService<CreditoProveedorDetalle> {

  constructor(protected http: HttpClient) {
    super(http, `${environment.HOST}/credito-proveedor-detalles`);
   }
}
