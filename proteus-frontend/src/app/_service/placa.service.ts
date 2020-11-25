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
}
