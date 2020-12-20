import { MatDialogRef } from '@angular/material/dialog';
import { Checklist } from './../../../../_model/checklist';
import { MatTableDataSource } from '@angular/material/table';
import { PlacaService } from './../../../../_service/placa.service';
import { ServicioService } from './../../../../_service/servicio.service';
import { ChecklistService } from './../../../../_service/checklist.service';
import { Servicio } from './../../../../_model/servicio';
import { Placa } from './../../../../_model/placa';
import { Observable } from 'rxjs';
import { FormGroup, FormControl, Validators } from '@angular/forms';
import { Component, OnInit } from '@angular/core';
import * as moment from 'moment';

@Component({
  selector: 'app-checklist-dialogo-buscar',
  templateUrl: './checklist-dialogo-buscar.component.html',
  styleUrls: ['./checklist-dialogo-buscar.component.css']
})
export class ChecklistDialogoBuscarComponent implements OnInit {

  formBusqueda : FormGroup;
  idBusqueda : number = 0;

  idServicio : number = 0;
  idPlaca : number = 0;

  servicios$ : Observable<Servicio[]>;
  placas$ : Observable<Placa[]>;

  dataSource : MatTableDataSource<Checklist>;
  checklists : Checklist[] = [];
  displayedColumns = ['noChecklist', 'checklistServicioTipo', 'noServicio', 'servicioTipo', 'placa', 'fechaIngreso', 'fechaRevision', 'select'];

  myControlServicio : FormControl = new FormControl('', Validators.required);
  myControlPlaca : FormControl = new FormControl('', Validators.required);

  formatoFecha : string = 'YYYY-MM-DD';

  constructor(
    private checklistService : ChecklistService,
    private servicioService : ServicioService,
    private placaService : PlacaService,
    private dialogRef : MatDialogRef<ChecklistDialogoBuscarComponent>
  ) { }

  ngOnInit(): void {
    this.servicios$ = this.servicioService.getByFinalizado(false);

    this.placas$ = this.placaService.getInService();

    this.checklistService.getAllNotFinalizado().subscribe(data => {
      this.checklists = [];
      for(let checklist of data) {
        checklist.fechaHoraIngreso = moment(checklist.fechaHoraIngreso).format(this.formatoFecha);
        checklist.fechaRevision = moment(checklist.fechaRevision).format(this.formatoFecha);
        this.checklists.push(checklist);
      }
      this.dataSource = new MatTableDataSource(this.checklists);
    });

    this.initForm(); 
  }

  initForm() {
    this.formBusqueda = new FormGroup({
      'servicio' : this.myControlServicio,
      'placa' : this.myControlPlaca
    });

    this.formBusqueda.get('servicio').disable();
    this.formBusqueda.get('placa').disable();
  }

  setInputs() {
    if(this.idBusqueda == 1) {
      this.setServicioInput(true);
      this.setPlacaInput(false);
    } else if(this.idBusqueda == 2) {
      this.setServicioInput(false);
      this.setPlacaInput(true);
    }
  }

  setServicioInput(e : boolean) {
    this.dataSource = new MatTableDataSource();
    this.checklists = [];
    this.myControlServicio.reset();
    if(e) {
      this.formBusqueda.get('servicio').enable();
    } else {
      this.formBusqueda.get('servicio').disable();
    }
  }

  setPlacaInput(e : boolean) {
    this.dataSource = new MatTableDataSource();
    this.checklists = [];
    this.myControlPlaca.reset();
    if(e) {
      this.formBusqueda.get('placa').enable();
    } else {
      this.formBusqueda.get('placa').disable();
    }
  }

  buscar() {
    if(this.idBusqueda == 1) {
      this.checklistService.getByServicioFinalizado(this.idServicio).subscribe(data => {
        this.checklists = [];
        data.fechaHoraIngreso = moment(data.fechaHoraIngreso).format(this.formatoFecha);
        data.fechaRevision = moment(data.fechaRevision).format(this.formatoFecha);
        this.checklists.push(data);
        this.dataSource = new MatTableDataSource(this.checklists);
      });
    } else if(this.idBusqueda == 2) {
      this.checklistService.getByPlacaAndServicioFinalizado(this.idPlaca, false).subscribe(data => {
        this.checklists = [];
        for(let checklist of data) {
          checklist.fechaHoraIngreso = moment(checklist.fechaHoraIngreso).format(this.formatoFecha);
          checklist.fechaRevision = moment(checklist.fechaRevision).format(this.formatoFecha);
          this.checklists.push(checklist);
        }
        this.dataSource = new MatTableDataSource(this.checklists);
      });
    } else {
      return;
    }
  }

  cerrar(checked : boolean, checklist : Checklist) {
    if(checked) {
      this.dialogRef.close(checklist);
    }
  }
}
