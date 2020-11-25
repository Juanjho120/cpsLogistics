import { environment } from './../../environments/environment';
import { HttpClient } from '@angular/common/http';
import { GenericService } from './generic.service';
import { Injectable } from '@angular/core';
import { Repuesto } from '../_model/repuesto';

@Injectable({
  providedIn: 'root'
})
export class RepuestoService extends GenericService<Repuesto> {

  constructor(protected http: HttpClient) {
    super(http, `${environment.HOST}/repuestos`);
   }
}
