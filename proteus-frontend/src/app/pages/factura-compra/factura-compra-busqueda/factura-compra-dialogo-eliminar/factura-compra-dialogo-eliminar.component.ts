import { switchMap } from 'rxjs/operators';
import { MAT_DIALOG_DATA, MatDialogRef } from '@angular/material/dialog';
import { FacturaCompraService } from './../../../../_service/factura-compra.service';
import { FacturaCompra } from './../../../../_model/facturaCompra';
import { Component, OnInit, Inject } from '@angular/core';

@Component({
  selector: 'app-factura-compra-dialogo-eliminar',
  templateUrl: './factura-compra-dialogo-eliminar.component.html',
  styleUrls: ['./factura-compra-dialogo-eliminar.component.css']
})
export class FacturaCompraDialogoEliminarComponent implements OnInit {

  facturaCompra : FacturaCompra;

  constructor(
    private dialogRef : MatDialogRef<FacturaCompraDialogoEliminarComponent>,
    @Inject(MAT_DIALOG_DATA) private data : FacturaCompra,
    private facturaCompraService : FacturaCompraService
  ) { }

  ngOnInit(): void {
    this.facturaCompra = new FacturaCompra();
    this.facturaCompra.idFacturaCompra = this.data.idFacturaCompra;
    this.facturaCompra.codigo = this.data.codigo;
    this.facturaCompra.proveedor = this.data.proveedor;
  }

  eliminar() {
    this.facturaCompraService.delete(this.facturaCompra.idFacturaCompra).pipe(switchMap(() =>{
      return this.facturaCompraService.getAll();
    })).subscribe(data => {
      this.facturaCompraService.setObjetoCambio(data);
      this.facturaCompraService.setMensajeCambio('Factura eliminada');
    });
    this.cerrar();
  }

  cerrar() {
    this.dialogRef.close();
  }

}
