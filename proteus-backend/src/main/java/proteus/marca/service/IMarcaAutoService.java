package proteus.marca.service;

import proteus.generico.service.ICRUD;
import proteus.marca.model.MarcaAuto;

/**
 * Services for MarcaAuto Model (Interface)
 * 
 * @author Juan Tzun
 */
public interface IMarcaAutoService extends ICRUD<MarcaAuto, Integer> {

	MarcaAuto getByNombre(String nombre) throws Exception;
	
}
