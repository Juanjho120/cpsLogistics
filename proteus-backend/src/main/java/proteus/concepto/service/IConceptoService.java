package proteus.concepto.service;

import proteus.concepto.model.Concepto;
import proteus.generico.service.ICRUD;

/**
 * Services for Concepto Model (Interface)
 * 
 * @author Juan Tzun
 */
public interface IConceptoService extends ICRUD<Concepto, Integer> {

	Concepto getByNombre(String nombre) throws Exception;
	
}
