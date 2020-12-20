import { ChecklistDialogoBuscarComponent } from './checklist-dialogo-buscar/checklist-dialogo-buscar.component';
import { MatDialog } from '@angular/material/dialog';
import { MatSnackBar } from '@angular/material/snack-bar';
import { Usuario } from './../../../_model/usuario';
import { UsuarioService } from './../../../_service/usuario.service';
import { switchMap } from 'rxjs/operators';
import { PersonalService } from './../../../_service/personal.service';
import { Personal } from './../../../_model/personal';
import { ChecklistServicioTipo } from './../../../_model/checklistServicioTipo';
import { ChecklistServicioTipoService } from './../../../_service/checklist-servicio-tipo.service';
import { ServicioService } from './../../../_service/servicio.service';
import { Servicio } from './../../../_model/servicio';
import { Observable } from 'rxjs';
import { FormGroup, FormControl, Validators } from '@angular/forms';
import { ChecklistDetalleService } from './../../../_service/checklist-detalle.service';
import { ChecklistService } from './../../../_service/checklist.service';
import { ChecklistDetalle } from './../../../_model/checklistDetalle';
import { Checklist } from './../../../_model/checklist';
import { Component, OnInit } from '@angular/core';
import * as moment from 'moment';

@Component({
  selector: 'app-checklist-ingreso-edicion',
  templateUrl: './checklist-ingreso-edicion.component.html',
  styleUrls: ['./checklist-ingreso-edicion.component.css']
})
export class ChecklistIngresoEdicionComponent implements OnInit {

  checklist : Checklist;
  checklistDetalleMecanico : ChecklistDetalle[] = [];
  checklistDetalleElectrico : ChecklistDetalle[] = [];

  servicios$ : Observable<Servicio[]>;
  idServicio : number = 0;
  servicioSeleccionado : Servicio;

  formChecklist : FormGroup;

  formatoFecha : string = 'YYYY-MM-DD';
  fechaRevisionSeleccionada: Date = new Date();
  maxFecha: Date = new Date();
  fechaRevisionFormato : string;

  checklistServicioTipos$ : Observable<ChecklistServicioTipo[]>;
  idChecklistServicioTipo : number = 0;

  supervisores$ : Observable<Personal[]>;
  idSupervisor : number = 0;

  mecanicos$ : Observable<Personal[]>;
  idMecanico : number = 0;

  usuario : Usuario;

  constructor(
    private checklistService : ChecklistService,
    private checklistDetalleService : ChecklistDetalleService,
    private servicioService : ServicioService,
    private checklistServicioTipoService : ChecklistServicioTipoService,
    private personalService : PersonalService,
    private usuarioService : UsuarioService,
    private snackBar : MatSnackBar,
    private dialog : MatDialog
  ) { }

  ngOnInit(): void {
    this.servicios$ = this.servicioService.getNotInChecklist();

    this.checklistServicioTipos$ = this.checklistServicioTipoService.getAll();

    this.supervisores$ = this.personalService.getByPersonalPuesto(2);

    this.mecanicos$ = this.personalService.getByPersonalPuesto(1);

    this.usuarioService.getById(1).subscribe(data => {
      this.usuario = data;
    });

    this.checklistService.getMensajeCambio().subscribe(data => {
      this.snackBar.open(data, 'AVISO', {duration : 2000});
    });

    this.servicioService.getMensajeCambio().subscribe(data => {
      this.snackBar.open(data, 'AVISO', {duration : 2000});
    });

    this.initForm();
  }

  initForm() {
    this.formChecklist = new FormGroup({
      'servicio' : new FormControl('', Validators.required),
      'placa' : new FormControl({value : '', disabled : true}, Validators.required),
      'fechaRevision' : new FormControl('', Validators.required),
      'kilometrajeActual' : new FormControl({value : '', disabled : true}, Validators.required),
      'kilometrajeProximo' : new FormControl({value : '', disabled : true}, Validators.required),
      'checklistServicioTipo' : new FormControl('', Validators.required),
      'observaciones' : new FormControl(''),
      'mecanico' : new FormControl('', Validators.required),
      'supervisor' : new FormControl('', Validators.required)
    });

  }

  cargarCampos() {
    this.servicioService.getById(this.idServicio).subscribe(data => {
      this.servicioSeleccionado = data;
      this.formChecklist = new FormGroup({
        'servicio' : new FormControl(this.servicioSeleccionado.idServicio, Validators.required),
        'placa' : new FormControl({value : this.servicioSeleccionado.placa.numero, disabled : true}, Validators.required),
        'fechaRevision' : new FormControl('', Validators.required),
        'kilometrajeActual' : new FormControl({value : this.servicioSeleccionado.kilometrajeRecorrido, disabled : true}, Validators.required),
        'kilometrajeProximo' : new FormControl({value : this.servicioSeleccionado.kilometrajeProximoServicio, disabled : true}, Validators.required),
        'checklistServicioTipo' : new FormControl('', Validators.required),
        'observaciones' : new FormControl(''),
        'mecanico' : new FormControl('', Validators.required),
        'supervisor' : new FormControl('', Validators.required)
      });
    });
  }

  cargarDetalle() {
    this.checklistDetalleService.createByChecklistTipo(this.idChecklistServicioTipo).subscribe(data => {
      this.checklistDetalleElectrico = [];
      this.checklistDetalleMecanico = [];
      for(let checklistDetalle of data) {
        if(!checklistDetalle.electrico) {
          this.checklistDetalleMecanico.push(checklistDetalle);
        } else {
          this.checklistDetalleElectrico.push(checklistDetalle);
        }
      }
    });
  }

  cambiarFecha(e : any) {
    this.fechaRevisionSeleccionada = e.value;
    this.fechaRevisionFormato = moment(this.fechaRevisionSeleccionada).format(this.formatoFecha);
  }

  verEvaluacion() {
    console.log(this.checklistDetalleMecanico);
    console.log(this.checklistDetalleElectrico);
  }

  nuevoChecklist() {
    this.checklist = new Checklist();
    this.formChecklist.reset();
    //this.formChecklist.get('servicio').enable();
    this.checklistDetalleMecanico = [];
    this.checklistDetalleElectrico = [];
    this.fechaRevisionSeleccionada = new Date();
    this.fechaRevisionFormato = moment(this.fechaRevisionSeleccionada).format(this.formatoFecha);
    this.servicioSeleccionado = new Servicio();
    this.idSupervisor = 0;
    this.idMecanico = 0;
    this.idChecklistServicioTipo = 0;
    this.servicios$ = this.servicioService.getNotInChecklist();
  }

  evaluarBotonGuardar() {
    if(this.formChecklist.valid && this.evaluarChecklistDetalle(this.checklistDetalleMecanico) 
        && this.evaluarChecklistDetalle(this.checklistDetalleElectrico)) {
      return false;
    }
    return true;
  }

  evaluarChecklistDetalle(checklistDetalles : ChecklistDetalle[]) {
    for(let checklistDetalle of checklistDetalles) {
      if(checklistDetalle.checklistEvaluacion.idChecklistEvaluacion == null) {
        return false;
      }
    }
    return true;
  }

  operar() {

    if(this.checklist != null && this.checklist.idChecklist > 0) {
      let checklistDetalles : ChecklistDetalle[] = [];
      
      for(let checklistDetalle of this.checklistDetalleMecanico) {
        checklistDetalles.push(checklistDetalle);
      }

      for(let checklistDetalle of this.checklistDetalleElectrico) {
        checklistDetalles.push(checklistDetalle);
      }

      this.checklist.checklistDetalle = checklistDetalles;
      this.checklist.fechaRevision = this.fechaRevisionFormato;
      this.checklist.checklistServicioTipo = new ChecklistServicioTipo();
      this.checklist.checklistServicioTipo.idChecklistServicioTipo = this.idChecklistServicioTipo;
      this.checklist.servicio = this.servicioSeleccionado;
      this.checklist.mecanico = new Personal();
      this.checklist.mecanico.idPersonal = this.idMecanico;
      this.checklist.supervisor = new Personal();
      this.checklist.supervisor.idPersonal = this.idSupervisor;
      this.checklist.noOrdenTrabajo = this.servicioSeleccionado.idServicio + "";
      this.checklist.observaciones = this.formChecklist.value['observaciones'];
      this.checklist.fechaHoraIngreso = null;

      this.checklistService.update(this.checklist).pipe(switchMap(() => {
        return this.checklistService.getAll();
      })).subscribe(data => {
        this.checklistService.setObjetoCambio(data);
        this.checklistService.setMensajeCambio('Checklist actualizado');
        this.nuevoChecklist();
      });

    } else {
      this.checklist = new Checklist();
      let checklistDetalles : ChecklistDetalle[] = [];
      
      for(let checklistDetalle of this.checklistDetalleMecanico) {
        checklistDetalles.push(checklistDetalle);
      }

      for(let checklistDetalle of this.checklistDetalleElectrico) {
        checklistDetalles.push(checklistDetalle);
      }

      this.checklist.checklistDetalle = checklistDetalles;
      this.checklist.fechaRevision = this.fechaRevisionFormato;
      this.checklist.checklistServicioTipo = new ChecklistServicioTipo();
      this.checklist.checklistServicioTipo.idChecklistServicioTipo = this.idChecklistServicioTipo;
      this.checklist.servicio = this.servicioSeleccionado;
      this.checklist.mecanico = new Personal();
      this.checklist.mecanico.idPersonal = this.idMecanico;
      this.checklist.supervisor = new Personal();
      this.checklist.supervisor.idPersonal = this.idSupervisor;
      this.checklist.noOrdenTrabajo = this.servicioSeleccionado.idServicio + "";
      this.checklist.observaciones = this.formChecklist.value['observaciones'];
      this.checklist.usuarioIngreso = this.usuario;

      this.checklistService.create(this.checklist).pipe(switchMap(() => {
        return this.checklistService.getAll();
      })).subscribe(data => {
        this.checklistService.setObjetoCambio(data);
        this.checklistService.setMensajeCambio('Checklist creado');
        this.nuevoChecklist();
      });
    }
  }

  abrirChecklistDialogoBuscar() {
    let dialogRef = this.dialog.open(ChecklistDialogoBuscarComponent, {
      width: 'auto',
      height: 'auto'
    });

    dialogRef.afterClosed().subscribe(data => {
      if(data != undefined) {
        this.nuevoChecklist();
        this.checklist = data;
        this.servicios$ = this.servicioService.getAll();
        this.cargarChecklistBusqueda();
      }
    });
  }

  cargarChecklistBusqueda() {
    this.formChecklist = new FormGroup({
      'servicio' : new FormControl(this.checklist.servicio.idServicio, Validators.required),
      'placa' : new FormControl({value : this.checklist.servicio.placa.numero, disabled : true}, Validators.required),
      'fechaRevision' : new FormControl(this.checklist.fechaRevision, Validators.required),
      'kilometrajeActual' : new FormControl({value : this.checklist.servicio.kilometrajeRecorrido, disabled : true}, Validators.required),
      'kilometrajeProximo' : new FormControl({value : this.checklist.servicio.kilometrajeProximoServicio, disabled : true}, Validators.required),
      'checklistServicioTipo' : new FormControl(this.checklist.checklistServicioTipo.idChecklistServicioTipo, Validators.required),
      'observaciones' : new FormControl(this.checklist.observaciones),
      'mecanico' : new FormControl(this.checklist.mecanico.idPersonal, Validators.required),
      'supervisor' : new FormControl(this.checklist.supervisor.idPersonal, Validators.required)
    });

    this.servicioSeleccionado = this.checklist.servicio;
    this.idServicio = this.checklist.servicio.idServicio;
    this.fechaRevisionFormato = this.checklist.fechaRevision;
    this.idChecklistServicioTipo = this.checklist.checklistServicioTipo.idChecklistServicioTipo;
    this.idSupervisor = this.checklist.supervisor.idPersonal;
    this.idMecanico = this.checklist.mecanico.idPersonal;

    //BUBBLE SORT
    for(let i = 0; i < this.checklist.checklistDetalle.length; i++) {
      for(let j = 0; j < this.checklist.checklistDetalle.length - 1 - i; j++) {
        if(this.checklist.checklistDetalle[j].idChecklistDetalle > this.checklist.checklistDetalle[j+1].idChecklistDetalle) {
          [this.checklist.checklistDetalle[j], this.checklist.checklistDetalle[j+1]] = [this.checklist.checklistDetalle[j+1], this.checklist.checklistDetalle[j]];
        }
      }
    }

    //SEPARACION DE DETALLES EN MECANICOS Y ELECTRICOS
    for(let checklistDetalle of this.checklist.checklistDetalle) {
      if(!checklistDetalle.electrico) {
        this.checklistDetalleMecanico.push(checklistDetalle);
      } else {
        this.checklistDetalleElectrico.push(checklistDetalle);
      }
    }
  }

}
