import { DataService } from './../../../_service/data.service';
import { Inventario } from './../../../_model/inventario';
import { Usuario } from './../../../_model/usuario';
import { UsuarioService } from './../../../_service/usuario.service';
import { map } from 'rxjs/operators';
import { InventarioService } from './../../../_service/inventario.service';
import { MatSnackBar } from '@angular/material/snack-bar';
import { RepuestoService } from './../../../_service/repuesto.service';
import { Observable } from 'rxjs';
import { Repuesto } from './../../../_model/repuesto';
import { FormGroup, FormControl, Validators } from '@angular/forms';
import { Component, OnInit } from '@angular/core';
import * as moment from 'moment';

@Component({
  selector: 'app-inventario-busqueda',
  templateUrl: './inventario-busqueda.component.html',
  styleUrls: ['./inventario-busqueda.component.css']
})
export class InventarioBusquedaComponent implements OnInit {

  form : FormGroup;

  myControlRepuesto : FormControl = new FormControl('', Validators.required);
  myControlConcepto : FormControl = new FormControl('', Validators.required);
  myControlFechaInicio : FormControl = new FormControl('', Validators.required);
  myControlFechaFin : FormControl = new FormControl('', Validators.required);
  myControlUsuario : FormControl = new FormControl('', Validators.required);

  porConcepto : boolean = false;
  porRepuesto : boolean = false;
  porFecha : boolean = false;
  porUsuario : boolean = false;

  idConcepto : number = 0;
  idUsuario : number = 0;

  repuesto : Repuesto;
  repuestos : Repuesto[];
  repuestosFiltrados$ : Observable<Repuesto[]>;

  formatoFechaHora : string = 'YYYY-MM-DD 00:00:00';
  formatoFechaHoraBusqueda : string = 'YYYY-MM-DD HH:mm:ss';
  maxFecha: Date = new Date();
  fechaInicioSeleccionada: Date = new Date();
  fechaFinSeleccionada: Date = new Date();
  fechaInicioFormato : string;
  fechaFinFormato : string;

  usuarios$ : Observable<Usuario[]>;
  inventarios : Inventario[] = [];

  constructor(
    private repuestoService : RepuestoService,
    private usuarioService : UsuarioService,
    private inventarioService : InventarioService,
    private dataService : DataService,
    private snackBar : MatSnackBar
  ) { }

  ngOnInit(): void {
    this.form = new FormGroup({
      'concepto' : this.myControlConcepto,
      'repuesto' : this.myControlRepuesto,
      'fechaInicio' : this.myControlFechaInicio,
      'fechaFin' : this.myControlFechaFin,
      'usuario' : this.myControlUsuario
    });

    this.form.get('concepto').disable();
    this.form.get('repuesto').disable();
    this.form.get('fechaInicio').disable();
    this.form.get('fechaFin').disable();
    this.form.get('usuario').disable();

    this.repuestoService.getAll().subscribe(data => {
      this.repuestos = data;
    });

    this.repuestosFiltrados$ = this.myControlRepuesto.valueChanges.pipe(map(val => this.filtrarRepuestos(val)))

    this.usuarios$ = this.usuarioService.getAll();
  }

  convertirInventarioFechaHora(i : Inventario) {
    let inventario : Inventario = new Inventario();
    inventario.idInventario = i.idInventario;
    inventario.usuario = i.usuario;
    inventario.concepto = i.concepto;
    inventario.cantidad = i.cantidad;
    inventario.razon = i.razon;
    inventario.fechaHora = moment(i.fechaHora).format(this.formatoFechaHoraBusqueda);
    inventario.inventarioDetalle = i.inventarioDetalle;
    return inventario;
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
    this.repuesto = e.option.value;
  }

  setRepuestoInput(e : boolean) {
    this.inventarios = [];
    this.myControlRepuesto.reset();
    if(e) {
      this.form.get('repuesto').enable();
    } else {
      this.form.get('repuesto').disable();
    }
  }

  setConceptoSelect(e : boolean) {
    this.inventarios = [];
    this.myControlConcepto.reset();
    if(e) {
      this.form.get('concepto').enable();
    } else {
      this.form.get('concepto').disable();
    }
  }

  setFechaInput(e : boolean) {
    this.inventarios = [];
    this.myControlFechaInicio.reset();
    this.myControlFechaFin.reset();
    if(e) {
      this.form.get('fechaInicio').enable();
      this.form.get('fechaFin').enable();
    } else {
      this.form.get('fechaInicio').disable();
      this.form.get('fechaFin').disable();
    }
  }

  setUsuarioSelect(e : boolean) {
    this.inventarios = [];
    this.myControlUsuario.reset();
    if(e) {
      this.form.get('usuario').enable();
    } else {
      this.form.get('usuario').disable();
    }
  }

  cambiarFechaInicio(e : any) {
    this.fechaInicioSeleccionada = e.value;
    this.fechaInicioFormato = moment(this.fechaInicioSeleccionada).format(this.formatoFechaHora);
  }

  cambiarFechaFin(e : any) {
    this.fechaFinSeleccionada = e.value;
    this.fechaFinFormato = moment(this.fechaFinSeleccionada).format(this.formatoFechaHora);
  }

  buscar() {
    if(!this.porConcepto && !this.porRepuesto && !this.porFecha && !this.porUsuario) {

      let mensaje = 'Debe elegir algún parámetro de búsqueda';
      this.snackBar.open(mensaje, "AVISO", { duration : 2000});

    } else if(this.porConcepto && !this.porRepuesto && !this.porFecha && !this.porUsuario) {

      this.inventarios = [];
      this.inventarioService.getByConcepto(this.idConcepto).subscribe(data => {
        if(data!=null) {
          if(data.length > 0) {
            for(let d of data) {
              this.inventarios.push(this.convertirInventarioFechaHora(d));
            }
          }
        }
      });

    } else if(!this.porConcepto && this.porRepuesto && !this.porFecha && !this.porUsuario) {

      this.inventarios = [];
      this.inventarioService.getByRepuesto(this.repuesto.idRepuesto).subscribe(data => {
        if(data!=null) {
          if(data.length > 0) {
            for(let d of data) {
              this.inventarios.push(this.convertirInventarioFechaHora(d));
            }
          }
        }
      });

    } else if(!this.porConcepto && !this.porRepuesto && this.porFecha && !this.porUsuario) {

      this.inventarios = [];
      this.inventarioService.getByFechaRango(this.fechaInicioFormato, this.fechaFinFormato).subscribe(data => {
        if(data!=null) {
          if(data.length > 0) {
            for(let d of data) {
              this.inventarios.push(this.convertirInventarioFechaHora(d));
            }
          }
        }
      });

    } else if(!this.porConcepto && !this.porRepuesto && !this.porFecha && this.porUsuario) {

      this.inventarios = [];
      this.inventarioService.getByUsuario(this.idUsuario).subscribe(data => {
        if(data!=null) {
          if(data.length > 0) {
            for(let d of data) {
              this.inventarios.push(this.convertirInventarioFechaHora(d));
            }
          }
        }
      });

    } else if(this.porConcepto && !this.porRepuesto && this.porFecha && !this.porUsuario) {

      this.inventarios = [];
      this.inventarioService.getByConceptoAndFechaRango(this.idConcepto, this.fechaInicioFormato, this.fechaFinFormato).subscribe(data => {
        if(data!=null) {
          if(data.length > 0) {
            for(let d of data) {
              this.inventarios.push(this.convertirInventarioFechaHora(d));
            }
          }
        }
      });

    } else if(this.porConcepto && this.porRepuesto && !this.porFecha && !this.porUsuario) {

      this.inventarios = [];
      this.inventarioService.getByConceptoAndRepuesto(this.idConcepto, this.repuesto.idRepuesto).subscribe(data => {
        if(data!=null) {
          if(data.length > 0) {
            for(let d of data) {
              this.inventarios.push(this.convertirInventarioFechaHora(d));
            }
          }
        }
      });

    } else if(this.porConcepto && this.porRepuesto && !this.porFecha && !this.porUsuario) {

      this.inventarios = [];
      this.inventarioService.getByConceptoAndRepuesto(this.idConcepto, this.repuesto.idRepuesto).subscribe(data => {
        if(data!=null) {
          if(data.length > 0) {
            for(let d of data) {
              this.inventarios.push(this.convertirInventarioFechaHora(d));
            }
          }
        }
      });

    } else if(this.porConcepto && !this.porRepuesto && !this.porFecha && this.porUsuario) {

      this.inventarios = [];
      this.inventarioService.getByConceptoAndUsuario(this.idConcepto, this.idUsuario).subscribe(data => {
        if(data!=null) {
          if(data.length > 0) {
            for(let d of data) {
              this.inventarios.push(this.convertirInventarioFechaHora(d));
            }
          }
        }
      });

    } else if(!this.porConcepto && this.porRepuesto && !this.porFecha && this.porUsuario) {

      this.inventarios = [];
      this.inventarioService.getByRepuestoAndUsuario(this.repuesto.idRepuesto, this.idUsuario).subscribe(data => {
        if(data!=null) {
          if(data.length > 0) {
            for(let d of data) {
              this.inventarios.push(this.convertirInventarioFechaHora(d));
            }
          }
        }
      });

    } else if(!this.porConcepto && this.porRepuesto && this.porFecha && !this.porUsuario) {

      this.inventarios = [];
      this.inventarioService.getByRepuestoAndFechaRango(this.repuesto.idRepuesto, this.fechaInicioFormato, this.fechaFinFormato).subscribe(data => {
        if(data!=null) {
          if(data.length > 0) {
            for(let d of data) {
              this.inventarios.push(this.convertirInventarioFechaHora(d));
            }
          }
        }
      });

    } else if(!this.porConcepto && !this.porRepuesto && this.porFecha && this.porUsuario) {

      this.inventarios = [];
      this.inventarioService.getByUsuarioAndFechaRango(this.idUsuario, this.fechaInicioFormato, this.fechaFinFormato).subscribe(data => {
        if(data!=null) {
          if(data.length > 0) {
            for(let d of data) {
              this.inventarios.push(this.convertirInventarioFechaHora(d));
            }
          }
        }
      });

    } else if(this.porConcepto && this.porRepuesto && !this.porFecha && this.porUsuario) {

      this.inventarios = [];
      this.inventarioService.getByConceptoAndRepuestoAndUsuario(this.idConcepto, this.repuesto.idRepuesto, this.idUsuario).subscribe(data => {
        if(data!=null) {
          if(data.length > 0) {
            for(let d of data) {
              this.inventarios.push(this.convertirInventarioFechaHora(d));
            }
          }
        }
      });

    } else if(this.porConcepto && this.porRepuesto && this.porFecha && !this.porUsuario) {

      this.inventarios = [];
      this.inventarioService.getByConceptoAndRepuestoAndFecha(this.idConcepto, this.repuesto.idRepuesto, this.fechaInicioFormato, this.fechaFinFormato).subscribe(data => {
        if(data!=null) {
          if(data.length > 0) {
            for(let d of data) {
              this.inventarios.push(this.convertirInventarioFechaHora(d));
            }
          }
        }
      });

    } else if(!this.porConcepto && this.porRepuesto && this.porFecha && this.porUsuario) {

      this.inventarios = [];
      this.inventarioService.getByRepuestoAndFechaAndUsuario(this.repuesto.idRepuesto, this.fechaInicioFormato, this.fechaFinFormato, this.idUsuario).subscribe(data => {
        if(data!=null) {
          if(data.length > 0) {
            for(let d of data) {
              this.inventarios.push(this.convertirInventarioFechaHora(d));
            }
          }
        }
      });

    } else if(this.porConcepto && !this.porRepuesto && this.porFecha && this.porUsuario) {

      this.inventarios = [];
      this.inventarioService.getByConceptoAndFechaAndUsuario(this.idConcepto, this.fechaInicioFormato, this.fechaFinFormato, this.idUsuario).subscribe(data => {
        if(data!=null) {
          if(data.length > 0) {
            for(let d of data) {
              this.inventarios.push(this.convertirInventarioFechaHora(d));
            }
          }
        }
      });

    } else if(this.porConcepto && this.porRepuesto && this.porFecha && this.porUsuario) {

      this.inventarios = [];
      this.inventarioService.getByConceptoAndRepuestoAndFechaAndUsuario(this.idConcepto, this.repuesto.idRepuesto, this.fechaInicioFormato, this.fechaFinFormato, this.idUsuario).subscribe(data => {
        if(data!=null) {
          if(data.length > 0) {
            for(let d of data) {
              this.inventarios.push(this.convertirInventarioFechaHora(d));
            }
          }
        }
      });

    }
  } 

  enviarInventarioEdicion(inventario : Inventario) {
    this.dataService.setInventarioCambio(inventario);
  }
}
