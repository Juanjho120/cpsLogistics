import { ChecklistServicioDialogoComponent } from './checklist-servicio-dialogo/checklist-servicio-dialogo.component';
import { MatDialog } from '@angular/material/dialog';
import { ChecklistService } from './../../../_service/checklist.service';
import { CajaChicaService } from './../../../_service/caja-chica.service';
import { CajaChica } from './../../../_model/cajaChica';
import { MatSnackBar } from '@angular/material/snack-bar';
import { ServicioService } from './../../../_service/servicio.service';
import { Servicio } from './../../../_model/servicio';
import { FormGroup, FormControl, Validators } from '@angular/forms';
import { Component, OnInit } from '@angular/core';
import * as moment from 'moment';

@Component({
  selector: 'app-servicio-resumen',
  templateUrl: './servicio-resumen.component.html',
  styleUrls: ['./servicio-resumen.component.css']
})
export class ServicioResumenComponent implements OnInit {

  formBusqueda : FormGroup;

  servicio : Servicio;
  totalTrabajo : number = 0;
  totalRepuesto : number = 0;
  totalCajaChica : number = 0;

  cajasChicas : CajaChica[] = [];

  formatoFecha : string = 'YYYY-MM-DD';

  constructor(
    private servicioService : ServicioService,
    private cajaChicaService : CajaChicaService,
    private checklistService : ChecklistService,
    private snackBar : MatSnackBar,
    private dialog : MatDialog
  ) { }

  ngOnInit(): void {
    this.servicioService.getMensajeCambio().subscribe(data => {
      this.snackBar.open(data, 'AVISO', {duration : 2000});
    });

    this.cargarServicio(1);

    this.formBusqueda = new FormGroup({
      'noServicio' : new FormControl('', Validators.required)
    });
  }

  cargarServicio(idServicio : number) {
    
    this.servicioService.getById(idServicio).subscribe(data => {
      if(data!=null) {
        this.totalTrabajo = 0;
        this.totalRepuesto = 0;
        this.totalCajaChica = 0;
        this.servicio = data;
        if(!(this.servicio.servicioTipo.nombre == "REPARACION")) {
          this.servicio.servicioTipo.nombre = `SERVICIO ${this.servicio.servicioTipo.nombre}`;
        }

        document.getElementById("div-servicio").style.display = "block";
        for(let servicioTrabajo of this.servicio.servicioTrabajo) {
          this.totalTrabajo += servicioTrabajo.costo;
        };

        for(let servicioRepuesto of this.servicio.servicioRepuesto) {
          this.totalRepuesto += servicioRepuesto.costoTotal;
        };

        this.servicio.fechaHora = moment(this.servicio.fechaHora).format(this.formatoFecha);

        this.cajasChicas = [];
        this.cajaChicaService.getByServicio(idServicio).subscribe(data => {
          this.cajasChicas = data;

          for(let cajaChica of this.cajasChicas) {
            this.totalCajaChica += cajaChica.monto;
          }
          
        });
      }
      
    });
  }

  buscar() {
    this.servicio = new Servicio();
    document.getElementById("div-servicio").style.display = "none";
    this.cargarServicio(this.formBusqueda.value['noServicio']);
  }

  abrirChecklistDialogo() {
    this.checklistService.getByServicio(this.servicio.idServicio).subscribe(dataChecklist => {
      if(dataChecklist!=null) {
        this.dialog.open(ChecklistServicioDialogoComponent, {
          width: '1300px',
          data: dataChecklist
        });
      } else {
        this.snackBar.open('El servicio no posee checklist', 'AVISO', {duration : 2000});
      }
    });
  }

  imprimirReporte() {

  }

}