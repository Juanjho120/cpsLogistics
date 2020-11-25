package proteus.banco.service;

import proteus.banco.model.Banco;
import proteus.generico.service.ICRUD;

/**
 * Services for Banco Model (Interface)
 * 
 * @author Juan Tzun
 */
public interface IBancoService extends ICRUD<Banco, Integer> {

	Banco getByNombre(String nombre) throws Exception;
	
}
