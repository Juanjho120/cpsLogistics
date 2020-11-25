import { switchMap } from 'rxjs/operators';
import { MarcaAutoService } from './../../../_service/marca-auto.service';
import { MarcaAuto } from './../../../_model/marcaAuto';
import { Component, Inject, OnInit } from '@angular/core';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';

@Component({
  selector: 'app-marca-auto-dialogo',
  templateUrl: './marca-auto-dialogo.component.html',
  styleUrls: ['./marca-auto-dialogo.component.css']
})
export class MarcaAutoDialogoComponent implements OnInit {

  marcaAuto : MarcaAuto;

  constructor(
    private dialogRef : MatDialogRef<MarcaAutoDialogoComponent>,
    @Inject(MAT_DIALOG_DATA) private data : MarcaAuto,
    private marcaAutoService : MarcaAutoService
  ) { }

  ngOnInit(): void {
    this.marcaAuto = new MarcaAuto();
    this.marcaAuto.idMarcaAuto = this.data.idMarcaAuto;
    this.marcaAuto.nombre = this.data.nombre;
  }

  editar() {
    if(this.marcaAuto != null && this.marcaAuto.idMarcaAuto > 0) {
      this.marcaAutoService.update(this.marcaAuto).pipe(switchMap(() => {
        return this.marcaAutoService.getAll();
      })).subscribe(data => {
        this.marcaAutoService.setObjetoCambio(data);
        this.marcaAutoService.setMensajeCambio('Marca de auto actualizado');
      });
    }
    this.cerrar();
  }

  cerrar() {
    this.dialogRef.close();
  }

}
