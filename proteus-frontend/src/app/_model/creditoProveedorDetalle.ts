import { FacturaCompra } from './facturaCompra';
import { CreditoProveedor } from './creditoProveedor';
export class CreditoProveedorDetalle {
    idCreditoProveedorDetalle : number;
    creditoProveedor : CreditoProveedor;
    facturaCompra : FacturaCompra;
    observaciones : string;
    vencida : boolean;
    pagada : boolean;
}