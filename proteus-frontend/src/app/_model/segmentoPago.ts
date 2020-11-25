import { SegmentoCreditoDetalle } from './segmentoCreditoDetalle';
export class SegmentoPago {
    idSegmentoPago : number;
    segmentoCreditoDetalle : SegmentoCreditoDetalle;
    fechaHoraPago : string;
    monto : number;
}