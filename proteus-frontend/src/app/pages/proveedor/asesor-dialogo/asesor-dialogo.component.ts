import { switchMap } from 'rxjs/operators';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { ProveedorAsesorService } from './../../../_service/proveedor-asesor.service';
import { ProveedorAsesor } from './../../../_model/proveedorAsesor';
import { FormGroup, FormControl, Validators } from '@angular/forms';
import { Component, OnInit, Inject } from '@angular/core';

@Component({
  selector: 'app-asesor-dialogo',
  templateUrl: './asesor-dialogo.component.html',
  styleUrls: ['./asesor-dialogo.component.css']
})
export class AsesorDialogoComponent implements OnInit {

  form: FormGroup;
  proveedorAsesor : ProveedorAsesor;

  constructor(
    private dialogRef : MatDialogRef<AsesorDialogoComponent>,
    @Inject(MAT_DIALOG_DATA) private data : ProveedorAsesor,
    private proveedorAsesorService : ProveedorAsesorService
  ) { }

  ngOnInit(): void {
    this.proveedorAsesor = new ProveedorAsesor();
    this.proveedorAsesor.idProveedorAsesor = this.data.idProveedorAsesor;
    this.proveedorAsesor.proveedor = this.data.proveedor;
    this.proveedorAsesor.nombre = this.data.nombre;
    this.proveedorAsesor.telefono = this.data.telefono;

    this.initForm();
  }

  initForm() {
    this.form = new FormGroup({
      'id' : new FormControl(this.proveedorAsesor.idProveedorAsesor),
      'proveedor' : new FormControl(this.proveedorAsesor.proveedor),
      'nombre' : new FormControl(this.proveedorAsesor.nombre, Validators.required),
      'telefono' : new FormControl(this.proveedorAsesor.telefono, Validators.required)
    })
  }

  f() {
    return this.form.controls;
  }

  operar() {
    if(this.form.invalid) {return; }
    this.proveedorAsesor.nombre = this.form.value['nombre'];
    this.proveedorAsesor.telefono = this.form.value['telefono'];

    if(this.proveedorAsesor.idProveedorAsesor > 0) {
      this.proveedorAsesorService.update(this.proveedorAsesor).pipe(switchMap(() => {
        return this.proveedorAsesorService.getAll();
      })).subscribe(data => {
        this.proveedorAsesorService.setObjetoCambio(data);
        this.proveedorAsesorService.setMensajeCambio('Asesor actualizado');
      });
    } else {
      this.proveedorAsesorService.create(this.proveedorAsesor).pipe(switchMap(() => {
        return this.proveedorAsesorService.getAll();
      })).subscribe(data => {
        this.proveedorAsesorService.setObjetoCambio(data);
        this.proveedorAsesorService.setMensajeCambio('Asesor creado');
      });
    }

    this.cerrar();
  }

  cerrar() {
    this.dialogRef.close();
  }
}
