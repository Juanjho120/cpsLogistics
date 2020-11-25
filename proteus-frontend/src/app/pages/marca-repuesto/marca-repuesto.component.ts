import { MarcaRepuestoDialogoComponent } from './marca-repuesto-dialogo/marca-repuesto-dialogo.component';
import { switchMap } from 'rxjs/operators';
import { MatSnackBar } from '@angular/material/snack-bar';
import { MatDialog } from '@angular/material/dialog';
import { FormGroup, FormControl, Validators } from '@angular/forms';
import { MarcaRepuestoService } from './../../_service/marca-repuesto.service';
import { MatSort } from '@angular/material/sort';
import { MatTableDataSource } from '@angular/material/table';
import { Component, OnInit, ViewChild } from '@angular/core';
import { MarcaRepuesto } from 'src/app/_model/marcaRepuesto';
import { MarcaRepuestoDialogoEliminarComponent } from './../marca-repuesto/marca-repuesto-dialogo-eliminar/marca-repuesto-dialogo-eliminar.component';

@Component({
  selector: 'app-marca-repuesto',
  templateUrl: './marca-repuesto.component.html',
  styleUrls: ['./marca-repuesto.component.css']
})
export class MarcaRepuestoComponent implements OnInit {

  displayedColumns = ['nombre', 'acciones'];
  dataSource: MatTableDataSource<MarcaRepuesto>;
  @ViewChild(MatSort) sort: MatSort;

  marcaRepuestoNombre : string;
  form: FormGroup;

  constructor(
    private marcaRepuestoService : MarcaRepuestoService,
    private dialog : MatDialog,
    private snackBar : MatSnackBar
  ) { }

  ngOnInit(): void {
    this.form = new FormGroup({
      'id' : new FormControl(0),
      'nombre' : new FormControl('', [Validators.minLength(3)])
    });

    this.marcaRepuestoService.getObjetoCambio().subscribe(data => {
      this.dataSource = new MatTableDataSource(data);
      this.dataSource.sort = this.sort;
    });

    this.marcaRepuestoService.getMensajeCambio().subscribe(data => {
      this.snackBar.open(data, 'AVISO', {duration : 2000});
    });

    this.marcaRepuestoService.getAll().subscribe(data => {
      this.dataSource = new MatTableDataSource(data);
      this.dataSource.sort = this.sort;
    })
  }

  f() {
    return this.form.controls;
  }

  guardar() {
    let marcaRepuesto = new MarcaRepuesto();
    marcaRepuesto.nombre = this.form.value['nombre'];
    this.marcaRepuestoService.create(marcaRepuesto).pipe(switchMap(() => {
      return this.marcaRepuestoService.getAll();
    })).subscribe(data => {
      this.marcaRepuestoService.setObjetoCambio(data);
      this.marcaRepuestoService.setMensajeCambio('Marca de repuesto creado');
    });
  }

  abrirDialogoEdicion(marcaRepuesto : MarcaRepuesto) {
    this.dialog.open(MarcaRepuestoDialogoComponent, {
      width: '250px',
      data: marcaRepuesto
    })
  }

  abrirDialogoEliminar(marcaRepuesto : MarcaRepuesto) {
    this.dialog.open(MarcaRepuestoDialogoEliminarComponent, {
      width: '300px',
      data: marcaRepuesto
    })
  }

}
