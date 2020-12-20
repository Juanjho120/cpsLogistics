import { Personal } from './../../../../_model/personal';
import { ComprobanteTipoService } from './../../../../_service/comprobante-tipo.service';
import { PersonalService } from './../../../../_service/personal.service';
import { switchMap } from 'rxjs/operators';
import { ProveedorMenorService } from './../../../../_service/proveedor-menor.service';
import { ProveedorMenor } from './../../../../_model/proveedorMenor';
import { Observable } from 'rxjs';
import { CajaChicaService } from './../../../../_service/caja-chica.service';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { CajaChica } from './../../../../_model/cajaChica';
import { FormGroup, Validators, FormControl } from '@angular/forms';
import { Component, OnInit, Inject } from '@angular/core';

@Component({
  selector: 'app-caja-chica-dialogo-editar',
  templateUrl: './caja-chica-dialogo-editar.component.html',
  styleUrls: ['./caja-chica-dialogo-editar.component.css']
})
export class CajaChicaDialogoEditarComponent implements OnInit {

  formCajaChica : FormGroup;
  cajaChica : CajaChica;

  proveedoresMenores$ : Observable<ProveedorMenor[]>;
  idProveedorMenor : number = 0;

  personal$ : Observable<Personal[]>;
  idAutoriza : number = 0;
  idRecibe : number = 0;

  constructor(
    private dialogRef : MatDialogRef<CajaChicaDialogoEditarComponent>,
    @Inject(MAT_DIALOG_DATA) private data : CajaChica,
    private cajaChicaService : CajaChicaService,
    private proveedorMenorService : ProveedorMenorService,
    private personalService : PersonalService
  ) { }

  ngOnInit(): void {
    console.log(this.data);
    this.proveedoresMenores$ = this.proveedorMenorService.getAll();

    this.personal$ = this.personalService.getAll();
    
    this.cajaChica = new CajaChica();
    this.cajaChica.idCajaChica = this.data.idCajaChica;
    this.cajaChica.servicio = this.data.servicio;
    this.cajaChica.fechaIngreso = this.data.fechaIngreso;
    this.cajaChica.descripcion = this.data.descripcion;
    this.cajaChica.monto = this.data.monto;
    this.cajaChica.autoriza = this.data.autoriza;
    this.cajaChica.recibe = this.data.recibe;
    this.cajaChica.concepto = this.data.concepto;
    this.cajaChica.comprobanteTipo = this.data.comprobanteTipo;
    this.cajaChica.numeroComprobante = this.data.numeroComprobante;
    this.cajaChica.proveedorMenor = this.data.proveedorMenor;

    this.initForm();
  }

  private initForm() {
    this.formCajaChica = new FormGroup({
      'fechaIngreso' : new FormControl(this.cajaChica.fechaIngreso),
      'concepto' : new FormControl(this.cajaChica.concepto.nombre, Validators.required),
      'autoriza' : new FormControl(this.cajaChica.autoriza.idPersonal, Validators.required),
      'recibe' : new FormControl(this.cajaChica.recibe.idPersonal, Validators.required),
      'servicio' : new FormControl(''),
      'descripcion' : new FormControl(this.cajaChica.descripcion, Validators.required),
      'comprobanteTipo' : new FormControl(this.cajaChica.comprobanteTipo.nombre, Validators.required),
      'proveedorMenor' : new FormControl(''),
      'numeroComprobante' : new FormControl(''),
      'monto' : new FormControl(this.cajaChica.monto, Validators.required),
    });

    this.formCajaChica.get('fechaIngreso').disable();
    this.formCajaChica.get('concepto').disable();
    this.formCajaChica.get('servicio').disable();
    this.formCajaChica.get('comprobanteTipo').disable();
    
    if(this.cajaChica.servicio == null) {
      this.formCajaChica.get('servicio').disable();
    } else {
      this.formCajaChica.patchValue({
        servicio : this.cajaChica.servicio.idServicio
      });
    }

    if(this.cajaChica.proveedorMenor==null) {
      this.formCajaChica.get('proveedorMenor').disable();
    } else {
      this.formCajaChica.get('proveedorMenor').enable();
      this.idProveedorMenor = this.cajaChica.proveedorMenor.idProveedorMenor;
      this.formCajaChica.patchValue({
        proveedorMenor : this.cajaChica.proveedorMenor.idProveedorMenor
      });
    }

    if(this.cajaChica.numeroComprobante==null) {
      this.formCajaChica.get('numeroComprobante').disable();
    } else {
      this.formCajaChica.get('numeroComprobante').enable();
      this.formCajaChica.patchValue({
        numeroComprobante : this.cajaChica.numeroComprobante
      });
    }

    this.idAutoriza = this.cajaChica.autoriza.idPersonal;
    this.idRecibe = this.cajaChica.recibe.idPersonal;
  }

  operar() {
    if(this.formCajaChica.invalid) {return; }

    this.cajaChica.descripcion = this.formCajaChica.value['descripcion'];
    this.cajaChica.monto = this.formCajaChica.value['monto'];
    this.cajaChica.numeroComprobante = this.formCajaChica.value['numeroComprobante'];
    this.cajaChica.autoriza = new Personal();
    this.cajaChica.recibe = new Personal();
    if(this.idProveedorMenor>0) {
      this.cajaChica.proveedorMenor = new ProveedorMenor();
      this.cajaChica.proveedorMenor.idProveedorMenor = this.idProveedorMenor;
    }
    this.cajaChica.autoriza.idPersonal = this.idAutoriza;
    this.cajaChica.recibe.idPersonal = this.idRecibe;

    //ACTUALIZAR
    if(this.cajaChica != null && this.cajaChica.idCajaChica > 0) {
      this.cajaChicaService.update(this.cajaChica).pipe(switchMap(() => {
        return this.cajaChicaService.getAll();
      })).subscribe(data => {
        this.cajaChicaService.setObjetoCambio(data);
        this.cajaChicaService.setMensajeCambio('Caja chica actualizada');
      })
    }
    this.cerrar();
  }

  cerrar() {
    this.dialogRef.close();
  }

}
