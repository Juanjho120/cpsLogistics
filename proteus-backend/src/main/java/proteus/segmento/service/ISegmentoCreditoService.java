package proteus.segmento.service;

import java.time.LocalDate;

import proteus.generico.service.ICRUD;
import proteus.segmento.model.SegmentoCredito;

/**
 * Services for SegmentoCredito Model (Interface)
 * 
 * @author Juan Tzun
 */
public interface ISegmentoCreditoService extends ICRUD<SegmentoCredito, Integer> {

	SegmentoCredito getBySegmento(Integer idSegmento) throws Exception;
	void deleteBySegmento(Integer idSegmento) throws Exception;
	void updateCredito(Integer idSegmentoCredito) throws Exception;
	void updateUltimaTransaccion(Integer idSegmentoCredito, LocalDate ultimaTransaccion) throws Exception;
	
}
