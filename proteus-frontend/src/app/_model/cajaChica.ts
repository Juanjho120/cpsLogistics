import { ComprobanteTipo } from './comprobanteTipo';
import { Personal } from './personal';
import { Concepto } from './concepto';
import { ProveedorMenor } from './proveedorMenor';
import { Servicio } from './servicio';
export class CajaChica {
    idCajaChica : number;
    fechaIngreso : string;
    concepto : Concepto;
    monto : number;
    autoriza : Personal;
    recibe : Personal;
    descripcion : string;
    servicio : Servicio;
    comprobanteTipo : ComprobanteTipo;
    numeroComprobante : string;
    proveedorMenor : ProveedorMenor;
}