import { MonedaDialogoEliminarComponent } from './moneda/moneda-dialogo-eliminar/moneda-dialogo-eliminar.component';
import { MonedaDialogoEditarComponent } from './moneda/moneda-dialogo-editar/moneda-dialogo-editar.component';
import { CuentaBancariaTipoDialogoEliminarComponent } from './cuenta-bancaria-tipo/cuenta-bancaria-tipo-dialogo-eliminar/cuenta-bancaria-tipo-dialogo-eliminar.component';
import { CuentaBancariaTipoDialogoEditarComponent } from './cuenta-bancaria-tipo/cuenta-bancaria-tipo-dialogo-editar/cuenta-bancaria-tipo-dialogo-editar.component';
import { BancoDialogoEliminarComponent } from './banco/banco-dialogo-eliminar/banco-dialogo-eliminar.component';
import { BancoDialogoEditarComponent } from './banco/banco-dialogo-editar/banco-dialogo-editar.component';
import { CuentaBancariaDialogoEliminarComponent } from './cuenta-bancaria-dialogo-eliminar/cuenta-bancaria-dialogo-eliminar.component';
import { MatDialog } from '@angular/material/dialog';
import { CuentaBancariaDialogoEditarComponent } from './cuenta-bancaria-dialogo-editar/cuenta-bancaria-dialogo-editar.component';
import { MatTableDataSource } from '@angular/material/table';
import { MatSnackBar } from '@angular/material/snack-bar';
import { switchMap } from 'rxjs/operators';
import { Personal } from './../../../_model/personal';
import { Segmento } from './../../../_model/segmento';
import { Proveedor } from './../../../_model/proveedor';
import { CuentaBancariaTipo } from './../../../_model/cuentaBancariaTipo';
import { Moneda } from './../../../_model/moneda';
import { Banco } from './../../../_model/banco';
import { CuentaBancaria } from './../../../_model/cuentaBancaria';
import { MonedaService } from './../../../_service/moneda.service';
import { CuentaBancariaTipoService } from './../../../_service/cuenta-bancaria-tipo.service';
import { BancoService } from './../../../_service/banco.service';
import { CuentaBancariaService } from './../../../_service/cuenta-bancaria.service';
import { SegmentoService } from './../../../_service/segmento.service';
import { PersonalService } from './../../../_service/personal.service';
import { ProveedorService } from './../../../_service/proveedor.service';
import { CategoriaService } from './../../../_service/categoria.service';
import { Categoria } from './../../../_model/categoria';
import { Observable } from 'rxjs';
import { FormGroup, Validators, FormControl } from '@angular/forms';
import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-cuenta-bancaria',
  templateUrl: './cuenta-bancaria.component.html',
  styleUrls: ['./cuenta-bancaria.component.css']
})
export class CuentaBancariaComponent implements OnInit {

  formCuentaBancaria : FormGroup;
  formBusqueda : FormGroup;

  categorias$ : Observable<Categoria[]>;
  idCategoriaC : number = 0;
  idCategoriaB : number = 0;

  bancos$ : Observable<Banco[]>;
  idBancoC : number = 0;
  idBancoB : number = 0;

  monedas$ : Observable<Moneda[]>;
  idMonedaC : number = 0;
  idMonedaB : number = 0;

  cuentaBancariaTipos$ : Observable<CuentaBancariaTipo[]>;
  idCuentaBancariaTipoC : number = 0;
  idCuentaBancariaTipoB : number = 0;

  proveedores$ : Observable<Proveedor[]>;
  idProveedor : number = 0;

  segmentos$ : Observable<Segmento[]>;
  idSegmento : number = 0;

  personal$ : Observable<Personal[]>;
  idPersonal : number = 0;
  
  entidades$ : Observable<any[]>;
  entidadSeleccionada : any;

  idBusqueda : number = 0;

  myControlCategoria : FormControl = new FormControl('', Validators.required);
  myControlBanco : FormControl = new FormControl('', Validators.required);
  myControlProveedor : FormControl = new FormControl('', Validators.required);
  myControlSegmento : FormControl = new FormControl('', Validators.required);
  myControlPersonal : FormControl = new FormControl('', Validators.required);
  myControlNombre : FormControl = new FormControl('', Validators.required);
  myControlCuentaBancariaTipo : FormControl = new FormControl('', Validators.required);
  myControlMoneda : FormControl = new FormControl('', Validators.required); 

  dataSource : MatTableDataSource<CuentaBancaria>;
  displayedColumns = ['banco', 'tipoCuenta', 'noCuenta', 'nombre', 'moneda', 'acciones'];

  dataSourceBancos : MatTableDataSource<Banco>;
  displayedColumnsBancos = ['nombre', 'acciones'];

  dataSourceCuentaBancariaTipos : MatTableDataSource<CuentaBancariaTipo>;
  displayedColumnsCuentaBancariaTipos = ['nombre', 'acciones'];

  dataSourceMonedas : MatTableDataSource<Moneda>;
  displayedColumnsMonedas = ['simbolo', 'nombre', 'acciones'];
  
  formBanco : FormGroup;
  formCuentaBancariaTipo : FormGroup;
  formMoneda : FormGroup;

  constructor(
    private categoriaService : CategoriaService,
    private proveedorService : ProveedorService,
    private personalService : PersonalService,
    private segmentoService : SegmentoService,
    private cuentaBancariaService : CuentaBancariaService,
    private bancoService : BancoService,
    private cuentaBancariaTipoService : CuentaBancariaTipoService,
    private monedaService : MonedaService,
    private snackBar : MatSnackBar,
    private dialog : MatDialog
  ) { }

  ngOnInit(): void {
    this.initFormCuentaBancaria();

    this.initFormBusqueda();

    this.initFormBanco();

    this.initFormCuentaBancariaTipo();

    this.initFormMoneda();

    this.categorias$ = this.categoriaService.getAll();
    this.segmentos$ = this.segmentoService.getAll();
    this.proveedores$ = this.proveedorService.getAll();
    this.bancos$ = this.bancoService.getAll();
    this.cuentaBancariaTipos$ = this.cuentaBancariaTipoService.getAll();
    this.monedas$ = this.monedaService.getAll();
    this.personal$ = this.personalService.getAll();

    /*this.bancos$ = this.bancoService.getObjetoCambio();
    this.cuentaBancariaTipos$ = this.cuentaBancariaTipoService.getObjetoCambio();
    this.monedas$ = this.monedaService.getObjetoCambio();*/

    this.bancoService.getAll().subscribe(data => {
      this.dataSourceBancos = new MatTableDataSource(data);
    });

    this.bancoService.getObjetoCambio().subscribe(data => {
      this.dataSourceBancos = new MatTableDataSource(data);
    });

    this.cuentaBancariaTipoService.getAll().subscribe(data => {
      this.dataSourceCuentaBancariaTipos = new MatTableDataSource(data);
    });

    this.cuentaBancariaTipoService.getObjetoCambio().subscribe(data => {
      this.dataSourceCuentaBancariaTipos = new MatTableDataSource(data);
    });

    this.monedaService.getAll().subscribe(data => {
      this.dataSourceMonedas = new MatTableDataSource(data);
    });

    this.monedaService.getObjetoCambio().subscribe(data => {
      this.dataSourceMonedas = new MatTableDataSource(data);
    });

    this.cuentaBancariaService.getAll().subscribe(data => {
      this.dataSource = new MatTableDataSource(data);
    });

    this.cuentaBancariaService.getMensajeCambio().subscribe(data => {
      this.snackBar.open(data, 'AVISO', {duration : 2000});
    });

    this.cuentaBancariaService.getObjetoCambio().subscribe(data => {
      this.dataSource = new MatTableDataSource(data);
    });

    this.cuentaBancariaTipoService.getMensajeCambio().subscribe(data => {
      this.snackBar.open(data, 'AVISO', {duration : 2000});
    });

    this.monedaService.getMensajeCambio().subscribe(data => {
      this.snackBar.open(data, 'AVISO', {duration : 2000});
    });

    this.bancoService.getMensajeCambio().subscribe(data => {
      this.snackBar.open(data, 'AVISO', {duration : 2000});
    });
  }

  initFormBanco() {
    this.formBanco = new FormGroup({
      'nombre' : new FormControl('', Validators.required)
    });
  }

  initFormCuentaBancariaTipo() {
    this.formCuentaBancariaTipo = new FormGroup({
      'nombre' : new FormControl('', Validators.required)
    });
  }

  initFormMoneda() {
    this.formMoneda = new FormGroup({
      'nombre' : new FormControl('', Validators.required),
      'simbolo' : new FormControl('', Validators.required)
    });
  }

  guardarCuentaBancaria() {
    let cuentaBancaria = new CuentaBancaria();
    cuentaBancaria.categoria = new Categoria();
    cuentaBancaria.categoria.idCategoria = this.idCategoriaC;
    if(this.idCategoriaC==1) {
      cuentaBancaria.idItem = this.entidadSeleccionada.idProveedor;
    } else if(this.idCategoriaC==2){
      cuentaBancaria.idItem = this.entidadSeleccionada.idSegmento;
    } else if(this.idCategoriaC==3){
      cuentaBancaria.idItem = this.entidadSeleccionada.idPersonal;
    } else if(this.idCategoriaC==4){
      cuentaBancaria.idItem = 0;
    }
    cuentaBancaria.nombre = this.formCuentaBancaria.value['nombreCuenta'];
    cuentaBancaria.numero = this.formCuentaBancaria.value['noCuenta'];
    cuentaBancaria.banco = new Banco();
    cuentaBancaria.banco.idBanco = this.idBancoC;
    cuentaBancaria.cuentaBancariaTipo = new CuentaBancariaTipo();
    cuentaBancaria.cuentaBancariaTipo.idCuentaBancariaTipo = this.idCuentaBancariaTipoC;
    cuentaBancaria.moneda = new Moneda();
    cuentaBancaria.moneda.idMoneda = this.idMonedaC;

    this.cuentaBancariaService.create(cuentaBancaria).pipe(switchMap(() => {
      return this.cuentaBancariaService.getAll();
    })).subscribe(data => {
      this.cuentaBancariaService.setObjetoCambio(data);
      this.cuentaBancariaService.setMensajeCambio('Cuenta bancaria creada');
      this.limpiarFormCuentaBancaria();
    });
  }

  initFormCuentaBancaria() {
    this.formCuentaBancaria = new FormGroup({
      'categoria' : new FormControl('', Validators.required),
      'entidad' : new FormControl('', Validators.required),
      'noCuenta' : new FormControl('', Validators.required),
      'nombreCuenta' : new FormControl('', Validators.required),
      'banco' : new FormControl('', Validators.required),
      'cuentaBancariaTipo' : new FormControl('', Validators.required),
      'moneda' : new FormControl('', Validators.required)
    });

    this.formCuentaBancaria.get('entidad').disable();
  }

  initFormBusqueda() {
    this.formBusqueda = new FormGroup({
      'categoria' : this.myControlCategoria,
      'banco' : this.myControlBanco,
      'proveedor' : this.myControlProveedor,
      'segmento' : this.myControlSegmento,
      'personal' : this.myControlPersonal,
      'nombre' : this.myControlNombre,
      'cuentaBancariaTipo' : this.myControlCuentaBancariaTipo,
      'moneda' : this.myControlMoneda
    });

    this.formBusqueda.get('categoria').disable();
    this.formBusqueda.get('banco').disable();
    this.formBusqueda.get('proveedor').disable();
    this.formBusqueda.get('segmento').disable();
    this.formBusqueda.get('personal').disable();
    this.formBusqueda.get('nombre').disable();
    this.formBusqueda.get('cuentaBancariaTipo').disable();
    this.formBusqueda.get('moneda').disable();
  }

  cargarEntidades() {
    this.entidades$ = new Observable();
    this.formCuentaBancaria.get('entidad').enable();
    if(this.idCategoriaC==1) {
      this.entidades$ = this.proveedores$;
    } else if(this.idCategoriaC==2) {
      this.entidades$ = this.segmentos$;
    } else if(this.idCategoriaC==3) {
      this.entidades$ = this.personal$;
    }
    else if(this.idCategoriaC==4) {
      this.formCuentaBancaria.get('entidad').disable();
    }
  }

  limpiarFormCuentaBancaria() {
    this.formCuentaBancaria.reset();
    this.entidades$ = new Observable();
    this.formCuentaBancaria.get('entidad').disable();
    this.entidadSeleccionada = null;
    this.idCategoriaC = 0;
    this.idBancoC = 0;
    this.idMonedaC = 0;
    this.idCuentaBancariaTipoC = 0;
  }

  setInputs() {
    if(this.idBusqueda == 1) {
      this.setCategoriaInput(true);
      this.setProveedorInput(false);
      this.setSegmentoInput(false);
      this.setPersonalInput(false);
      this.setNombreInput(false);
      this.setBancoInput(false);
      this.setCuentaBancariaTipoInput(false);
      this.setMonedaInput(false);
    } else if(this.idBusqueda == 2) {
      this.setCategoriaInput(false);
      this.setProveedorInput(true);
      this.setSegmentoInput(false);
      this.setPersonalInput(false);
      this.setNombreInput(false);
      this.setBancoInput(false);
      this.setCuentaBancariaTipoInput(false);
      this.setMonedaInput(false);
    } else if(this.idBusqueda == 3) {
      this.setCategoriaInput(false);
      this.setProveedorInput(false);
      this.setSegmentoInput(true);
      this.setPersonalInput(false);
      this.setNombreInput(false);
      this.setBancoInput(false);
      this.setCuentaBancariaTipoInput(false);
      this.setMonedaInput(false);
    } else if(this.idBusqueda == 4) {
      this.setCategoriaInput(false);
      this.setProveedorInput(false);
      this.setSegmentoInput(false);
      this.setPersonalInput(true);
      this.setNombreInput(false);
      this.setBancoInput(false);
      this.setCuentaBancariaTipoInput(false);
      this.setMonedaInput(false);
    } else if(this.idBusqueda == 5) {
      this.setCategoriaInput(false);
      this.setProveedorInput(false);
      this.setSegmentoInput(false);
      this.setPersonalInput(false);
      this.setNombreInput(true);
      this.setBancoInput(false);
      this.setCuentaBancariaTipoInput(false);
      this.setMonedaInput(false);
    } else if(this.idBusqueda == 6) {
      this.setCategoriaInput(false);
      this.setProveedorInput(false);
      this.setSegmentoInput(false);
      this.setPersonalInput(false);
      this.setNombreInput(false);
      this.setBancoInput(true);
      this.setCuentaBancariaTipoInput(false);
      this.setMonedaInput(false);
    } else if(this.idBusqueda == 7) {
      this.setCategoriaInput(false);
      this.setProveedorInput(false);
      this.setSegmentoInput(false);
      this.setPersonalInput(false);
      this.setNombreInput(false);
      this.setBancoInput(false);
      this.setCuentaBancariaTipoInput(true);
      this.setMonedaInput(false);
    } else if(this.idBusqueda == 8) {
      this.setCategoriaInput(false);
      this.setProveedorInput(false);
      this.setSegmentoInput(false);
      this.setPersonalInput(false);
      this.setNombreInput(false);
      this.setBancoInput(false);
      this.setCuentaBancariaTipoInput(false);
      this.setMonedaInput(true);
    }
  }

  setCategoriaInput(e : boolean) {
    this.dataSource = new MatTableDataSource();
    this.myControlCategoria.reset();
    if(e) {
      this.formBusqueda.get('categoria').enable();
    } else {
      this.formBusqueda.get('categoria').disable();
    }
  }

  setProveedorInput(e : boolean) {
    this.dataSource = new MatTableDataSource();
    this.myControlProveedor.reset();
    if(e) {
      this.formBusqueda.get('proveedor').enable();
    } else {
      this.formBusqueda.get('proveedor').disable();
    }
  }

  setSegmentoInput(e : boolean) {
    this.dataSource = new MatTableDataSource();
    this.myControlSegmento.reset();
    if(e) {
      this.formBusqueda.get('segmento').enable();
    } else {
      this.formBusqueda.get('segmento').disable();
    }
  }

  setPersonalInput(e : boolean) {
    this.dataSource = new MatTableDataSource();
    this.myControlPersonal.reset();
    if(e) {
      this.formBusqueda.get('personal').enable();
    } else {
      this.formBusqueda.get('personal').disable();
    }
  }

  setNombreInput(e : boolean) {
    this.dataSource = new MatTableDataSource();
    this.myControlNombre.reset();
    if(e) {
      this.formBusqueda.get('nombre').enable();
    } else {
      this.formBusqueda.get('nombre').disable();
    }
  }

  setBancoInput(e : boolean) {
    this.dataSource = new MatTableDataSource();
    this.myControlBanco.reset();
    if(e) {
      this.formBusqueda.get('banco').enable();
    } else {
      this.formBusqueda.get('banco').disable();
    }
  }

  setCuentaBancariaTipoInput(e : boolean) {
    this.dataSource = new MatTableDataSource();
    this.myControlCuentaBancariaTipo.reset();
    if(e) {
      this.formBusqueda.get('cuentaBancariaTipo').enable();
    } else {
      this.formBusqueda.get('cuentaBancariaTipo').disable();
    }
  }

  setMonedaInput(e : boolean) {
    this.dataSource = new MatTableDataSource();
    this.myControlMoneda.reset();
    if(e) {
      this.formBusqueda.get('moneda').enable();
    } else {
      this.formBusqueda.get('moneda').disable();
    }
  }

  buscarCuentaBancaria() {
    this.dataSource = new MatTableDataSource();
    if(this.idBusqueda == 0) {
      this.cuentaBancariaService.getAll().subscribe(data => {
        this.dataSource = new MatTableDataSource(data);
      });
    } else if(this.idBusqueda == 1) {
      this.cuentaBancariaService.getByCategoria(this.idCategoriaB).subscribe(data => {
        this.dataSource = new MatTableDataSource(data);
      });
    } else if(this.idBusqueda == 2) {
      this.cuentaBancariaService.getByCategoriaAndItem(1, this.idProveedor).subscribe(data => {
        this.dataSource = new MatTableDataSource(data);
      });
    } else if(this.idBusqueda == 3) {
      this.cuentaBancariaService.getByCategoriaAndItem(2, this.idSegmento).subscribe(data => {
        this.dataSource = new MatTableDataSource(data);
      });
    } else if(this.idBusqueda == 4) {
      this.cuentaBancariaService.getByCategoriaAndItem(3, this.idPersonal).subscribe(data => {
        this.dataSource = new MatTableDataSource(data);
      });
    } else if(this.idBusqueda == 5) {
      this.cuentaBancariaService.getByNombreLike(this.formBusqueda.value['nombre']).subscribe(data => {
        this.dataSource = new MatTableDataSource(data);
      });
    } else if(this.idBusqueda == 6) {
      this.cuentaBancariaService.getByBanco(this.idBancoB).subscribe(data => {
        this.dataSource = new MatTableDataSource(data);
      });
    } else if(this.idBusqueda == 7) {
      this.cuentaBancariaService.getByCuentaBancariaTipo(this.idCuentaBancariaTipoB).subscribe(data => {
        this.dataSource = new MatTableDataSource(data);
      });
    } else if(this.idBusqueda == 8) {
      this.cuentaBancariaService.getByMoneda(this.idMonedaB).subscribe(data => {
        this.dataSource = new MatTableDataSource(data);
      });
    }
  }

  abrirDialogoEditar(cuentaBancaria : CuentaBancaria) {
    this.dialog.open(CuentaBancariaDialogoEditarComponent, {
      width: '400px',
      data: cuentaBancaria
    });
  }

  abrirDialogoEliminar(cuentaBancaria : CuentaBancaria) {
    let dialogRef = this.dialog.open(CuentaBancariaDialogoEliminarComponent, {
      width: '400px',
      data: cuentaBancaria
    });

    dialogRef.afterClosed().subscribe(() => {
      this.buscarCuentaBancaria();
    });
  }

  abrirDialogoBancoEditar(banco : Banco) {
    this.dialog.open(BancoDialogoEditarComponent, {
      width: '250px',
      data: banco
    });
  }

  abrirDialogoBancoEliminar(banco : Banco) {
    this.dialog.open(BancoDialogoEliminarComponent, {
      width: '350px',
      data: banco
    });
  }
  
  abrirDialogoCuentaBancariaTipoEditar(cuentaBancariaTipo : CuentaBancariaTipo) {
    this.dialog.open(CuentaBancariaTipoDialogoEditarComponent, {
      width: '250px',
      data: cuentaBancariaTipo
    });
  }

  abrirDialogoCuentaBancariaTipoEliminar(cuentaBancariaTipo : CuentaBancariaTipo) {
    this.dialog.open(CuentaBancariaTipoDialogoEliminarComponent, {
      width: '350px',
      data: cuentaBancariaTipo
    });
  }

  abrirDialogoMonedaEditar(moneda : Moneda) {
    this.dialog.open(MonedaDialogoEditarComponent, {
      width: '250px',
      data: moneda
    });
  }

  abrirDialogoMonedaEliminar(moneda : Moneda) {
    this.dialog.open(MonedaDialogoEliminarComponent, {
      width: '350px',
      data: moneda
    });
  }

  guardarBanco() {
    let banco = new Banco();
    banco.nombre = this.formBanco.value['nombre'];
    this.bancoService.create(banco).pipe(switchMap(() => {
      return this.bancoService.getAll();
    })).subscribe(data => {
      this.bancoService.setObjetoCambio(data);
      this.bancoService.setMensajeCambio('Banco creado');
      this.formBanco.reset();
    });
  }

  guardarCuentaBancariaTipo() {
    let cuentaBancariaTipo = new CuentaBancariaTipo();
    cuentaBancariaTipo.nombre = this.formCuentaBancariaTipo.value['nombre'];
    this.cuentaBancariaTipoService.create(cuentaBancariaTipo).pipe(switchMap(() => {
      return this.cuentaBancariaTipoService.getAll();
    })).subscribe(data => {
      this.cuentaBancariaTipoService.setObjetoCambio(data);
      this.cuentaBancariaTipoService.setMensajeCambio('Tipo de cuenta creada');
      this.formCuentaBancariaTipo.reset();
    });
  }

  guardarMoneda() {
    let moneda = new Moneda();
    moneda.nombre = this.formMoneda.value['nombre'];
    moneda.simbolo = this.formMoneda.value['simbolo'];
    this.monedaService.create(moneda).pipe(switchMap(() => {
      return this.monedaService.getAll();
    })).subscribe(data => {
      this.monedaService.setObjetoCambio(data);
      this.monedaService.setMensajeCambio('Moneda creada');
      this.formMoneda.reset();
    });
  }

} 
