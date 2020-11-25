import { FacturaCompraDetalle } from './facturaCompraDetalle';
import { Proveedor } from './proveedor';
export class FacturaCompra {
    idFacturaCompra : number;
    proveedor : Proveedor;
    codigo : string;
    fecha : string;
    total : number;
    facturaCompraDetalle : FacturaCompraDetalle[];
}