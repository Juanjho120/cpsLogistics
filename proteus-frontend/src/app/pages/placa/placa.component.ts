import { MatDialog } from '@angular/material/dialog';
import { PlacaDialogoEliminarComponent } from './placa-dialogo-eliminar/placa-dialogo-eliminar.component';
import { switchMap } from 'rxjs/operators';
import { MarcaAuto } from 'src/app/_model/marcaAuto';
import { Observable } from 'rxjs';
import { FormGroup, FormControl, Validators } from '@angular/forms';
import { MarcaAutoService } from './../../_service/marca-auto.service';
import { MatSnackBar } from '@angular/material/snack-bar';
import { PlacaService } from './../../_service/placa.service';
import { MatPaginator } from '@angular/material/paginator';
import { MatSort } from '@angular/material/sort';
import { MatTableDataSource } from '@angular/material/table';
import { Placa } from './../../_model/placa';
import { Component, OnInit, ViewChild } from '@angular/core';
import * as moment from 'moment';

@Component({
  selector: 'app-placa',
  templateUrl: './placa.component.html',
  styleUrls: ['./placa.component.css']
})
export class PlacaComponent implements OnInit {

  displayedColumns = ['numero', 'marcaAuto', 'fechaUltimoServicio', 'ultimoKilometraje', 'acciones'];
  dataSource : MatTableDataSource<Placa>;
  @ViewChild(MatSort) sort : MatSort;
  @ViewChild(MatPaginator) paginator : MatPaginator;

  form : FormGroup;
  marcaAutos$ : Observable<MarcaAuto[]>;
  idMarcaAuto : number;

  maxFecha: Date = new Date();
  fechaUltimoServicioSeleccionado: Date = new Date();
  fechaUltimoServicioFormato : string;
  formatoFecha : string = 'YYYY-MM-DD';

  formPlacaDisable = true;

  constructor(
    private placaService : PlacaService,
    private marcaAutoService : MarcaAutoService,
    private snackBar : MatSnackBar,
    private dialog : MatDialog
  ) { }

  ngOnInit(): void {
    this.placaService.getObjetoCambio().subscribe(data => {
      this.dataSource = new MatTableDataSource(data);
      this.dataSource.sort = this.sort;
      this.dataSource.paginator = this.paginator;
    });

    this.placaService.getMensajeCambio().subscribe(data => {
      this.snackBar.open(data, 'AVISO', {duration : 2000});
    });

    this.placaService.getAll().subscribe(data => {
      this.dataSource = new MatTableDataSource(data);
      this.dataSource.sort = this.sort;
      this.dataSource.paginator = this.paginator;
    });

    this.initForm();

    this.disableForm();

    this.marcaAutos$ = this.marcaAutoService.getAll();
  }

  initForm() {
    this.fechaUltimoServicioFormato = "";
    this.idMarcaAuto = 0;
    this.form = new FormGroup({
      'id' : new FormControl(''),
      'marcaAuto' : new FormControl('', Validators.required),
      'numero' : new FormControl('', Validators.required),
      'fechaUltimoServicio' : new FormControl('', Validators.required),
      'ultimoKilometraje' : new FormControl('0', Validators.required)
    });
  }

  filter(value : string) {
    this.dataSource.filter = value.trim().toLowerCase();
  }

  cambiarFechaUltimoServicio(e : any) {
    this.fechaUltimoServicioSeleccionado = e.value;
    this.fechaUltimoServicioFormato = moment(this.fechaUltimoServicioSeleccionado).format(this.formatoFecha);
  }

  f() {
    return this.form.controls;
  }

  formularioEdicion(placa : Placa) {
    this.enableForm();
    this.form = new FormGroup({
      'id' : new FormControl(placa.idPlaca),
      'marcaAuto' : new FormControl(placa.marcaAuto.idMarcaAuto, Validators.required),
      'numero' : new FormControl(placa.numero, Validators.required),
      'fechaUltimoServicio' : new FormControl(placa.fechaUltimoServicio, Validators.required),
      'ultimoKilometraje' : new FormControl(placa.ultimoKilometraje, Validators.required)
    });

    this.idMarcaAuto = placa.marcaAuto.idMarcaAuto;
    this.fechaUltimoServicioFormato = placa.fechaUltimoServicio;
  }

  disableForm() {
    this.initForm();
    document.getElementById("mat-card-formulario").style.visibility = "hidden";
  }

  enableForm() {
    document.getElementById("mat-card-formulario").style.visibility = "visible";
  }

  operar() {
    let placa : Placa = new Placa();
    placa.idPlaca = this.form.value['id'];
    placa.marcaAuto = new MarcaAuto();
    placa.marcaAuto.idMarcaAuto = this.form.value['marcaAuto'];
    placa.numero = this.form.value['numero'];
    placa.ultimoKilometraje = this.form.value['ultimoKilometraje'];
    placa.fechaUltimoServicio = this.fechaUltimoServicioFormato;

    if(placa.idPlaca != null && placa.idPlaca > 0) {
      this.placaService.update(placa).pipe(switchMap(() => {
        return this.placaService.getAll();
      })).subscribe(data => {
        this.placaService.setObjetoCambio(data);
        this.placaService.setMensajeCambio('Placa actualizada');
      });
    } else {
      this.placaService.create(placa).pipe(switchMap(() => {
        return this.placaService.getAll();
      })).subscribe(data => {
        this.placaService.setObjetoCambio(data);
        this.placaService.setMensajeCambio('Placa registrada');
      });
    }

    this.initForm();

    this.disableForm();
  }

  abrirDialogoEliminar(placa : Placa) {
    this.dialog.open(PlacaDialogoEliminarComponent, {
      width: '300px',
      data: placa
    })
  }

}
