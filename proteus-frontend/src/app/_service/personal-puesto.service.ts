import { environment } from './../../environments/environment';
import { HttpClient } from '@angular/common/http';
import { GenericService } from './generic.service';
import { Injectable } from '@angular/core';
import { PersonalPuesto } from '../_model/personalPuesto';

@Injectable({
  providedIn: 'root'
})
export class PersonalPuestoService extends GenericService<PersonalPuesto> {

  constructor(protected http: HttpClient) {
    super(http, `${environment.HOST}/personal-puestos`);
   }
}
