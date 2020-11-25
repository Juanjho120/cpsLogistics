import { switchMap } from 'rxjs/operators';
import { SegmentoService } from './../../../../_service/segmento.service';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { FormGroup, FormControl, Validators } from '@angular/forms';
import { Segmento } from './../../../../_model/segmento';
import { Component, OnInit, Inject } from '@angular/core';

@Component({
  selector: 'app-segmento-ingreso-busqueda-dialogo',
  templateUrl: './segmento-ingreso-busqueda-dialogo.component.html',
  styleUrls: ['./segmento-ingreso-busqueda-dialogo.component.css']
})
export class SegmentoIngresoBusquedaDialogoComponent implements OnInit {

  segmento : Segmento;
  form: FormGroup;

  constructor(
    private dialogRef : MatDialogRef<SegmentoIngresoBusquedaDialogoComponent>,
    @Inject(MAT_DIALOG_DATA) private data : Segmento,
    private segmentoService : SegmentoService
  ) { }

  ngOnInit(): void {
    this.segmento = new Segmento();
    this.segmento.idSegmento = this.data.idSegmento;
    this.segmento.nombre = this.data.nombre;
    this.segmento.nit = this.data.nit;
    this.segmento.direccionFiscal = this.data.direccionFiscal;

    this.initForm();
  }

  private initForm() {
    this.form = new FormGroup({
      'id' : new FormControl(this.segmento.idSegmento),
      'nombre' : new FormControl(this.segmento.nombre),
      'nit' : new FormControl(this.segmento.nit),
      'direccionFiscal' : new FormControl(this.segmento.direccionFiscal)
    })
  }

  f() {
    return this.form.controls;
  }

  operar() {
    if(this.form.invalid) {return; }

    this.segmento.idSegmento = this.form.value['id'];
    this.segmento.nombre = this.form.value['nombre'];
    this.segmento.nit = this.form.value['nit'];
    this.segmento.direccionFiscal = this.form.value['direccionFiscal'];

    //ACTUALIZAR
    if(this.segmento != null && this.segmento.idSegmento > 0) {
      this.segmentoService.update(this.segmento).pipe(switchMap(() => {
        return this.segmentoService.getAll();
      })).subscribe(data => {
        this.segmentoService.setObjetoCambio(data);
        this.segmentoService.setMensajeCambio('El segmento se ha actualizado');
      })
    } else {
      this.segmentoService.create(this.segmento).pipe(switchMap(() => {
        return this.segmentoService.getAll();
      })).subscribe(data => {
        this.segmentoService.setObjetoCambio(data);
        this.segmentoService.setMensajeCambio('Segmento creado');
      })
    }

    this.cerrar();
  }

  cerrar() {
    this.dialogRef.close();
  }
}
