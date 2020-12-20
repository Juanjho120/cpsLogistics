import { MatSnackBar } from '@angular/material/snack-bar';
import { Cheque } from './../../../../_model/cheque';
import { PagoProveedorDetalle } from './../../../../_model/pagoProveedorDetalle';
import { PagoProveedorTransaccionChequeDTO } from './../../../../_model/dto/pagoProveedorTransaccionChequeDTO';
import { CuentaBancaria } from './../../../../_model/cuentaBancaria';
import { CuentaBancariaService } from './../../../../_service/cuenta-bancaria.service';
import { PagoTipoService } from './../../../../_service/pago-tipo.service';
import { ChequeService } from './../../../../_service/cheque.service';
import { PagoTipo } from './../../../../_model/pagoTipo';
import { MatTableDataSource } from '@angular/material/table';
import { CreditoProveedorDetalle } from './../../../../_model/creditoProveedorDetalle';
import { CreditoProveedorDetalleService } from './../../../../_service/credito-proveedor-detalle.service';
import { switchMap } from 'rxjs/operators';
import { PagoProveedor } from './../../../../_model/pagoProveedor';
import { MAT_DIALOG_DATA, MatDialogRef } from '@angular/material/dialog';
import { PagoProveedorService } from './../../../../_service/pago-proveedor.service';
import { CreditoProveedor } from './../../../../_model/creditoProveedor';
import { FormGroup, FormControl, Validators } from '@angular/forms';
import { Component, OnInit, Inject } from '@angular/core';
import { Observable } from 'rxjs';
import * as moment from 'moment';

@Component({
  selector: 'app-pago-proveedor-dialogo',
  templateUrl: './pago-proveedor-dialogo.component.html',
  styleUrls: ['./pago-proveedor-dialogo.component.css']
})
export class PagoProveedorDialogoComponent implements OnInit {

  formPago : FormGroup;
  formCheque : FormGroup;
  proveedorNombre : string = "";

  creditoProveedorDetallesPorPagar : CreditoProveedorDetalle[] = [];
  creditoProveedorDetallesAPagar : CreditoProveedorDetalle[] = [];

  dataSourceFacturasPorPagar : MatTableDataSource<CreditoProveedorDetalle>;
  dataSourceFacturasAPagar : MatTableDataSource<CreditoProveedorDetalle>;

  displayedColumnsFactura = ['codigo', 'fecha', 'total'];

  pagoTipos$ : Observable<PagoTipo[]>;
  idPagoTipo : number = 0;

  cuentasBancarias$ : Observable<CuentaBancaria[]>;
  idCuentaBancaria : number = 0;

  formatoFecha : string = 'YYYY-MM-DD';
  maxFecha: Date = new Date();
  fechaEmisionSeleccionada: Date = new Date();
  fechaEmisionFormato : string;

  constructor(
    private dialogRef : MatDialogRef<PagoProveedorDialogoComponent>,
    @Inject(MAT_DIALOG_DATA) private data : CreditoProveedor,
    private pagoProveedorService : PagoProveedorService,
    private creditoProveedorDetalleService : CreditoProveedorDetalleService,
    private chequeService : ChequeService,
    private pagoTipoService : PagoTipoService,
    private cuentaBancariaService : CuentaBancariaService,
    private snackBar : MatSnackBar
  ) { }

  ngOnInit(): void {
    this.proveedorNombre = this.data.proveedor.nombre;
    this.dataSourceFacturasAPagar = new MatTableDataSource();
    this.creditoProveedorDetalleService.getByCreditoProveedorAndPagada(this.data.idCreditoProveedor, false).subscribe(data => {
      this.creditoProveedorDetallesPorPagar = data;
      this.dataSourceFacturasPorPagar = new MatTableDataSource(this.creditoProveedorDetallesPorPagar);
    });

    this.pagoTipos$ = this.pagoTipoService.getAll();

    this.cuentasBancarias$ = this.cuentaBancariaService.getAll();

    this.idPagoTipo = 2; //PARA CHEQUES SIEMPRE

    this.initFormPago();

    this.initFormCheque();

  }
  
  initFormPago() {
    this.formPago = new FormGroup({
      'pagoTipo' : new FormControl(this.idPagoTipo, Validators.required),
      'observaciones' : new FormControl('', Validators.required)
    });

    this.formPago.get('pagoTipo').disable();
  }

  initFormCheque() {
    this.formCheque = new FormGroup({
      'cuentaBancaria' : new FormControl('', Validators.required),
      'numero' : new FormControl('', Validators.required),
      'monto' : new FormControl('', Validators.required),
      'fechaEmision' : new FormControl('', Validators.required)
    });
  }

  removerFacturaPorPagar(i : number) {
    this.creditoProveedorDetallesAPagar.push(this.creditoProveedorDetallesPorPagar[i]);
    this.creditoProveedorDetallesPorPagar.splice(i, 1);

    this.dataSourceFacturasAPagar = new MatTableDataSource(this.creditoProveedorDetallesAPagar);
    this.dataSourceFacturasPorPagar = new MatTableDataSource(this.creditoProveedorDetallesPorPagar);
  }

  removerFacturaAPagar(i : number) {
    this.creditoProveedorDetallesPorPagar.push(this.creditoProveedorDetallesAPagar[i]);
    this.creditoProveedorDetallesAPagar.splice(i, 1);

    this.dataSourceFacturasAPagar = new MatTableDataSource(this.creditoProveedorDetallesAPagar);
    this.dataSourceFacturasPorPagar = new MatTableDataSource(this.creditoProveedorDetallesPorPagar);
  }

  cambiarFechaEmision(e : any) {
    this.fechaEmisionSeleccionada = e.value;
    this.fechaEmisionFormato = moment(this.fechaEmisionSeleccionada).format(this.formatoFecha);
  }

  evaluarGuardarPagoProveedorBoton() {
    if(this.formPago.valid && this.formCheque.valid && this.creditoProveedorDetallesAPagar.length > 0) {
      return false;
    }
    return true;
  }

  guardarPagoProveedor() {

    let pagoProveedorDto = new PagoProveedorTransaccionChequeDTO();
    pagoProveedorDto.pagoProveedor = new PagoProveedor();
    pagoProveedorDto.pagoProveedor.pagoTipo = new PagoTipo();
    pagoProveedorDto.pagoProveedor.pagoTipo.idPagoTipo = this.idPagoTipo;
    pagoProveedorDto.pagoProveedor.monto = this.formCheque.value['monto'];
    pagoProveedorDto.pagoProveedor.observaciones = this.formPago.value['observaciones'];
    pagoProveedorDto.pagoProveedor.pagoProveedorDetalle = [];
    
    let totalPago = 0;
    for(let creditoProveedorDetalleAux of this.creditoProveedorDetallesAPagar) {
      totalPago += creditoProveedorDetalleAux.facturaCompra.total;
      let pagoProveedorDetalle = new PagoProveedorDetalle();
      pagoProveedorDetalle.creditoProveedorDetalle = new CreditoProveedorDetalle();
      pagoProveedorDetalle.creditoProveedorDetalle.idCreditoProveedorDetalle = creditoProveedorDetalleAux.idCreditoProveedorDetalle;
      pagoProveedorDto.pagoProveedor.pagoProveedorDetalle.push(pagoProveedorDetalle);
    }

    if(totalPago > pagoProveedorDto.pagoProveedor.monto || totalPago < pagoProveedorDto.pagoProveedor.monto) {
      this.snackBar.open('El monto a pagar debe ser igual a la suma de las facturas seleccionadas', 'AVISO', {duration : 2000});
      return;
    }

    if(this.idPagoTipo == 2) {
      let cheque = new Cheque();
      cheque.cuentaBancaria = new CuentaBancaria();
      cheque.cuentaBancaria.idCuentaBancaria = this.idCuentaBancaria;
      cheque.numero = this.formCheque.value['numero'];
      cheque.monto = this.formCheque.value['monto'];
      cheque.fechaEmision = this.fechaEmisionFormato;

      pagoProveedorDto.cheque = cheque;
    }

    this.pagoProveedorService.createDTO(pagoProveedorDto).subscribe(() => {
      this.pagoProveedorService.setMensajeCambio('Pago a proveedor creado');
      this.cerrar();
    });
  }

  cerrar() {
    this.dialogRef.close();
  }

 }
