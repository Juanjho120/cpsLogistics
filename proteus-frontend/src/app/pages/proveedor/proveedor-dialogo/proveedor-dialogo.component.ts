import { switchMap } from 'rxjs/operators';
import { ProveedorService } from './../../../_service/proveedor.service';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { FormGroup, Validators, FormControl } from '@angular/forms';
import { Proveedor } from './../../../_model/proveedor';
import { Component, OnInit, Inject } from '@angular/core';

@Component({
  selector: 'app-proveedor-dialogo',
  templateUrl: './proveedor-dialogo.component.html',
  styleUrls: ['./proveedor-dialogo.component.css']
})
export class ProveedorDialogoComponent implements OnInit {

  form: FormGroup;
  proveedor : Proveedor;

  constructor(
    private dialogRef : MatDialogRef<ProveedorDialogoComponent>,
    @Inject(MAT_DIALOG_DATA) private data : Proveedor,
    private proveedorService : ProveedorService
  ) { }

  ngOnInit(): void {
    this.proveedor = new Proveedor();
    this.proveedor.idProveedor = this.data.idProveedor;
    this.proveedor.nombre = this.data.nombre;
    this.proveedor.direccionFiscal = this.data.direccionFiscal;
    this.proveedor.telefono = this.data.telefono;
    this.proveedor.nit = this.data.nit;

    this.initForm();
  }

  initForm() {
    this.form = new FormGroup({
      'id' : new FormControl(this.proveedor.idProveedor),
      'nombre' : new FormControl(this.proveedor.nombre, Validators.required),
      'telefono' : new FormControl(this.proveedor.telefono, Validators.required),
      'direccion' : new FormControl(this.proveedor.direccionFiscal, Validators.required),
      'nit' : new FormControl(this.proveedor.nit)
    })
  }

  f() {
    return this.form.controls;
  }

  operar() {
    if(this.form.invalid) {return; }

    this.proveedor.nombre = this.form.value['nombre'];
    this.proveedor.telefono = this.form.value['telefono'];
    this.proveedor.direccionFiscal = this.form.value['direccion'];
    this.proveedor.nit = this.form.value['nit'];

    if(this.proveedor.idProveedor > 0) {
      this.proveedorService.update(this.proveedor).pipe(switchMap(() => {
        return this.proveedorService.getAll();
      })).subscribe(data => {
        this.proveedorService.setObjetoCambio(data);
        this.proveedorService.setMensajeCambio('Proveedor actualizado');
      });
    } else {
      this.proveedorService.create(this.proveedor).pipe(switchMap(() => {
        return this.proveedorService.getAll();
      })).subscribe(data => {
        this.proveedorService.setObjetoCambio(data);
        this.proveedorService.setMensajeCambio('Proveedor creado');
      });
    }
    
    this.cerrar();
  }

  cerrar() {
    this.dialogRef.close();
  }

}
