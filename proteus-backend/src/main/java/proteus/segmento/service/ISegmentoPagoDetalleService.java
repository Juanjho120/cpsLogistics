package proteus.segmento.service;

import java.util.List;

import proteus.generico.service.ICRUD;
import proteus.segmento.model.SegmentoPagoDetalle;

/**
 * Services for SegmentoPagoDetalle Model (Interface)
 * 
 * @author Juan Tzun
 */
public interface ISegmentoPagoDetalleService extends ICRUD<SegmentoPagoDetalle, Integer> {

	List<SegmentoPagoDetalle> getBySegmentoCreditoDetalle(Integer idSegmentoCreditoDetalle) throws Exception;
	void deleteBySegmentoPago(Integer idSegmentoPago) throws Exception;
	
}
