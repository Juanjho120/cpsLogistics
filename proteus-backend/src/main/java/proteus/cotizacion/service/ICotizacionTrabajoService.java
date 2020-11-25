package proteus.cotizacion.service;

import java.util.List;

import proteus.cotizacion.model.CotizacionTrabajo;
import proteus.generico.service.ICRUD;

/**
 * Services for CotizacionTrabajo Model (Interface)
 * 
 * @author Juan Tzun
 */
public interface ICotizacionTrabajoService extends ICRUD<CotizacionTrabajo, Integer> {

	List<CotizacionTrabajo> getByCotizacion(Integer idCotizacion) throws Exception;
	void deleteByCotizacion(Integer idCotizacion) throws Exception;
	
}
