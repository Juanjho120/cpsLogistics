import { SegmentoPagoDialogoEliminarComponent } from './segmento-pago-dialogo-eliminar/segmento-pago-dialogo-eliminar.component';
import { SegmentoPagoDTO } from './../../../_model/dto/segmentoPagoDTO';
import { SegmentoPago } from './../../../_model/segmentoPago';
import { MatSort } from '@angular/material/sort';
import { SegmentoPagoService } from './../../../_service/segmento-pago.service';
import { MatDialog } from '@angular/material/dialog';
import { SegmentoPagoDialogoComponent } from './segmento-pago-dialogo/segmento-pago-dialogo.component';
import { SegmentoCreditoDetalleDTO } from './../../../_model/dto/segmentoCreditoDetalleDTO';
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

@Component({
  selector: 'app-segmento-credito-pago',
  templateUrl: './segmento-credito-pago.component.html',
  styleUrls: ['./segmento-credito-pago.component.css']
})
export class SegmentoCreditoPagoComponent implements OnInit {

  form : FormGroup;

  segmentoCreditos : SegmentoCredito[];
  segmentoCreditoSeleccionado : SegmentoCredito = new SegmentoCredito();

  myControlNit : FormControl = new FormControl('', Validators.required);

  segmentoCreditoFiltrados$ : Observable<SegmentoCredito[]>;

  displayedColumnsDetalle = ['fechaEmision', 'servicio', 'placa', 'totalFacturado', 'totalPagado', 'totalRestante', 'accion'];
  displayedColumnsPago = ['fechaPago', 'servicio', 'placa', 'monto', 'acciones'];
  dataSourceDetalle : MatTableDataSource<SegmentoCreditoDetalle>;
  dataSourceDetalleDTO : MatTableDataSource<SegmentoCreditoDetalleDTO>;
  dataSourcePagoDTO : MatTableDataSource<SegmentoPagoDTO>;
  @ViewChild(MatSort) sort : MatSort;

  constructor(
    private segmentoCreditoService : SegmentoCreditoService,
    private segmentoCreditoDetalleService : SegmentoCreditoDetalleService,
    private segmentoPagoService : SegmentoPagoService,
    private snackBar : MatSnackBar,
    private dialog : MatDialog
  ) { }

  ngOnInit(): void {
    this.form = new FormGroup({
      'nit' : this.myControlNit
    });

    this.segmentoPagoService.getMensajeCambio().subscribe(data => {
      this.snackBar.open(data, 'AVISO', {duration : 2000});
    })

    this.segmentoCreditoSeleccionado.segmento = new Segmento();

    this.segmentoCreditoService.getAll().subscribe(data => {
      this.segmentoCreditos = data;
    });

    this.segmentoCreditoFiltrados$ = this.myControlNit.valueChanges.pipe(map(val => this.filtrarSegmentoCreditos(val)))
  }

  filtrarSegmentoCreditos(val : any) {
    document.getElementById("div-segmento").style.display = "none";
    if(val != null && val.idSegmentoCredito > 0) {
      return this.segmentoCreditos.filter(el => 
        el.segmento.nit.toLowerCase().includes(val.segmento.nit.toLowerCase())
      );
    }
    return this.segmentoCreditos.filter(el => 
      el.segmento.nit.toLowerCase().includes(val?.toLowerCase())
    );
  }

  mostrarSegmentoCredito(segmentoCredito : SegmentoCredito) {
    return segmentoCredito ? `${segmentoCredito.segmento.nit}` : segmentoCredito;
  }

  seleccionarSegmentoCredito(e : any) {
    this.segmentoCreditoSeleccionado = e.option.value;
  }

  buscar() {
    document.getElementById("div-segmento").style.display = "block";
    this.segmentoCreditoDetalleService.getBySegmento(this.segmentoCreditoSeleccionado.segmento.idSegmento).subscribe(data => {
      this.dataSourceDetalle = new MatTableDataSource(data);
    });

    this.segmentoCreditoDetalleService.getDTOBySegmento(this.segmentoCreditoSeleccionado.segmento.idSegmento).subscribe(data => {
      this.dataSourceDetalleDTO = new MatTableDataSource(data);
      this.dataSourceDetalleDTO.sort = this.sort;
    });

    this.segmentoPagoService.getDTOBySegmento(this.segmentoCreditoSeleccionado.segmento.idSegmento).subscribe(data => {
      this.dataSourcePagoDTO = new MatTableDataSource(data);
      this.dataSourcePagoDTO.sort = this.sort;
    });

  }

  abrirDialogoPago(segmentoCreditoDetalle : SegmentoCreditoDetalleDTO) {
    this.dialog.open(SegmentoPagoDialogoComponent, {
      width : '250px',
      data : segmentoCreditoDetalle
    })
  }

  abrirDialogoPagoEliminar(segmentoPagoDTO : SegmentoPagoDTO) {
    this.dialog.open(SegmentoPagoDialogoEliminarComponent, {
      width : '250px',
      data : segmentoPagoDTO
    })
  }

  filterPago(value : string) {
    this.dataSourcePagoDTO.filter = value.trim().toLowerCase();
  }

  filterDetalle(value : string) {
    this.dataSourceDetalleDTO.filter = value.trim().toLowerCase();
  }
}
