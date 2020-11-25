import { switchMap } from 'rxjs/operators';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { SegmentoPagoService } from './../../../../_service/segmento-pago.service';
import { SegmentoPagoDTO } from './../../../../_model/dto/segmentoPagoDTO';
import { Component, OnInit, Inject } from '@angular/core';

@Component({
  selector: 'app-segmento-pago-dialogo-eliminar',
  templateUrl: './segmento-pago-dialogo-eliminar.component.html',
  styleUrls: ['./segmento-pago-dialogo-eliminar.component.css']
})
export class SegmentoPagoDialogoEliminarComponent implements OnInit {

  segmentoPagoDTO : SegmentoPagoDTO;

  constructor(
    private dialogRef : MatDialogRef<SegmentoPagoDialogoEliminarComponent>,
    @Inject(MAT_DIALOG_DATA) private data : SegmentoPagoDTO,
    private segmentoPagoService : SegmentoPagoService
  ) { }

  ngOnInit(): void {
    this.segmentoPagoDTO = new SegmentoPagoDTO();
    this.segmentoPagoDTO.idSegmentoPago = this.data.idSegmentoPago;
    this.segmentoPagoDTO.fechaPago = this.data.fechaPago;
    this.segmentoPagoDTO.monto = this.data.monto;
  }

  eliminar() {
    this.segmentoPagoService.delete(this.segmentoPagoDTO.idSegmentoPago).pipe(switchMap(() => {
      return this.segmentoPagoService.getAll();
    })).subscribe(data => {
      this.segmentoPagoService.setObjetoCambio(data);
      this.segmentoPagoService.setMensajeCambio('El pago del segmento se ha eliminado');
    });

    this.cerrar();
  }

  cerrar() {
    this.dialogRef.close();
  }

}
