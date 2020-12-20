package proteus.comprobante.service;

import proteus.comprobante.model.ComprobanteTipo;
import proteus.generico.service.ICRUD;

/**
 * Services for ComprobanteTipo Model (Interface)
 * 
 * @author Juan Tzun
 */
public interface IComprobanteTipoService extends ICRUD<ComprobanteTipo, Integer> {

	ComprobanteTipo getByNombre(String nombre) throws Exception;
	
}
