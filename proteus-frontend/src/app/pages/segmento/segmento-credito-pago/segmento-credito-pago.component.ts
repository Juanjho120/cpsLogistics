import { SegmentoPagoDetalleDialogoComponent } from './segmento-pago-detalle-dialogo/segmento-pago-detalle-dialogo.component';
import { SegmentoService } from './../../../_service/segmento.service';
import { SegmentoCreditoDetalleDialogoComponent } from './segmento-credito-detalle-dialogo/segmento-credito-detalle-dialogo.component';
import { ServicioDTO } from './../../../_model/dto/servicioDTO';
import { ServicioService } from './../../../_service/servicio.service';
import { Servicio } from './../../../_model/servicio';
import { SegmentoPago } from './../../../_model/segmentoPago';
import { SegmentoPagoDialogoEliminarComponent } from './segmento-pago-dialogo-eliminar/segmento-pago-dialogo-eliminar.component';
import { MatSort } from '@angular/material/sort';
import { SegmentoPagoService } from './../../../_service/segmento-pago.service';
import { MatDialog } from '@angular/material/dialog';
import { SegmentoPagoDialogoComponent } from './segmento-pago-dialogo/segmento-pago-dialogo.component';
import { SegmentoCreditoDetalleService } from './../../../_service/segmento-credito-detalle.service';
import { MatTableDataSource } from '@angular/material/table';
import { SegmentoCreditoDetalle } from './../../../_model/segmentoCreditoDetalle';
import { SegmentoCredito } from './../../../_model/segmentoCredito';
import { Segmento } from './../../../_model/segmento';
import { MatSnackBar } from '@angular/material/snack-bar';
import { SegmentoCreditoService } from './../../../_service/segmento-credito.service';
import { Observable } from 'rxjs';
import { FormGroup, FormControl, Validators } from '@angular/forms';
import { Component, OnInit, ViewChild } from '@angular/core';
import { map } from 'rxjs/operators';
import * as moment from 'moment';

@Component({
  selector: 'app-segmento-credito-pago',
  templateUrl: './segmento-credito-pago.component.html',
  styleUrls: ['./segmento-credito-pago.component.css']
})
export class SegmentoCreditoPagoComponent implements OnInit {

  form : FormGroup;

  segmentos$ : Observable<Segmento[]>;
  idSegmento : number = 0;
  segmentoCredito : SegmentoCredito = new SegmentoCredito();

  formatoFecha : string = 'YYYY-MM-DD';

  serviciosDTO : ServicioDTO[] = [];

  displayedColumnsDetalle = ['fechaEmision', 'facturaNumero', 'servicio', 'placa', 'totalFacturado', 'totalPagado', 'totalRestante'];
  displayedColumnsPago = ['fechaPago', 'pagoTipo', 'facturas', 'monto', 'acciones'];
  displayedColumnsServicio = ['noServicio', 'fecha', 'servicioTipo', 'placaServicio', 'segmento', 'trabajo', 'repuesto', 'total', 'facturar'];
  dataSourceDetalle : MatTableDataSource<SegmentoCreditoDetalle>;
  dataSourcePago : MatTableDataSource<SegmentoPago>;
  dataSourceServicio : MatTableDataSource<ServicioDTO>;
  @ViewChild(MatSort) sort : MatSort;

  constructor(
    private segmentoCreditoService : SegmentoCreditoService,
    private segmentoCreditoDetalleService : SegmentoCreditoDetalleService,
    private segmentoPagoService : SegmentoPagoService,
    private segmentoService : SegmentoService,
    private servicioService : ServicioService,
    private snackBar : MatSnackBar,
    private dialog : MatDialog
  ) { }

  ngOnInit(): void {
    this.form = new FormGroup({
      'segmento' : new FormControl('', Validators.required)
    });

    this.segmentoPagoService.getMensajeCambio().subscribe(data => {
      this.snackBar.open(data, 'AVISO', {duration : 2000});
    });

    this.segmentoCreditoService.getMensajeCambio().subscribe(data => {
      this.snackBar.open(data, 'AVISO', {duration : 2000});
    });

    this.servicioService.getMensajeCambio().subscribe(data => {
      this.snackBar.open(data, 'AVISO', {duration : 2000});
    });

    this.segmentoCredito.segmento = new Segmento();
    this.segmentoCredito.credito = 0;

    this.segmentos$ = this.segmentoService.getWithCredito();

    this.servicioService.getByFinalizadoAndFacturado(true, false).subscribe(data => {
      for(let servicio of data) {
        this.serviciosDTO.push(this.convertirServicio(servicio));
      }
      this.dataSourceServicio = new MatTableDataSource(this.serviciosDTO);
    });
  }

  buscarSegmentoCredito() {
    this.dataSourceDetalle = new MatTableDataSource();
    this.dataSourcePago = new MatTableDataSource();
    document.getElementById("div-segmento").style.display = "block";

    this.segmentoCreditoService.getBySegmento(this.idSegmento).subscribe(data => {
      this.segmentoCredito = data;
    });

    this.segmentoCreditoDetalleService.getBySegmento(this.idSegmento).subscribe(data => {
      let segmentoCreditoDetalles : SegmentoCreditoDetalle[] = [];
      for(let segmentoCreditoDetalle of data) {
        segmentoCreditoDetalle.fechaHoraEmision = moment(segmentoCreditoDetalle.fechaHoraEmision).format(this.formatoFecha);
        segmentoCreditoDetalles.push(segmentoCreditoDetalle);
      }
      this.dataSourceDetalle = new MatTableDataSource(segmentoCreditoDetalles);
      this.dataSourceDetalle.sort = this.sort;
    });

    this.segmentoPagoService.getBySegmento(this.idSegmento).subscribe(data => {
      let segmentoPagos : SegmentoPago[] = [];
      for(let segmentoPago of data) {
        segmentoPago.fechaHoraPago = moment(segmentoPago.fechaHoraPago).format(this.formatoFecha);
        segmentoPago.facturas = "";
        for(let segmentoPagoDetalle of segmentoPago.segmentoPagoDetalle) {
          segmentoPago.facturas += `${segmentoPagoDetalle.segmentoCreditoDetalle.facturaNumero} `;
        }
        segmentoPagos.push(segmentoPago);
      }
      this.dataSourcePago = new MatTableDataSource(segmentoPagos);
      this.dataSourcePago.sort = this.sort;
    });

  }

  abrirDialogoSegmentoPagoDetalle(idSegmentoPago : number) {
    this.dialog.open(SegmentoPagoDetalleDialogoComponent, {
      width : '1000px',
      data : idSegmentoPago
    })
  }

  abrirDialogoSegmentoPagoEliminar(segmentoPago : SegmentoPago) {
    let dialogRef = this.dialog.open(SegmentoPagoDialogoEliminarComponent, {
      width : '350px',
      data : segmentoPago
    });

    dialogRef.afterClosed().subscribe(() => {
      this.buscarSegmentoCredito();
    });
  }

  filterPago(value : string) {
    this.dataSourcePago.filter = value.trim().toLowerCase();
  }

  filterDetalle(value : string) {
    this.dataSourceDetalle.filter = value.trim().toLowerCase();
  }

  abrirDialogoSegmentoPago() {
    let dialogRef = this.dialog.open(SegmentoPagoDialogoComponent, {
      width : '500px',
      data : this.segmentoCredito
    });

    dialogRef.afterClosed().subscribe(() => {
      this.buscarSegmentoCredito();
    });
  }

  convertirServicio(servicio : Servicio) {
    let servicioDTO = new ServicioDTO();
    servicioDTO.noServicio = servicio.idServicio;
    servicioDTO.fecha = moment(servicio.fechaHora).format(this.formatoFecha);
    servicioDTO.servicioTipo = servicio.servicioTipo.nombre;
    servicioDTO.placa = servicio.placa.numero;
    servicioDTO.segmento = servicio.segmento.nombre;
    servicioDTO.proximoServicio = servicio.kilometrajeProximoServicio;
    servicioDTO.costoTotal = `Q. ${servicio.costoTotal.toFixed(2)}`;
    servicioDTO.facturado = servicio.facturado;
    servicioDTO.finalizado = servicio.finalizado;

    let costoTrabajo = 0;
    for(let servicioTrabajo of servicio.servicioTrabajo) {
      costoTrabajo += servicioTrabajo.costo;
    }

    let costoRepuesto = 0;
    for(let servicioRepuesto of servicio.servicioRepuesto) {
      costoRepuesto += servicioRepuesto.costoTotal;
    }

    servicioDTO.costoTrabajo = `Q. ${costoTrabajo.toFixed(2)}`;
    servicioDTO.costoRepuesto = `Q. ${costoRepuesto.toFixed(2)}`;

    return servicioDTO;
  }

  abrirDialogoSegmentoCreditoDetalle(servicioDTO : ServicioDTO) {
    let dialogRef = this.dialog.open(SegmentoCreditoDetalleDialogoComponent, {
      width : '250px',
      data : servicioDTO.noServicio
    });

    dialogRef.afterClosed().subscribe(() => {
      this.dataSourceServicio = new MatTableDataSource();
      this.serviciosDTO = [];
      this.servicioService.getByFacturado(false).subscribe(data => {
        for(let servicio of data) {
          this.serviciosDTO.push(this.convertirServicio(servicio));
        }
        this.dataSourceServicio = new MatTableDataSource(this.serviciosDTO);
      });
    });
  }

}
