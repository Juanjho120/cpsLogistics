import { ProveedorAsesorService } from './../../_service/proveedor-asesor.service';
import { AsesorDialogoComponent } from './asesor-dialogo/asesor-dialogo.component';
import { AsesorDialogoEliminarComponent } from './asesor-dialogo-eliminar/asesor-dialogo-eliminar.component';
import { ProveedorDialogoEliminarComponent } from './proveedor-dialogo-eliminar/proveedor-dialogo-eliminar.component';
import { ProveedorDialogoComponent } from './proveedor-dialogo/proveedor-dialogo.component';
import { MatSnackBar } from '@angular/material/snack-bar';
import { MatDialog } from '@angular/material/dialog';
import { ProveedorAsesor } from './../../_model/proveedorAsesor';
import { Proveedor } from './../../_model/proveedor';
import { ProveedorDTO } from './../../_model/dto/proveedorDTO';
import { ProveedorService } from './../../_service/proveedor.service';
import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-proveedor',
  templateUrl: './proveedor.component.html',
  styleUrls: ['./proveedor.component.css']
})
export class ProveedorComponent implements OnInit {

  proveedores : ProveedorDTO[] = [];

  constructor(
    private proveedorService : ProveedorService,
    private proveedorAsesorService : ProveedorAsesorService,
    private dialog : MatDialog,
    private snackBar : MatSnackBar
  ) { }

  ngOnInit(): void {
    this.proveedorAsesorService.getObjetoCambio().subscribe(data => {
      this.proveedorService.getAllDTO().subscribe(datas => {
        this.proveedores = datas;
      });
    });

    this.proveedorService.getObjetoCambio().subscribe(data => {
      this.proveedorService.getAllDTO().subscribe(datas => {
        this.proveedores = datas;
      });
    });

    this.proveedorAsesorService.getMensajeCambio().subscribe(data => {
      this.snackBar.open(data, 'AVISO', {duration : 2000});
    });

    this.proveedorService.getMensajeCambio().subscribe(data => {
      this.snackBar.open(data, 'AVISO', {duration : 2000});
    });

    this.proveedorService.getAllDTO().subscribe(data => {
      this.proveedores = data;
    });
  }

  abrirAsesorDialogo(proveedorAsesor : ProveedorAsesor, proveedorDTO : ProveedorDTO) {
    let proveedorAsesorAux = new ProveedorAsesor();
    if(proveedorAsesor != null) {
      proveedorAsesorAux = proveedorAsesor;
    } else if(proveedorDTO != null) {
      proveedorAsesorAux.proveedor = proveedorDTO.proveedor;
    }
    this.dialog.open(AsesorDialogoComponent, {
      width: '300px',
      data: proveedorAsesorAux
    })
  }

  abrirAsesorDialogoEliminar(proveedorAsesor : ProveedorAsesor) {
    this.dialog.open(AsesorDialogoEliminarComponent, {
      width: '300px',
      data: proveedorAsesor
    })
  }

  abrirProveedorDialogo(proveedor : Proveedor) {
    let proveedorAux = proveedor != null ? proveedor : new Proveedor();
    this.dialog.open(ProveedorDialogoComponent, {
      width: '300px',
      data: proveedorAux
    })
  }

  abrirProveedorDialogoEliminar(proveedor : Proveedor) {
    this.dialog.open(ProveedorDialogoEliminarComponent, {
      width: '300px',
      data: proveedor
    })
  }
}
