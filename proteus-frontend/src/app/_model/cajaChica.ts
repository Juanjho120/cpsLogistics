import { Proveedor } from './proveedor';
import { Servicio } from './servicio';
export class CajaChica {
    idCajaChica : number;
    servicio : Servicio;
    proveedor : Proveedor;
    descripcion : string;
    monto : number;
    codigoFactura : string;
}