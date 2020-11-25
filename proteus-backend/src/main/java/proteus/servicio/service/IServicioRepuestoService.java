package proteus.servicio.service;

import java.util.List;

import proteus.generico.service.ICRUD;
import proteus.servicio.model.ServicioRepuesto;

/**
 * Services for ServicioRepuesto Model (Interface)
 * 
 * @author Juan Tzun
 */
public interface IServicioRepuestoService extends ICRUD<ServicioRepuesto, Integer> {

	void deleteByServicio(Integer idServicio) throws Exception;
	List<ServicioRepuesto> getByServicio(Integer idServicio) throws Exception;
	
}
