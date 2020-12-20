import { InventarioEntradaSalidaDTO } from './../_model/dto/inventarioEntradaSalidaDTO';
import { environment } from './../../environments/environment';
import { HttpClient } from '@angular/common/http';
import { GenericService } from './generic.service';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class ReporteService extends GenericService<any> {

  constructor(protected http: HttpClient) {
    super(http, `${environment.HOST}/reportes`);
  }

  crearReporteInventarioEntradaSalida(fechaDesde : string, fechaHasta : string) {
    return this.http.get(`${this.url}/inventarios/inventario-entrada-salida/${fechaDesde}/${fechaHasta}`, {
      responseType: 'blob'
    });
  }
}
