import { switchMap } from 'rxjs/operators';
import { SegmentoPago } from './../../../../_model/segmentoPago';
import { SegmentoPagoService } from './../../../../_service/segmento-pago.service';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { SegmentoCreditoDetalleDTO } from './../../../../_model/dto/segmentoCreditoDetalleDTO';
import { FormGroup, FormControl, Validators } from '@angular/forms';
import { Component, OnInit, Inject } from '@angular/core';
import { SegmentoCreditoDetalle } from 'src/app/_model/segmentoCreditoDetalle';

@Component({
  selector: 'app-segmento-pago-dialogo',
  templateUrl: './segmento-pago-dialogo.component.html',
  styleUrls: ['./segmento-pago-dialogo.component.css']
})
export class SegmentoPagoDialogoComponent implements OnInit {

  form: FormGroup;
  segmentoPago : SegmentoPago;

  constructor(
    private dialogRef : MatDialogRef<SegmentoPagoDialogoComponent>,
    @Inject(MAT_DIALOG_DATA) private data : SegmentoCreditoDetalleDTO,
    private segmentoPagoService : SegmentoPagoService
  ) { }

  ngOnInit(): void {
    this.segmentoPago = new SegmentoPago();
    this.segmentoPago.segmentoCreditoDetalle = new SegmentoCreditoDetalle();
    this.segmentoPago.segmentoCreditoDetalle.idSegmentoCreditoDetalle = this.data.idSegmentoCreditoDetalle;
    
    this.initForm();
  }

  initForm() {
    this.form = new FormGroup({
      'monto' : new FormControl('', Validators.required)
    })
  }

  f() {
    return this.form.controls;
  }

  operar() {
    if(this.form.invalid) {return; }

    this.segmentoPago.monto = this.form.value['monto'];

    this.segmentoPagoService.create(this.segmentoPago).pipe(switchMap(() => {
      return this.segmentoPagoService.getAll();
    })).subscribe(data => {
      this.segmentoPagoService.setObjetoCambio(data);
      this.segmentoPagoService.setMensajeCambio('Pago registrado');
    })

    this.cerrar();
  }

  cerrar() {
    this.dialogRef.close();
  }
}
