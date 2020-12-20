package proteus.saldo.service;

import proteus.generico.service.ICRUD;
import proteus.saldo.model.Saldo;

/**
 * Services for Saldo Model (Interface)
 * 
 * @author Juan Tzun
 */
public interface ISaldoService extends ICRUD<Saldo, Integer> {

	Saldo getByNombre(String nombre) throws Exception;
	
}
