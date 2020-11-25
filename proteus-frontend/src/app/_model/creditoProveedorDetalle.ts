import { FacturaCompra } from './facturaCompra';
import { CreditoProveedor } from './creditoProveedor';
export class CreditoProveedorDetalle {
    idCreditoProveedorDetalle : number;
    creditoProveedor : CreditoProveedor;
    facturaCompra : FacturaCompra;
    descripcion : string;
    observaciones : string;
    vencida : boolean;
}