import { InventarioService } from './../../../_service/inventario.service';
import { Inventario } from './../../../_model/inventario';
import { MatTableDataSource } from '@angular/material/table';
import { InventarioEntradaSalidaDTO } from './../../../_model/dto/inventarioEntradaSalidaDTO';
import { FormGroup, FormControl, Validators } from '@angular/forms';
import { Component, OnInit } from '@angular/core';
import * as moment from 'moment';

@Component({
  selector: 'app-inventario-entrada-salida',
  templateUrl: './inventario-entrada-salida.component.html',
  styleUrls: ['./inventario-entrada-salida.component.css']
})
export class InventarioEntradaSalidaComponent implements OnInit {

  form : FormGroup;

  myControlFechaInicio : FormControl = new FormControl('', Validators.required);
  myControlFechaFin : FormControl = new FormControl('', Validators.required);

  maxFecha: Date = new Date();
  fechaInicioSeleccionada: Date = new Date();
  fechaFinSeleccionada: Date = new Date();
  fechaInicioFormato : string;
  fechaFinFormato : string;

  formatoFechaHora : string = 'YYYY-MM-DD 00:00:00';

  displayedColumns = ['codigo', 'descripcion', 'entrada', 'salida', 'existencia'];
  dataSource : MatTableDataSource<InventarioEntradaSalidaDTO>;

  inventarios : Inventario[] = [];

  constructor(
    private inventarioService : InventarioService
  ) { }

  ngOnInit(): void {
    this.form = new FormGroup({
      'fechaInicio' : this.myControlFechaInicio,
      'fechaFin' : this.myControlFechaFin
    });
  }

  cambiarFechaInicio(e : any) {
    this.fechaInicioSeleccionada = e.value;
    this.fechaInicioFormato = moment(this.fechaInicioSeleccionada).format(this.formatoFechaHora);
    console.log(this.fechaInicioFormato);
  }

  cambiarFechaFin(e : any) {
    this.fechaFinSeleccionada = e.value;
    this.fechaFinFormato = moment(this.fechaFinSeleccionada).format(this.formatoFechaHora);
    console.log(this.fechaFinFormato);
  }

  buscar() {
    this.inventarioService.getInventarioEntradaSalidaByFechaRango(this.fechaInicioFormato, this.fechaFinFormato).subscribe(data => {
      this.dataSource = new MatTableDataSource(data);
    });
  }
}
