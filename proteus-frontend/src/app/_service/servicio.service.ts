import { environment } from './../../environments/environment';
import { HttpClient } from '@angular/common/http';
import { GenericService } from './generic.service';
import { Injectable } from '@angular/core';
import { Servicio } from '../_model/servicio';

@Injectable({
  providedIn: 'root'
})
export class ServicioService extends GenericService<Servicio> {

  constructor(protected http: HttpClient) {
    super(http, `${environment.HOST}/servicios`);
   }
}
