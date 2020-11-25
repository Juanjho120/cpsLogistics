package proteus.servicio.service;

import proteus.generico.service.ICRUD;
import proteus.servicio.model.ServicioTipo;

/**
 * Services for ServicioTipo Model (Interface)
 * 
 * @author Juan Tzun
 */
public interface IServicioTipoService extends ICRUD<ServicioTipo, Integer> {

	ServicioTipo getByNombre(String nombre) throws Exception;
	
}
