import { environment } from './../../environments/environment';
import { HttpClient } from '@angular/common/http';
import { GenericService } from './generic.service';
import { Injectable } from '@angular/core';
import { CajaChica } from '../_model/cajaChica';

@Injectable({
  providedIn: 'root'
})
export class CajaChicaService extends GenericService<CajaChica> {

  constructor(protected http: HttpClient) { 
    super(http, `${environment.HOST}/cajas-chicas`);
  }

  getByServicio(idServicio : number) {
    return this.http.get<CajaChica[]>(`${this.url}/servicio/${idServicio}`);
  }

  getByPlaca(idPlaca : number) {
    return this.http.get<CajaChica[]>(`${this.url}/placa/${idPlaca}`);
  }

  getByProveedorMenor(idProveedorMenor : number) {
    return this.http.get<CajaChica[]>(`${this.url}/proveedor-menor/${idProveedorMenor}`);
  }

  getByFechaIngreso(fechaDesde : string, fechaHasta : string) {
    return this.http.get<CajaChica[]>(`${this.url}/fecha-ingreso/${fechaDesde}/${fechaHasta}`);
  }
}
