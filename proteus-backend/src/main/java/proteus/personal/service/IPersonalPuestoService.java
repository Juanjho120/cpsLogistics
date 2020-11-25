package proteus.personal.service;

import proteus.generico.service.ICRUD;
import proteus.personal.model.PersonalPuesto;

/**
 * Services for PersonalPuesto Model (Interface)
 * 
 * @author Juan Tzun
 */
public interface IPersonalPuestoService extends ICRUD<PersonalPuesto, Integer> {

	PersonalPuesto getByNombre(String nombre) throws Exception;
	
}
