import { MatSnackBar } from '@angular/material/snack-bar';
import { MatDialogRef } from '@angular/material/dialog';
import { MatTableDataSource } from '@angular/material/table';
import { Servicio } from './../../../../_model/servicio';
import { FormGroup, FormControl, Validators } from '@angular/forms';
import { Observable } from 'rxjs';
import { Segmento } from './../../../../_model/segmento';
import { ServicioService } from './../../../../_service/servicio.service';
import { SegmentoService } from './../../../../_service/segmento.service';
import { Component, OnInit } from '@angular/core';
import * as moment from 'moment';
import { Cotizacion } from 'src/app/_model/cotizacion';

@Component({
  selector: 'app-servicio-dialogo-buscar',
  templateUrl: './servicio-dialogo-buscar.component.html',
  styleUrls: ['./servicio-dialogo-buscar.component.css']
})
export class ServicioDialogoBuscarComponent implements OnInit {

  formBusqueda : FormGroup;

  idSegmento : number = 0;
  segmentos$ : Observable<Segmento[]>;

  dataSource : MatTableDataSource<Servicio>;
  servicioFormato : Servicio[] = [];
  displayedColumns = ['fecha', 'servicioTipo', 'segmento', 'placa', 'costo', 'cotizacion', 'select'];

  formatoFecha : string = 'YYYY-MM-DD';
  formatoFechaHora : string = 'YYYY-MM-DD 00:00:00';
  formatoFechaHoraB : string = 'YYYY-MM-DD HH:mm:ss';

  myControlSegmento : FormControl = new FormControl('', Validators.required);

  constructor(
    private segmentoService : SegmentoService,
    private servicioService : ServicioService,
    private snackBar : MatSnackBar,
    private dialogRef : MatDialogRef<ServicioDialogoBuscarComponent>
  ) { }

  ngOnInit(): void {
    this.segmentos$ = this.segmentoService.getAll();

    this.servicioService.getByFinalizadoAndFacturado(false, false).subscribe(data => {
      this.servicioFormato = [];
      for(let servicio of data) {
        servicio.fechaHora = moment(servicio.fechaHora).format(this.formatoFecha);
        if(servicio.cotizacion==null) {
          servicio.cotizacion = new Cotizacion();
          servicio.cotizacion.idCotizacion = 0;
        }
        this.servicioFormato.push(servicio);
      }
      this.dataSource = new MatTableDataSource(this.servicioFormato);
    });

    this.servicioService.getMensajeCambio().subscribe(data => {
      this.snackBar.open(data, 'AVISO', {duration : 2000});
    });

    this.initForm();
  }

  initForm() {
    this.formBusqueda = new FormGroup({
      'segmento' : this.myControlSegmento
    });
  }

  buscar() {
    if(this.idSegmento == 0) {
      return;
    } else {
      this.servicioService.getBySegmentoAndFinalizadoAndFacturado(this.idSegmento, false, false).subscribe(data => {
        this.servicioFormato = [];
        for(let servicio of data) {
          servicio.fechaHora = moment(servicio.fechaHora).format(this.formatoFecha);
          if(servicio.cotizacion==null) {
            servicio.cotizacion = new Cotizacion();
            servicio.cotizacion.idCotizacion = 0;
          }
          this.servicioFormato.push(servicio);
        }
        this.dataSource = new MatTableDataSource(this.servicioFormato);
      });
    }
  }

  cerrar(checked : boolean, servicio : Servicio) {
    if(checked) {
      this.dialogRef.close(servicio);
    }
  }

}
