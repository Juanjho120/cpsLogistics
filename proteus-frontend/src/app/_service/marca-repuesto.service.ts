import { environment } from './../../environments/environment';
import { HttpClient } from '@angular/common/http';
import { GenericService } from './generic.service';
import { Injectable } from '@angular/core';
import { MarcaRepuesto } from '../_model/marcaRepuesto';

@Injectable({
  providedIn: 'root'
})
export class MarcaRepuestoService extends GenericService<MarcaRepuesto> {

  constructor(protected http: HttpClient) {
    super(http, `${environment.HOST}/marcas-repuestos`);
   }
}
