package proteus.cotizacion.service;

import java.util.List;

import proteus.cotizacion.model.Cotizacion;
import proteus.generico.service.ICRUD;

/**
 * Services for Cotizacion Model (Interface)
 * 
 * @author Juan Tzun
 */
public interface ICotizacionService extends ICRUD<Cotizacion, Integer> {

	List<Cotizacion> getBySegmento(Integer idSegmento) throws Exception;
	List<Cotizacion> getByUsuario(Integer idUsuario) throws Exception;
	List<Cotizacion> getByFecha(String fechaDesde, String fechaHasta);
	
}
