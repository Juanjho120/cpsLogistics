import { switchMap } from 'rxjs/operators';
import { CotizacionService } from './../../../_service/cotizacion.service';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { Cotizacion } from './../../../_model/cotizacion';
import { Component, OnInit, Inject } from '@angular/core';

@Component({
  selector: 'app-cotizacion-dialogo-eliminar',
  templateUrl: './cotizacion-dialogo-eliminar.component.html',
  styleUrls: ['./cotizacion-dialogo-eliminar.component.css']
})
export class CotizacionDialogoEliminarComponent implements OnInit {

  cotizacion : Cotizacion;
  
  constructor(
    private dialogRef : MatDialogRef<CotizacionDialogoEliminarComponent>,
    @Inject(MAT_DIALOG_DATA) private data : Cotizacion,
    private cotizacionService : CotizacionService
  )
   { }

  ngOnInit(): void {
    this.cotizacion = new Cotizacion();
    this.cotizacion.idCotizacion = this.data.idCotizacion;
    this.cotizacion.segmento = this.data.segmento;
  }

  eliminar() {
    this.cotizacionService.delete(this.cotizacion.idCotizacion).pipe(switchMap(() =>{
      return this.cotizacionService.getAll();
    })).subscribe(data => {
      this.cotizacionService.setObjetoCambio(data);
      this.cotizacionService.setMensajeCambio('Cotizacion eliminada');
    });
    this.cerrar(true);
  }

  cerrar(opcion : boolean) {
    this.dialogRef.close(opcion);
  }

}
