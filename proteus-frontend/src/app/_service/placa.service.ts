import { environment } from './../../environments/environment';
import { HttpClient } from '@angular/common/http';
import { GenericService } from './generic.service';
import { Injectable } from '@angular/core';
import { Placa } from '../_model/placa';

@Injectable({
  providedIn: 'root'
})
export class PlacaService extends GenericService<Placa> {

  constructor(protected http: HttpClient) {
    super(http, `${environment.HOST}/placas`);
  }

  getNotInService() {
    return this.http.get<Placa[]>(`${this.url}/not-in-service`);
  }

  getInService() {
    return this.http.get<Placa[]>(`${this.url}/in-service`);
  }
  
}
