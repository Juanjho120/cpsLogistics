import { UsuarioService } from './../../../_service/usuario.service';
import { DataService } from './../../../_service/data.service';
import { Usuario } from './../../../_model/usuario';
import { Concepto } from './../../../_model/concepto';
import { InventarioService } from './../../../_service/inventario.service';
import { map } from 'rxjs/operators';
import { MatSnackBar } from '@angular/material/snack-bar';
import { RepuestoService } from './../../../_service/repuesto.service';
import { Observable } from 'rxjs';
import { InventarioDetalle } from './../../../_model/inventarioDetalle';
import { Inventario } from './../../../_model/inventario';
import { Repuesto } from './../../../_model/repuesto';
import { FormGroup, FormControl, Validators } from '@angular/forms';
import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-inventario-registro',
  templateUrl: './inventario-registro.component.html',
  styleUrls: ['./inventario-registro.component.css']
})
export class InventarioRegistroComponent implements OnInit {

  idConcepto : number;
  form : FormGroup;
  formInventario : FormGroup;

  repuestos : Repuesto[];
  repuestoSeleccionado : Repuesto;

  inventarioDetalle : InventarioDetalle[] = [];

  myControlRepuesto : FormControl = new FormControl('', Validators.required);

  repuestosFiltrados$ : Observable<Repuesto[]>;

  inventarioCompleto : boolean = false;

  idInventario : number = 0;

  conceptoControl : boolean = false;

  usuarioLogueado : Usuario;

  constructor(
    private repuestoService : RepuestoService,
    private inventarioService : InventarioService,
    private dataService : DataService,
    private usuarioService : UsuarioService,
    private snackBar : MatSnackBar
  ) { }

  ngOnInit(): void {
    this.form = new FormGroup({
      'cantidad' : new FormControl('', Validators.required),
      'repuesto' : this.myControlRepuesto
    });

    this.formInventario = new FormGroup({
      'razon' : new FormControl('', Validators.required)
    });

    this.repuestoService.getAll().subscribe(data => {
      this.repuestos = data;
    });

    this.repuestosFiltrados$ = this.myControlRepuesto.valueChanges.pipe(map(val => this.filtrarRepuestos(val)));

    this.dataService.getInventarioCambio().subscribe(data => {
      this.cargarCamposInventario(data);
    });

    this.usuarioService.getUsuarioByToken().subscribe(data => {
      this.usuarioLogueado = data;
    });

  }

  cargarCamposInventario(inventario : Inventario) {
    this.idInventario = inventario.idInventario;
    this.formInventario.patchValue({
      razon : inventario.razon
    });
    this.idConcepto = inventario.concepto.idConcepto;
    this.inventarioDetalle = inventario.inventarioDetalle;
    this.inventarioCompleto = true;
    this.conceptoControl = true;
  }
  
  filtrarRepuestos(val : any) {
    if(val != null && val.idRepuesto > 0) {
      return this.repuestos.filter(el => 
        el.descripcion.toLowerCase().includes(val.descripcion.toLowerCase()) || el.codigo.toLowerCase().includes(val.codigo.toLowerCase())
      );
    }
    return this.repuestos.filter(el =>
      el.descripcion.toLowerCase().includes(val?.toLowerCase()) || el.codigo.toLowerCase().includes(val?.toLowerCase())
    );
  }

  mostrarRepuesto(repuesto : Repuesto) {
    return repuesto ? `${repuesto.descripcion}` : repuesto;
  }

  seleccionarRepuesto(e: any) {
    this.repuestoSeleccionado = e.option.value;
  }
  
  agregarDetalle() {
    if(this.repuestoSeleccionado) {
      let cont = 0;
      for(let i = 0; i < this.inventarioDetalle.length; i++) {
        let inventarioDetalleAux = this.inventarioDetalle[i];
        if(inventarioDetalleAux.repuesto.idRepuesto === this.repuestoSeleccionado.idRepuesto) {
          cont++;
          break;
        }
      }

      if(cont > 0) {
        let mensaje = 'Este repuesto ya se encuentra agregado'
        this.snackBar.open(mensaje, "AVISO", { duration : 2000});
      } else {
        let inventarioDetalle = new InventarioDetalle();
        inventarioDetalle.cantidad = this.form.value['cantidad'];
        inventarioDetalle.repuesto = this.repuestoSeleccionado;
        this.inventarioDetalle.push(inventarioDetalle);
        this.limpiarControlDetalle();
      }
    } else {
      let mensaje = 'Seleccione un repuesto valido'
      this.snackBar.open(mensaje, "AVISO", { duration : 2000});
    }
    this.verificarInventarioCompleto();
  }

  removerDetalle(index : number) {
    this.inventarioDetalle.splice(index, 1);
    this.verificarInventarioCompleto();
  }

  verificarInventarioCompleto() {
    if(this.inventarioDetalle.length > 0) {
      this.inventarioCompleto = true;
    } else {
      this.inventarioCompleto = false;
    }
  }

  limpiarControlDetalle() {
    this.repuestoSeleccionado = null;
    this.myControlRepuesto.reset();
    this.form.reset();
  }

  limpiarControlGeneral() {
    this.limpiarControlDetalle();
    this.formInventario.reset();
    this.inventarioDetalle = [];
    this.inventarioCompleto = false;
    this.idConcepto = 0;
    this.conceptoControl = false;
  }

  guardarInventario() {
    if(this.idConcepto > 0) {
      let inventario = new Inventario();
      inventario.usuario = this.usuarioLogueado;
      inventario.concepto = new Concepto();
      inventario.razon = this.formInventario.value['razon'];
      inventario.concepto.idConcepto = this.idConcepto;
      inventario.inventarioDetalle = this.inventarioDetalle;

      if(this.idInventario>0) {
        inventario.idInventario = this.idInventario;
        this.inventarioService.update(inventario).subscribe( () => {
          let mensaje = 'Inventario Actualizado'
          this.snackBar.open(mensaje, "AVISO", { duration : 2000});
          this.limpiarControlGeneral();
        });  
      } else {
        this.inventarioService.create(inventario).subscribe( () => {
          let mensaje = 'Inventario Creado'
          this.snackBar.open(mensaje, "AVISO", { duration : 2000});
          this.limpiarControlGeneral();
        });
      }
    } else {
      let mensaje = 'Identifique si es SALIDA o ENTRADA'
      this.snackBar.open(mensaje, "AVISO", { duration : 2000});
    }
  }

}
