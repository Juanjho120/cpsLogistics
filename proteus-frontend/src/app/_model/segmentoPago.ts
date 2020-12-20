import { PagoTipo } from './pagoTipo';
import { SegmentoPagoDetalle } from './segmentoPagoDetalle';
export class SegmentoPago {
    idSegmentoPago : number;
    pagoTipo : PagoTipo;
    idItem : number;
    fechaHoraPago : string;
    monto : number;
    facturas : string;
    segmentoPagoDetalle : SegmentoPagoDetalle[];
}