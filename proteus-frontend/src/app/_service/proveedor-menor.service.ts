import { environment } from './../../environments/environment';
import { HttpClient } from '@angular/common/http';
import { ProveedorMenor } from './../_model/proveedorMenor';
import { GenericService } from './generic.service';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class ProveedorMenorService extends GenericService<ProveedorMenor> {

  constructor(protected http: HttpClient) {
    super(http, `${environment.HOST}/proveedores-menores`);
  }

  getByNombre(nombre : string) {
    return this.http.get<ProveedorMenor>(`${this.url}/nombre/${nombre}`);
  }
  
}
