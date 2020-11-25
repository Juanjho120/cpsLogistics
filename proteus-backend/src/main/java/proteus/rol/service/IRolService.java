package proteus.rol.service;

import proteus.generico.service.ICRUD;
import proteus.rol.model.Rol;

/**
 * Services for Rol Model (Interface)
 * 
 * @author Juan Tzun
 */
public interface IRolService extends ICRUD<Rol, Integer> {

	Rol getByNombre(String nombre) throws Exception;
	
}
