import { ProveedorMenorService } from './../../../_service/proveedor-menor.service';
import { Saldo } from './../../../_model/saldo';
import { SaldoService } from './../../../_service/saldo.service';
import { ComprobanteTipoService } from './../../../_service/comprobante-tipo.service';
import { PersonalService } from './../../../_service/personal.service';
import { CajaChicaDialogoEditarComponent } from './caja-chica-dialogo-editar/caja-chica-dialogo-editar.component';
import { CajaChicaDialogoEliminarComponent } from './caja-chica-dialogo-eliminar/caja-chica-dialogo-eliminar.component';
import { switchMap } from 'rxjs/operators';
import { MatTableDataSource } from '@angular/material/table';
import { CajaChica } from './../../../_model/cajaChica';
import { ServicioService } from './../../../_service/servicio.service';
import { Servicio } from './../../../_model/servicio';
import { MatDialog } from '@angular/material/dialog';
import { MatSnackBar } from '@angular/material/snack-bar';
import { CajaChicaService } from './../../../_service/caja-chica.service';
import { PlacaService } from './../../../_service/placa.service';
import { Placa } from './../../../_model/placa';
import { FormGroup, FormControl, Validators } from '@angular/forms';
import { Component, OnInit } from '@angular/core';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { Concepto } from 'src/app/_model/concepto';
import { Personal } from 'src/app/_model/personal';
import { ComprobanteTipo } from 'src/app/_model/comprobanteTipo';
import { ProveedorMenor } from 'src/app/_model/proveedorMenor';

@Component({
  selector: 'app-caja-chica',
  templateUrl: './caja-chica.component.html',
  styleUrls: ['./caja-chica.component.css']
})
export class CajaChicaComponent implements OnInit {

  formCajaChica : FormGroup;
  formBusqueda : FormGroup;

  idPlacaInService : number = 0;
  idPlacaAll : number = 0;
  placasInService$ : Observable<Placa[]>;
  placasAll$ : Observable<Placa[]>;

  idServicio : number = 0;
  servicios$ : Observable<Servicio[]>;

  idConceptoC : number = 0;
  idConceptoB : number = 0;

  personal$ : Observable<Personal[]>;
  idAutoriza : number = 0;
  idRecibe : number = 0;

  comprobanteTipos$ : Observable<ComprobanteTipo[]>;
  idComprobanteTipoC : number = 0;
  idComprobanteTipoB : number = 0;

  idProveedorMenorC : number = 0;
  idProveedorMenorB : number = 0;
  proveedoresMenores$ : Observable<ProveedorMenor[]>;

  saldoCajaChica : Saldo = new Saldo();

  idBusqueda : number = 0;

  maxFecha : Date = new Date();
  fechaInicioSeleccionada: Date = new Date();
  fechaFinSeleccionada: Date = new Date();
  fechaInicioFormato : string;
  fechaFinFormato : string;
  formatoFecha : string = 'YYYY-MM-DD';

  myControlFechaFin : FormControl = new FormControl('', Validators.required);
  myControlFechaInicio : FormControl = new FormControl('', Validators.required);
  myControlPlaca : FormControl = new FormControl('', Validators.required);
  myControlServicio : FormControl = new FormControl('', Validators.required);

  dataSource : MatTableDataSource<CajaChica>;
  displayedColumns = ['fechaIngreso','concepto', 'monto', 'autoriza', 'recibe', 'descripcion', 'comprobanteTipo', 'acciones'];

  constructor(
    private placaService : PlacaService,
    private servicioService : ServicioService,
    private cajaChicaService : CajaChicaService,
    private personalService : PersonalService,
    private comprobanteTipoService : ComprobanteTipoService,
    private proveedorMenorService : ProveedorMenorService,
    private saldoService : SaldoService,
    private snackBar : MatSnackBar,
    private dialog : MatDialog
  ) { }

  ngOnInit(): void {
    this.actualizarSaldo();

    this.placasInService$ = this.placaService.getInService();
    this.placasAll$ = this.placaService.getAll();

    this.servicios$ = this.servicioService.getAll();

    this.personal$ = this.personalService.getAll();

    this.comprobanteTipos$ = this.comprobanteTipoService.getAll();

    this.proveedoresMenores$ = this.proveedorMenorService.getAll();

    this.initFormCajaChica();

    this.initFormBusqueda();

    this.cajaChicaService.getAll().subscribe(data => {
      this.dataSource = new MatTableDataSource(data);
    });

    this.cajaChicaService.getMensajeCambio().subscribe(data => {
      this.snackBar.open(data, 'AVISO', {duration : 2000});
    });

    this.cajaChicaService.getObjetoCambio().subscribe(data => {
      this.dataSource = new MatTableDataSource(data);
    });

  }

  actualizarSaldo() {
    this.saldoService.getByNombre("Caja Chica").subscribe(data => {
      this.saldoCajaChica = data;
    });
  }

  initFormCajaChica() {
    this.formCajaChica = new FormGroup({
      'concepto' : new FormControl('', Validators.required),
      'monto' : new FormControl('', Validators.required),
      'autoriza' : new FormControl('', Validators.required),
      'recibe' : new FormControl('', Validators.required),
      'descripcion' : new FormControl('', Validators.required),
      'placa' : new FormControl(''),
      'comprobanteTipo' : new FormControl('', Validators.required),
      'proveedorMenor' : new FormControl('', Validators.required),
      'numeroComprobante' : new FormControl(''),
    });

    this.formCajaChica.get('proveedorMenor').disable();
    this.formCajaChica.get('numeroComprobante').disable();
  }

  initFormBusqueda() {
    this.formBusqueda = new FormGroup({
      'servicio' : this.myControlServicio,
      'placa' : this.myControlPlaca,
      'fechaInicio' : this.myControlFechaInicio,
      'fechaFin' : this.myControlFechaFin
    });

    this.formBusqueda.get('servicio').disable();
    this.formBusqueda.get('placa').disable();
    this.formBusqueda.get('fechaInicio').disable();
    this.formBusqueda.get('fechaFin').disable();
  }

  limpiarFormCajaChica() {
    this.formCajaChica.reset();
    this.idPlacaInService = 0;
    this.idConceptoC = 0;
    this.idAutoriza = 0;
    this.idRecibe = 0;
    this.idComprobanteTipoC = 0;
  }

  cambiarFechaInicio(e : any) {
    this.fechaInicioSeleccionada = e.value;
    this.fechaInicioFormato = moment(this.fechaInicioSeleccionada).format(this.formatoFecha);
  }

  cambiarFechaFin(e : any) {
    this.fechaFinSeleccionada = e.value;
    this.fechaFinFormato = moment(this.fechaFinSeleccionada).format(this.formatoFecha);
  }

  guardarCajaChica() {
    if(this.idPlacaInService==0) {
      let cajaChica = new CajaChica();
      cajaChica.servicio = null;
      cajaChica.concepto = new Concepto();
      cajaChica.concepto.idConcepto = this.idConceptoC;
      cajaChica.autoriza = new Personal();
      cajaChica.autoriza.idPersonal = this.idAutoriza;
      cajaChica.recibe = new Personal();
      cajaChica.recibe.idPersonal = this.idRecibe;
      cajaChica.descripcion = this.formCajaChica.value['descripcion'];
      cajaChica.comprobanteTipo = new ComprobanteTipo();
      cajaChica.comprobanteTipo.idComprobanteTipo = this.idComprobanteTipoC;
      cajaChica.numeroComprobante = this.formCajaChica.value['numeroComprobante'];
      cajaChica.monto = this.formCajaChica.value['monto'];

      this.cajaChicaService.create(cajaChica).pipe(switchMap(() => {
        return this.cajaChicaService.getAll();
      })).subscribe(data => {
        this.cajaChicaService.setObjetoCambio(data);
        this.cajaChicaService.setMensajeCambio('Caja chica creada');
        this.limpiarFormCajaChica();
        this.actualizarSaldo();
      });
    } else {
      this.servicioService.getByPlacaAndFinalizado(this.idPlacaInService, false).subscribe(data => {
        let cajaChica = new CajaChica();
        cajaChica.servicio = new Servicio();
        cajaChica.servicio.idServicio = data[0].idServicio;
        cajaChica.concepto = new Concepto();
        cajaChica.concepto.idConcepto = this.idConceptoC;
        cajaChica.autoriza = new Personal();
        cajaChica.autoriza.idPersonal = this.idAutoriza;
        cajaChica.recibe = new Personal();
        cajaChica.recibe.idPersonal = this.idRecibe;
        cajaChica.descripcion = this.formCajaChica.value['descripcion'];
        cajaChica.comprobanteTipo = new ComprobanteTipo();
        cajaChica.comprobanteTipo.idComprobanteTipo = this.idComprobanteTipoC;
        cajaChica.numeroComprobante = this.formCajaChica.value['numeroComprobante'];
        cajaChica.monto = this.formCajaChica.value['monto'];
        this.cajaChicaService.create(cajaChica).pipe(switchMap(() => {
          return this.cajaChicaService.getAll();
        })).subscribe(data => {
          this.cajaChicaService.setObjetoCambio(data);
          this.cajaChicaService.setMensajeCambio('Caja chica creada');
          this.limpiarFormCajaChica();
          this.actualizarSaldo();
        });
      });
    }
  }

  buscarCajaChica() {
    if(this.idBusqueda == 0 || this.idBusqueda == 5) {
      this.cajaChicaService.getAll().subscribe(data => {
        this.dataSource = new MatTableDataSource(data);
      });
    } else if(this.idBusqueda == 1) {
      this.cajaChicaService.getByServicio(this.idServicio).subscribe(data => {
        this.dataSource = new MatTableDataSource(data);
      });
    } else if(this.idBusqueda == 2) {
      this.cajaChicaService.getByPlaca(this.idPlacaAll).subscribe(data => {
        this.dataSource = new MatTableDataSource(data);
      });
    } else if(this.idBusqueda == 4) {
      this.cajaChicaService.getByFechaIngreso(this.fechaInicioFormato, this.fechaFinFormato).subscribe(data => {
        this.dataSource = new MatTableDataSource(data);
      });
    }
  }

  setInputs() {
    if(this.idBusqueda == 1) {
      this.setServicioInput(true);
      this.setPlacaInput(false);
      this.setFechaInput(false);
    } else if(this.idBusqueda == 2) {
      this.setServicioInput(false);
      this.setPlacaInput(true);
      this.setFechaInput(false);
    } else if(this.idBusqueda == 4) {
      this.setServicioInput(false);
      this.setPlacaInput(false);
      this.setFechaInput(true);
    } else if(this.idBusqueda == 5) {
      this.setServicioInput(false);
      this.setPlacaInput(false);
      this.setFechaInput(false);
    }
  }

  setServicioInput(e : boolean) {
    this.dataSource = new MatTableDataSource();
    this.myControlServicio.reset();
    if(e) {
      this.formBusqueda.get('servicio').enable();
    } else {
      this.formBusqueda.get('servicio').disable();
    }
  }

  setPlacaInput(e : boolean) {
    this.dataSource = new MatTableDataSource();
    this.myControlPlaca.reset();
    if(e) {
      this.formBusqueda.get('placa').enable();
    } else {
      this.formBusqueda.get('placa').disable();
    }
  }

  setFechaInput(e : boolean) {
    this.dataSource = new MatTableDataSource();
    this.myControlFechaInicio.reset();
    this.myControlFechaFin.reset();
    if(e) {
      this.formBusqueda.get('fechaInicio').enable();
      this.formBusqueda.get('fechaFin').enable();
    } else {
      this.formBusqueda.get('fechaInicio').disable();
      this.formBusqueda.get('fechaFin').disable();
    }
  }
  
  abrirDialogoEditar(cajaChica : CajaChica) {
    let dialogRef = this.dialog.open(CajaChicaDialogoEditarComponent, {
      width: '400px',
      data: cajaChica
    });

    dialogRef.afterClosed().subscribe(() => {
      this.actualizarSaldo();
    });
  }

  abrirDialogoEliminar(cajaChica : CajaChica) {
    let dialogRef = this.dialog.open(CajaChicaDialogoEliminarComponent, {
      width: '400px',
      data: cajaChica
    });

    dialogRef.afterClosed().subscribe(() => {
      this.actualizarSaldo();
    });
  }

  evaluarProveedorSelect() {
    this.formCajaChica.get('proveedorMenor').disable();
    this.formCajaChica.get('numeroComprobante').disable();
    if(this.idComprobanteTipoC==1) {
      this.formCajaChica.get('proveedorMenor').enable();
      this.formCajaChica.get('numeroComprobante').enable();
    } else if(this.idComprobanteTipoC==2) {
      this.formCajaChica.get('numeroComprobante').enable();
    }
  }

}
