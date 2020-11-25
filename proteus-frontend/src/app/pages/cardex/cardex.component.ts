import { CardexDialogoEliminarComponent } from './cardex-dialogo-eliminar/cardex-dialogo-eliminar.component';
import { CardexDialogoComponent } from './cardex-dialogo/cardex-dialogo.component';
import { MatSnackBar } from '@angular/material/snack-bar';
import { MatDialog } from '@angular/material/dialog';
import { RepuestoService } from './../../_service/repuesto.service';
import { MatSort } from '@angular/material/sort';
import { Repuesto } from './../../_model/repuesto';
import { MatTableDataSource } from '@angular/material/table';
import { Component, OnInit, ViewChild } from '@angular/core';
import { MatPaginator } from '@angular/material/paginator';

@Component({
  selector: 'app-cardex',
  templateUrl: './cardex.component.html',
  styleUrls: ['./cardex.component.css']
})
export class CardexComponent implements OnInit {

  displayedColumns = ['codigo', 'codigoBarra', 'descripcion', 'existencia', 'acciones'];
  dataSource : MatTableDataSource<Repuesto>;
  @ViewChild(MatSort) sort : MatSort;
  @ViewChild(MatPaginator) paginator : MatPaginator;

  constructor(
    private repuestoService : RepuestoService,
    private dialog : MatDialog,
    private snackBar : MatSnackBar
  ) { }

  ngOnInit(): void {
    this.repuestoService.getObjetoCambio().subscribe(data => {
      this.dataSource = new MatTableDataSource(data);
      this.dataSource.sort = this.sort;
      this.dataSource.paginator = this.paginator;
    });

    this.repuestoService.getMensajeCambio().subscribe(data => {
      this.snackBar.open(data, 'AVISO', {duration : 2000});
    });

    this.repuestoService.getAll().subscribe(data => {
      this.dataSource = new MatTableDataSource(data);
      this.dataSource.sort = this.sort;
      this.dataSource.paginator = this.paginator;
    });
  }

  filter(value : string) {
    this.dataSource.filter = value.trim().toLowerCase();
  }

  abrirDialogoEdicion(repuesto? : Repuesto) {
    let repuestoAux = repuesto != null ? repuesto : new Repuesto();
    this.dialog.open(CardexDialogoComponent, {
      width : '450px',
      data : repuestoAux
    })
  }

  abrirDialogoEliminar(repuesto : Repuesto) {
    this.dialog.open(CardexDialogoEliminarComponent, {
      width: '300px',
      data: repuesto
    })
  }

}
