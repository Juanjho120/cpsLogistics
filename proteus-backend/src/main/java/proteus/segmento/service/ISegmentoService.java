package proteus.segmento.service;

import java.util.List;

import proteus.generico.service.ICRUD;
import proteus.segmento.model.Segmento;

/**
 * Services for Segmento Model (Interface)
 * 
 * @author Juan Tzun
 */
public interface ISegmentoService extends ICRUD<Segmento, Integer> {

	Segmento getByNit(String nit) throws Exception;
	List<Segmento> getWithCredito() throws Exception;
	
}
