import { ProductoEntradaSalidaDTO } from './../../../_model/dto/productoEntradaSalidaDTO';
import { map } from 'rxjs/operators';
import { RepuestoService } from './../../../_service/repuesto.service';
import { Observable } from 'rxjs';
import { Repuesto } from './../../../_model/repuesto';
import { ReporteService } from './../../../_service/reporte.service';
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
  formProducto: FormGroup;

  repuesto : Repuesto;
  repuestos : Repuesto[];
  repuestosFiltrados$ : Observable<Repuesto[]>;

  myControlFechaInicio : FormControl = new FormControl('', Validators.required);
  myControlFechaFin : FormControl = new FormControl('', Validators.required);
  myControlFechaInicioProducto : FormControl = new FormControl('', Validators.required);
  myControlFechaFinProducto : FormControl = new FormControl('', Validators.required);
  myControlRepuesto : FormControl = new FormControl('', Validators.required);

  maxFecha: Date = new Date();
  fechaInicioSeleccionada: Date = new Date();
  fechaFinSeleccionada: Date = new Date();
  fechaInicioProductoSeleccionada: Date = new Date();
  fechaFinProductoSeleccionada: Date = new Date();
  fechaInicioFormato : string;
  fechaFinFormato : string;
  fechaInicioProductoFormato : string;
  fechaFinProductoFormato : string;

  formatoFechaHora : string = 'YYYY-MM-DD 00:00:00';
  formatoFecha : string = 'YYYY-MM-DD';

  displayedColumns = ['codigo', 'descripcion', 'entrada', 'salida', 'existencia'];
  displayedColumnsProducto = ['fecha', 'inventarios', 'entrada', 'salida', 'razon'];
  dataSource : MatTableDataSource<InventarioEntradaSalidaDTO>;
  dataSourceProducto : MatTableDataSource<ProductoEntradaSalidaDTO>;

  inventarios : Inventario[] = [];

  botonReporteDisabled : boolean = true;
  botonReporteProductoDisabled : boolean = true;

  constructor(
    private inventarioService : InventarioService,
    private repuestoService : RepuestoService,
    private reporteService : ReporteService
  ) { }

  ngOnInit(): void {
    this.form = new FormGroup({
      'fechaInicio' : this.myControlFechaInicio,
      'fechaFin' : this.myControlFechaFin
    });

    this.formProducto = new FormGroup({
      'fechaInicio' : this.myControlFechaInicioProducto,
      'fechaFin' : this.myControlFechaFinProducto,
      'repuesto' : this.myControlRepuesto
    });

    this.repuestoService.getAll().subscribe(data => {
      this.repuestos = data;
    });

    this.repuestosFiltrados$ = this.myControlRepuesto.valueChanges.pipe(map(val => this.filtrarRepuestos(val)));
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

  cambiarFechaInicioProducto(e : any) {
    this.fechaInicioProductoSeleccionada = e.value;
    this.fechaInicioProductoFormato = moment(this.fechaInicioProductoSeleccionada).format(this.formatoFechaHora);
  }

  cambiarFechaFinProducto(e : any) {
    this.fechaFinProductoSeleccionada = e.value;
    this.fechaFinProductoFormato = moment(this.fechaFinProductoSeleccionada).format(this.formatoFechaHora);
  }

  buscarProducto() {
    this.inventarioService.getProductoEntradaSalidaDTO(this.repuesto.idRepuesto, this.fechaInicioProductoFormato, this.fechaFinProductoFormato).subscribe(data => {
      let productoDtos : ProductoEntradaSalidaDTO[] =[];
      for(let productoDto of data) {
        productoDto.fecha = moment(productoDto.fecha).format(this.formatoFecha);
        productoDtos.push(productoDto);
      }
      this.dataSourceProducto = new MatTableDataSource(productoDtos);
      this.botonReporteProductoDisabled = false;
    });
  }

  buscar() {
    this.inventarioService.getInventarioEntradaSalidaByFechaRango(this.fechaInicioFormato, this.fechaFinFormato).subscribe(data => {
      this.dataSource = new MatTableDataSource(data);
      this.botonReporteDisabled = false;
    });
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

  crearReporteProductoEntradaSalida() {}

  crearReporteInventarioEntradaSalida() {
    this.reporteService.crearReporteInventarioEntradaSalida(this.fechaInicioFormato, this.fechaFinFormato).subscribe(data => {
      const url = window.URL.createObjectURL(data);
      const a = document.createElement('a');
      a.setAttribute('style', 'display:none');
      document.body.appendChild(a);
      a.href = url;
      a.download = 'inventarioEntradaSalida.pdf';
      a.click();
    });
  }

}
