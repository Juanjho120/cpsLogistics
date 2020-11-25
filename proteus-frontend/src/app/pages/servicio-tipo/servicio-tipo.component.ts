import { ServicioTipoDialogoEliminarComponent } from './servicio-tipo-dialogo-eliminar/servicio-tipo-dialogo-eliminar.component';
import { ServicioTipoDialogoComponent } from './servicio-tipo-dialogo/servicio-tipo-dialogo.component';
import { switchMap } from 'rxjs/operators';
import { MatSnackBar } from '@angular/material/snack-bar';
import { MatDialog } from '@angular/material/dialog';
import { FormGroup, FormControl, Validators } from '@angular/forms';
import { ServicioTipoService } from './../../_service/servicio-tipo.service';
import { MatSort } from '@angular/material/sort';
import { MatTableDataSource } from '@angular/material/table';
import { Component, OnInit, ViewChild } from '@angular/core';
import { ServicioTipo } from 'src/app/_model/servicioTipo';

@Component({
  selector: 'app-servicio-tipo',
  templateUrl: './servicio-tipo.component.html',
  styleUrls: ['./servicio-tipo.component.css']
})
export class ServicioTipoComponent implements OnInit {

  displayedColumns = ['nombre', 'acciones'];
  dataSource: MatTableDataSource<ServicioTipo>;
  @ViewChild(MatSort) sort : MatSort;

  servicioTipoNombre : string;
  form: FormGroup;

  constructor(
    private servicioTipoService : ServicioTipoService,
    private dialog : MatDialog,
    private snackBar : MatSnackBar
  ) { }

  ngOnInit(): void {
    this.form = new FormGroup({
      'id' : new FormControl(0),
      'nombre' : new FormControl('', [Validators.minLength(3)])
    });

    this.servicioTipoService.getObjetoCambio().subscribe(data => {
      this.dataSource = new MatTableDataSource(data);
      this.dataSource.sort = this.sort;
    });

    this.servicioTipoService.getMensajeCambio().subscribe(data => {
      this.snackBar.open(data, 'AVISO', {duration : 2000});
    });

    this.servicioTipoService.getAll().subscribe(data => {
      this.dataSource = new MatTableDataSource(data);
      this.dataSource.sort = this.sort;
    });
  }

  f() {
    return this.form.controls;
  }

  guardar() {
    let servicioTipo = new ServicioTipo();
    servicioTipo.nombre = this.form.value['nombre'];
    this.servicioTipoService.create(servicioTipo).pipe(switchMap(() => {
      return this.servicioTipoService.getAll();
    })).subscribe(data => {
      this.servicioTipoService.setObjetoCambio(data);
      this.servicioTipoService.setMensajeCambio('Tipo de serviio creado');
    });
  }

  abrirDialogoEdicion(servicioTipo : ServicioTipo) {
    this.dialog.open(ServicioTipoDialogoComponent, {
      width: '250px',
      data: servicioTipo
    })
  }

  abrirDialogoEliminar(servicioTipo : ServicioTipo) {
    this.dialog.open(ServicioTipoDialogoEliminarComponent, {
      width: '300px',
      data: servicioTipo
    })
  }
}
