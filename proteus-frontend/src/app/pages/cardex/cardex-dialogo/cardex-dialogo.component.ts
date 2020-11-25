import { FormGroup, FormControl, Validators } from '@angular/forms';
import { switchMap } from 'rxjs/operators';
import { RepuestoService } from './../../../_service/repuesto.service';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { Repuesto } from './../../../_model/repuesto';
import { Component, OnInit, Inject } from '@angular/core';

@Component({
  selector: 'app-cardex-dialogo',
  templateUrl: './cardex-dialogo.component.html',
  styleUrls: ['./cardex-dialogo.component.css']
})
export class CardexDialogoComponent implements OnInit {

  repuesto : Repuesto;
  form: FormGroup;

  constructor(
    private dialogRef : MatDialogRef<CardexDialogoComponent>,
    @Inject(MAT_DIALOG_DATA) private data : Repuesto,
    private repuestoService : RepuestoService
  ) { }

  ngOnInit(): void {
    this.repuesto = new Repuesto();
    this.repuesto.idRepuesto = this.data.idRepuesto;
    this.repuesto.codigo = this.data.codigo;
    this.repuesto.codigoBarra = this.data.codigoBarra;
    this.repuesto.descripcion = this.data.descripcion;
    this.repuesto.existencia = this.data.existencia;

    this.initForm();
  }

  private initForm() {
    this.form = new FormGroup({
      'id': new FormControl(this.repuesto.idRepuesto),
      'codigo': new FormControl(this.repuesto.codigo, Validators.required),
      'codigoBarra' : new FormControl(this.repuesto.codigoBarra),
      'descripcion' : new FormControl(this.repuesto.descripcion, [Validators.required, Validators.minLength(3)]),
      'existencia' : new FormControl(this.repuesto.existencia, Validators.required)
    });
  }

  f() {
    return this.form.controls;
  }
  
  operar() {
    if(this.form.invalid) {return; }

    this.repuesto.codigo = this.form.value['codigo'];
    this.repuesto.codigoBarra = this.form.value['codigoBarra'];
    this.repuesto.descripcion = this.form.value['descripcion'];
    this.repuesto.existencia = this.form.value['existencia'];

    //ACTUALIZAR
    if(this.repuesto != null && this.repuesto.idRepuesto > 0) {
      this.repuestoService.update(this.repuesto).pipe(switchMap(() => {
        return this.repuestoService.getAll();
      })).subscribe(data => {
        this.repuestoService.setObjetoCambio(data);
        this.repuestoService.setMensajeCambio('El repuesto se ha actualizado');
      })
    }
    this.cerrar();
  }

  cerrar() {
    this.dialogRef.close();
  }

}
