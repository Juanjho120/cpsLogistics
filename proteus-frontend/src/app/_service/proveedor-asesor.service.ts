import { environment } from './../../environments/environment';
import { HttpClient } from '@angular/common/http';
import { GenericService } from './generic.service';
import { Injectable } from '@angular/core';
import { ProveedorAsesor } from '../_model/proveedorAsesor';

@Injectable({
  providedIn: 'root'
})
export class ProveedorAsesorService extends GenericService<ProveedorAsesor> {

  constructor(protected http: HttpClient) {
    super(http, `${environment.HOST}/proveedor-asesores`);
   }
}
