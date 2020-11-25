package proteus.cuenta.service;

import proteus.cuenta.model.CuentaBancariaTipo;
import proteus.generico.service.ICRUD;

/**
 * Services for CuentaBancariaTipo Model (Interface)
 * 
 * @author Juan Tzun
 */
public interface ICuentaBancariaTipoService extends ICRUD<CuentaBancariaTipo, Integer>{

	CuentaBancariaTipo getByNombre(String nombre) throws Exception;
	
}
