package proteus.proveedor.service;

import proteus.generico.service.ICRUD;
import proteus.proveedor.model.ProveedorMenor;

/**
 * Services for ProveedorMenor Model (Interface)
 * 
 * @author Juan Tzun
 */
public interface IProveedorMenorService extends ICRUD<ProveedorMenor, Integer> {

	ProveedorMenor getByNombre(String nombre) throws Exception;
	
}
