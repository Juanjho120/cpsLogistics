import { ProveedorDTO } from './../_model/dto/proveedorDTO';
import { environment } from './../../environments/environment';
import { HttpClient } from '@angular/common/http';
import { GenericService } from './generic.service';
import { Injectable } from '@angular/core';
import { Proveedor } from '../_model/proveedor';

@Injectable({
  providedIn: 'root'
})
export class ProveedorService extends GenericService<Proveedor> {

  constructor(protected http: HttpClient) {
    super(http, `${environment.HOST}/proveedores`);
  }

  getAllDTO() {
    return this.http.get<ProveedorDTO[]>(`${this.url}/dto`);
  }
}
