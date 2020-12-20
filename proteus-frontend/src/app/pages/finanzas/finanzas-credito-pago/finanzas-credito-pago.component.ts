import { PagoProveedorDetalleDialogoComponent } from './pago-proveedor-detalle-dialogo/pago-proveedor-detalle-dialogo.component';
import { TransaccionService } from './../../../_service/transaccion.service';
import { ChequeService } from './../../../_service/cheque.service';
import { PagoProveedorDTO } from './../../../_model/dto/pagoProveedorDTO';
import { PagoProveedorDialogoEliminarComponent } from './pago-proveedor-dialogo-eliminar/pago-proveedor-dialogo-eliminar.component';
import { PagoProveedorDialogoComponent } from './pago-proveedor-dialogo/pago-proveedor-dialogo.component';
import { PagoProveedorService } from './../../../_service/pago-proveedor.service';
import { CreditoProveedorService } from './../../../_service/credito-proveedor.service';
import { CreditoProveedorDetalle } from './../../../_model/creditoProveedorDetalle';
import { CreditoProveedor } from './../../../_model/creditoProveedor';
import { ProveedorService } from './../../../_service/proveedor.service';
import { Proveedor } from './../../../_model/proveedor';
import { Observable } from 'rxjs';
import { FormGroup, FormControl, Validators } from '@angular/forms';
import { CreditoProveedorDetalleService } from './../../../_service/credito-proveedor-detalle.service';
import { CreditoProveedorDetalleDialogoComponent } from './credito-proveedor-detalle-dialogo/credito-proveedor-detalle-dialogo.component';
import { MatDialog } from '@angular/material/dialog';
import { MatSnackBar } from '@angular/material/snack-bar';
import { FacturaCompraService } from './../../../_service/factura-compra.service';
import { FacturaCompra } from './../../../_model/facturaCompra';
import { MatTableDataSource } from '@angular/material/table';
import { Component, OnInit } from '@angular/core';
import * as moment from 'moment';

@Component({
  selector: 'app-finanzas-credito-pago',
  templateUrl: './finanzas-credito-pago.component.html',
  styleUrls: ['./finanzas-credito-pago.component.css']
})
export class FinanzasCreditoPagoComponent implements OnInit {

  dataSourceFactura : MatTableDataSource<FacturaCompra>;
  displayedColumnsFactura = ['codigo', 'fecha', 'proveedor', 'total', 'asignar'];

  dataSourceCreditoDetalle : MatTableDataSource<CreditoProveedorDetalle>;
  displayedColumnsCreditoDetalle = ['codigo', 'fechaFactura', 'observaciones', 'total', 'vencida'];

  dataSourcePagoProveedor : MatTableDataSource<PagoProveedorDTO>;
  displayedColumnsPagoProveedor = ['tipoDocumento', 'noDocumento', 'fechaPago', 'observaciones', 'monto', 'acciones'];

  formBusqueda : FormGroup;
  proveedores$ : Observable<Proveedor[]>;
  idProveedor : number = 0;

  creditoProveedor : CreditoProveedor;
  creditoProveedorDetalles : CreditoProveedorDetalle[] = [];
  pagoProveedores : PagoProveedorDTO[] = [];

  formatoFechaHora : string = 'YYYY-MM-DD';

  constructor(
    private facturaCompraService : FacturaCompraService,
    private proveedorService : ProveedorService,
    private creditoProveedorService : CreditoProveedorService,
    private creditoProveedorDetalleService : CreditoProveedorDetalleService,
    private chequeService : ChequeService,
    private transaccionService : TransaccionService,
    private pagoProveedorService : PagoProveedorService,
    private dialog : MatDialog,
    private snackBar : MatSnackBar
  ) { }

  ngOnInit(): void {

    this.facturaCompraService.getNotInCreditoProveedorDetalle().subscribe(data => {
      this.dataSourceFactura = new MatTableDataSource(data);
    });

    this.facturaCompraService.getMensajeCambio().subscribe(data => {
      this.snackBar.open(data, 'AVISO', {duration : 2000});
    });

    this.creditoProveedorService.getMensajeCambio().subscribe(data => {
      this.snackBar.open(data, 'AVISO', {duration : 2000});
    });

    this.pagoProveedorService.getMensajeCambio().subscribe(data => {
      this.snackBar.open(data, 'AVISO', {duration : 2000});
    });

    this.creditoProveedorDetalleService.getMensajeCambio().subscribe(data => {
      this.snackBar.open(data, 'AVISO', {duration : 2000});
    });

    this.creditoProveedorDetalleService.checkVencimiento().subscribe(() => {

    });

    this.formBusqueda = new FormGroup({
      'proveedor' : new FormControl('', Validators.required)
    });

    this.proveedores$ = this.proveedorService.getWithCredito();

    this.creditoProveedor = new CreditoProveedor();
    this.creditoProveedor.proveedor = new Proveedor();
    this.creditoProveedor.proveedor.nombre = "NOMBRE PROVEEDOR";
    this.creditoProveedor.proveedor.nit = "NIT PROVEEDOR";
    this.creditoProveedor.deudaAcumulativa = 0;
  }

  asignarFactura(facturaCompra : FacturaCompra) {
    let dialogRef = this.dialog.open(CreditoProveedorDetalleDialogoComponent, {
      width: '400px',
      data: facturaCompra
    });

    dialogRef.afterClosed().subscribe(() => {
      this.dataSourceFactura = new MatTableDataSource();
      this.facturaCompraService.getNotInCreditoProveedorDetalle().subscribe(data => {
        this.dataSourceFactura = new MatTableDataSource(data);
        this.buscarCreditoProveedor();
      });
    });
  }

  buscarCreditoProveedor() {
    this.creditoProveedorDetalleService.checkVencimiento();
    this.dataSourceCreditoDetalle = new MatTableDataSource();
    this.dataSourcePagoProveedor = new MatTableDataSource();
    this.creditoProveedor = new CreditoProveedor();

    if(this.idProveedor == 0) {
      return;
    } else {
      this.creditoProveedorService.getByProveedor(this.idProveedor).subscribe(data => {
        this.creditoProveedor = data;
  
        document.getElementById("div-credito-proveedor").style.display = "block";
        this.creditoProveedorDetalleService.getByCreditoProveedorAndPagada(this.creditoProveedor.idCreditoProveedor, false).subscribe(dato => {
          this.creditoProveedorDetalles = dato;
  
          this.dataSourceCreditoDetalle = new MatTableDataSource(this.creditoProveedorDetalles);
        });

        this.pagoProveedorService.getByCreditoProveedor(this.creditoProveedor.idCreditoProveedor).subscribe(data => {
          this.pagoProveedores = [];

          for(let pagoProveedor of data) {
            let pagoProveedorDto = new PagoProveedorDTO();
            pagoProveedorDto.idPagoProveedor = pagoProveedor.idPagoProveedor;
            pagoProveedorDto.tipoDocumento = pagoProveedor.pagoTipo.nombre;
            pagoProveedorDto.monto = pagoProveedor.monto;
            pagoProveedorDto.fechaPago = moment(pagoProveedor.fechaHoraPago).format(this.formatoFechaHora);
            pagoProveedorDto.observaciones = pagoProveedor.observaciones;

            if(pagoProveedor.pagoTipo.nombre === "CHEQUE") {
              this.chequeService.getById(pagoProveedor.idItem).subscribe(dataCheque => {
                pagoProveedorDto.noDocumento = dataCheque.numero;
              });
            } else if(pagoProveedor.pagoTipo.nombre === "TRANSACCION") {
              this.transaccionService.getById(pagoProveedor.idItem).subscribe(dataTransaccion => {
                pagoProveedorDto.noDocumento = dataTransaccion.referencia;
              });
            } else {
              pagoProveedorDto.noDocumento = "";
            }
            this.pagoProveedores.push(pagoProveedorDto);
          }
          this.dataSourcePagoProveedor = new MatTableDataSource(this.pagoProveedores);
        });
      });
    }
  }

  abrirDialogoPagoProveedor() {
    let dialogRef = this.dialog.open(PagoProveedorDialogoComponent, {
      width: '500px',
      data: this.creditoProveedor
    });

    dialogRef.afterClosed().subscribe(() => {
      this.buscarCreditoProveedor();
    });
  }

  abrirDialogoPagoProveedorEliminar(pagoProveedorDto : PagoProveedorDTO) {
    let dialogRef = this.dialog.open(PagoProveedorDialogoEliminarComponent, {
      width: '450px',
      data: pagoProveedorDto
    });

    dialogRef.afterClosed().subscribe(() => {
      this.buscarCreditoProveedor();
    });
  }

  abrirDialogoPagoProveedorDetalle(idPagoProveedor : number) {
    this.dialog.open(PagoProveedorDetalleDialogoComponent, {
      width: '1000px',
      data: idPagoProveedor
    });
  }

 }
