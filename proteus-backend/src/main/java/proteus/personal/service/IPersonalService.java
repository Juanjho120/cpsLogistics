package proteus.personal.service;

import java.util.List;

import proteus.generico.service.ICRUD;
import proteus.personal.model.Personal;

/**
 * Services for Personal Model (Interface)
 * 
 * @author Juan Tzun
 */
public interface IPersonalService extends ICRUD<Personal, Integer> {

	Personal getByTelefono(String telefono) throws Exception;
	List<Personal> getByPersonalPuesto(Integer idPersonalPuesto) throws Exception;
	
}
