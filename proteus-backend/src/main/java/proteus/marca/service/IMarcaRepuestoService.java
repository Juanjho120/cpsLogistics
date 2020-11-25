package proteus.marca.service;

import proteus.generico.service.ICRUD;
import proteus.marca.model.MarcaRepuesto;

/**
 * Services for MarcaRepuesto Model (Interface)
 * 
 * @author Juan Tzun
 */
public interface IMarcaRepuestoService extends ICRUD<MarcaRepuesto, Integer> {

	MarcaRepuesto getByNombre(String nombre) throws Exception;
	
}
