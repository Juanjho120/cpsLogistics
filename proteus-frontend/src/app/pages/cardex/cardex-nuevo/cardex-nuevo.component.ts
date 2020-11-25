import { switchMap } from 'rxjs/operators';
import { RepuestoService } from './../../../_service/repuesto.service';
import { Repuesto } from './../../../_model/repuesto';
import { FormGroup, FormControl, Validators } from '@angular/forms';
import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-cardex-nuevo',
  templateUrl: './cardex-nuevo.component.html',
  styleUrls: ['./cardex-nuevo.component.css']
})
export class CardexNuevoComponent implements OnInit {

  form : FormGroup;

  constructor(
    private repuestoService : RepuestoService
  ) { }

  ngOnInit(): void {
    this.initForm();
  }

  private initForm() {
    this.form = new FormGroup({
      'id': new FormControl(0),
      'codigo': new FormControl('', Validators.required),
      'codigoBarra' : new FormControl(''),
      'descripcion' : new FormControl('', [Validators.required, Validators.minLength(3)]),
      'existencia' : new FormControl('', Validators.required)
    });
  }

  f() {
    return this.form.controls;
  }

  operar() {
    if(this.form.invalid) {return; }

    let repuesto = new Repuesto();
    repuesto.idRepuesto = this.form.value['id'];
    repuesto.codigo = this.form.value['codigo'];
    repuesto.codigoBarra = this.form.value['codigoBarra'];
    repuesto.descripcion = this.form.value['descripcion'];
    repuesto.descripcion = repuesto.descripcion.toUpperCase();
    repuesto.existencia = this.form.value['existencia'];

    this.repuestoService.create(repuesto).pipe(switchMap(() => {
      return this.repuestoService.getAll();
    })).subscribe(data => {
      this.repuestoService.setObjetoCambio(data);
      this.repuestoService.setMensajeCambio('Repuesto creado');
    });

    this.initForm();
  }
}
