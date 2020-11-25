import { CreditoProveedor } from './creditoProveedor';
export class PagoProveedor {
    idPagoProveedor : number;
    creditoProveedor : CreditoProveedor;
    noDocumento : string;
    monto : number;
    fechaHoraPago : string;
    observaciones : string;
}