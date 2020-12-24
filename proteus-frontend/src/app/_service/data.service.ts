import { Inventario } from './../_model/inventario';
import { Subject } from 'rxjs';
import { FacturaCompra } from './../_model/facturaCompra';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class DataService {

  private facturaCompraCambio = new Subject<FacturaCompra>();

  private inventarioCambio = new Subject<Inventario>();

  constructor() { }

  getFacturaCompraCambio() {
    return this.facturaCompraCambio.asObservable();
  }

  setFacturaCompraCambio(facturaCompra : FacturaCompra) {
    this.facturaCompraCambio.next(facturaCompra);
  }

  getInventarioCambio() {
    return this.inventarioCambio.asObservable();
  }

  setInventarioCambio(inventario : Inventario) {
    this.inventarioCambio.next(inventario);
  }

}
