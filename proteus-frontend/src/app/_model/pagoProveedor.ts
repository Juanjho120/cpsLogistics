import { PagoProveedorDetalle } from './pagoProveedorDetalle';
import { PagoTipo } from './pagoTipo';
export class PagoProveedor {
    idPagoProveedor : number;
    pagoTipo : PagoTipo;
    idItem : number;
    monto : number;
    fechaHoraPago : string;
    observaciones : string;
    pagoProveedorDetalle : PagoProveedorDetalle[];
}