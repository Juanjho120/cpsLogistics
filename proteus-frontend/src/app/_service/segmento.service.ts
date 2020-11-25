import { environment } from './../../environments/environment';
import { HttpClient } from '@angular/common/http';
import { GenericService } from './generic.service';
import { Injectable } from '@angular/core';
import { Segmento } from '../_model/segmento';

@Injectable({
  providedIn: 'root'
})
export class SegmentoService extends GenericService<Segmento> {

  constructor(protected http: HttpClient) {
    super(http, `${environment.HOST}/segmentos`);
   }
}
