import { Rol } from './rol';
export class Usuario {
    idUsuario : number;
    nombre : string;
    apellido : string;
    username : string;
    email : string;
    telefono : string;
    roles : Rol[];
}