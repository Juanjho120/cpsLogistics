import { environment } from './../../environments/environment';
import { HttpClient } from '@angular/common/http';
import { GenericService } from './generic.service';
import { Injectable } from '@angular/core';
import { Personal } from '../_model/personal';

@Injectable({
  providedIn: 'root'
})
export class PersonalService extends GenericService<Personal> {

  constructor(protected http : HttpClient) {
    super(http, `${environment.HOST}/personal`);
   }
}
