package proteus.cotizacion.service;

import java.util.List;

import proteus.cotizacion.model.CotizacionRepuesto;
import proteus.generico.service.ICRUD;

/**
 * Services for CotizacionRepuesto Model (Interface)
 * 
 * @author Juan Tzun
 */
public interface ICotizacionRepuestoService extends ICRUD<CotizacionRepuesto, Integer> {

	List<CotizacionRepuesto> getByCotizacion(Integer idCotizacion) throws Exception;
	void deleteByCotizacion(Integer idCotizacion) throws Exception;
	
}
