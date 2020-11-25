import { environment } from './../../environments/environment';
import { HttpClient } from '@angular/common/http';
import { GenericService } from './generic.service';
import { Injectable } from '@angular/core';
import { MarcaAuto } from '../_model/marcaAuto';

@Injectable({
  providedIn: 'root'
})
export class MarcaAutoService extends GenericService<MarcaAuto> {

  constructor(protected http: HttpClient) {
    super(http, `${environment.HOST}/marcas-autos`);
   }
}
