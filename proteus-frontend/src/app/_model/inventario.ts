import { InventarioDetalle } from './inventarioDetalle';
import { Concepto } from './concepto';
import { Usuario } from './usuario';
export class Inventario {
    idInventario : number;
    usuario : Usuario;
    concepto : Concepto;
    razon : string;
    cantidad : number;
    fechaHora : string;
    inventarioDetalle : InventarioDetalle[];
}