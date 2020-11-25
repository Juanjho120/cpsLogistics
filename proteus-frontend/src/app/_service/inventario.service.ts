import { InventarioEntradaSalidaDTO } from './../_model/dto/inventarioEntradaSalidaDTO';
import { environment } from './../../environments/environment';
import { HttpClient } from '@angular/common/http';
import { GenericService } from './generic.service';
import { Injectable } from '@angular/core';
import { Inventario } from '../_model/inventario';

@Injectable({
  providedIn: 'root'
})
export class InventarioService extends GenericService<Inventario>{

  constructor(protected http: HttpClient) {
    super(http, `${environment.HOST}/inventarios`);
  }

  getByConcepto(idConcepto : number) {
    return this.http.get<Inventario[]>(`${this.url}/concepto/${idConcepto}`);
  }

  getByConceptoAndRepuesto(idConcepto : number, idRepuesto : number) {
    return this.http.get<Inventario[]>(`${this.url}/concepto/${idConcepto}/repuesto/${idRepuesto}`);
  }

  getByConceptoAndRepuestoAndUsuario(idConcepto : number, idRepuesto : number, idUsuario : number) {
    return this.http.get<Inventario[]>(`${this.url}/concepto/${idConcepto}/repuesto/${idRepuesto}/usuario/${idUsuario}`);
  }

  getByConceptoAndRepuestoAndFecha(idConcepto : number, idRepuesto : number, fechaDesde : string, fechaHasta : string) {
    return this.http.get<Inventario[]>(`${this.url}/concepto/${idConcepto}/repuesto/${idRepuesto}/fecha-rango/${fechaDesde}/${fechaHasta}`);
  }

  getByConceptoAndRepuestoAndFechaAndUsuario(idConcepto : number, idRepuesto : number, fechaDesde : string, fechaHasta : string, idUsuario : number) {
    return this.http.get<Inventario[]>(`${this.url}/concepto/${idConcepto}/repuesto/${idRepuesto}/fecha-rango/${fechaDesde}/${fechaHasta}/usuario/${idUsuario}`);
  }

  getByConceptoAndUsuario(idConcepto : number, idUsuario : number) {
    return this.http.get<Inventario[]>(`${this.url}/concepto/${idConcepto}/usuario/${idUsuario}`);
  }

  getByFechaRango(fechaDesde : string, fechaHasta : string) {
    return this.http.get<Inventario[]>(`${this.url}/fecha-rango/${fechaDesde}/${fechaHasta}`);
  }

  getByConceptoAndFechaRango(idConcepto : number, fechaDesde : string, fechaHasta : string) {
    return this.http.get<Inventario[]>(`${this.url}/concepto/${idConcepto}/fecha-rango/${fechaDesde}/${fechaHasta}`);
  }

  getByConceptoAndFechaAndUsuario(idConcepto : number, fechaDesde : string, fechaHasta : string, idUsuario : number) {
    return this.http.get<Inventario[]>(`${this.url}/concepto/${idConcepto}/fecha-rango/${fechaDesde}/${fechaHasta}/usuario/${idUsuario}`);
  }

  getByRepuesto(idRepuesto : number) {
    return this.http.get<Inventario[]>(`${this.url}/repuesto/${idRepuesto}`);
  }

  getByRepuestoAndUsuario(idRepuesto : number, idUsuario : number) {
    return this.http.get<Inventario[]>(`${this.url}/repuesto/${idRepuesto}/usuario/${idUsuario}`);
  }

  getByRepuestoAndFechaRango(idRepuesto : number, fechaDesde : string, fechaHasta : string) {
    return this.http.get<Inventario[]>(`${this.url}/repuesto/${idRepuesto}/fecha-rango/${fechaDesde}/${fechaHasta}`);
  }

  getByRepuestoAndFechaAndUsuario(idRepuesto : number, fechaDesde : string, fechaHasta : string, idUsuario : number) {
    return this.http.get<Inventario[]>(`${this.url}/repuesto/${idRepuesto}/fecha-rango/${fechaDesde}/${fechaHasta}/usuario/${idUsuario}`);
  }

  getByUsuario(idUsuario : number) {
    return this.http.get<Inventario[]>(`${this.url}/usuario/${idUsuario}`);
  }

  getByUsuarioAndFechaRango(idUsuario : number, fechaDesde : string, fechaHasta : string) {
    return this.http.get<Inventario[]>(`${this.url}/usuario/${idUsuario}/fecha-rango/${fechaDesde}/${fechaHasta}`);
  }

  getInventarioEntradaSalidaByFechaRango(fechaDesde : string, fechaHasta : string) {
    return this.http.get<InventarioEntradaSalidaDTO[]>(`${this.url}/entradas-salidas/fecha-rango/${fechaDesde}/${fechaHasta}`);
  }

}
