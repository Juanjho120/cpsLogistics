import { switchMap } from 'rxjs/operators';
import { RepuestoService } from './../../../_service/repuesto.service';
import { MAT_DIALOG_DATA, MatDialogRef } from '@angular/material/dialog';
import { Repuesto } from './../../../_model/repuesto';
import { Component, Inject, OnInit } from '@angular/core';

@Component({
  selector: 'app-cardex-dialogo-eliminar',
  templateUrl: './cardex-dialogo-eliminar.component.html',
  styleUrls: ['./cardex-dialogo-eliminar.component.css']
})
export class CardexDialogoEliminarComponent implements OnInit {

  repuesto : Repuesto;

  constructor(
    private dialogRef : MatDialogRef<CardexDialogoEliminarComponent>,
    @Inject(MAT_DIALOG_DATA) private data : Repuesto,
    private repuestoService : RepuestoService
  ) { }

  ngOnInit(): void {
    this.repuesto = new Repuesto();
    this.repuesto.idRepuesto = this.data.idRepuesto;
    this.repuesto.descripcion = this.data.descripcion;
  }

  eliminar() {
    this.repuestoService.delete(this.repuesto.idRepuesto).pipe(switchMap(() =>{
      return this.repuestoService.getAll();
    })).subscribe(data => {
      this.repuestoService.setObjetoCambio(data);
      this.repuestoService.setMensajeCambio('Repuesto eliminado');
    });
    this.cerrar();
  }

  cerrar() {
    this.dialogRef.close();
  }

}
