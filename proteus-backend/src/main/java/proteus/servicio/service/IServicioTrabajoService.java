package proteus.servicio.service;

import java.util.List;

import proteus.generico.service.ICRUD;
import proteus.servicio.model.ServicioTrabajo;

/**
 * Services for ServicioTrabajo Model (Interface)
 * 
 * @author Juan Tzun
 */
public interface IServicioTrabajoService extends ICRUD<ServicioTrabajo, Integer> {

	void deleteByServicio(Integer idServicio) throws Exception;
	List<ServicioTrabajo> getByServicio(Integer idServicio) throws Exception;
	
}
