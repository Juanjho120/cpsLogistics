import { environment } from './../../environments/environment';
import { HttpClient } from '@angular/common/http';
import { GenericService } from './generic.service';
import { Injectable } from '@angular/core';
import { Checklist } from '../_model/checklist';

@Injectable({
  providedIn: 'root'
})
export class ChecklistService extends GenericService<Checklist> {

  constructor(protected http: HttpClient) {
    super(http, `${environment.HOST}/checklists`);
   }
}
