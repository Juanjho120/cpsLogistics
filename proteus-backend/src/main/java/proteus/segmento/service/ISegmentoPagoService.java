package proteus.segmento.service;

import java.util.List;

import proteus.generico.service.ICRUD;
import proteus.segmento.dto.SegmentoPagoTransaccionChequeDTO;
import proteus.segmento.model.SegmentoPago;

/**
 * Services for SegmentoPago Model (Interface)
 * 
 * @author Juan Tzun
 */
public interface ISegmentoPagoService extends ICRUD<SegmentoPago, Integer> {

	List<SegmentoPago> getBySegmentoCreditoDetalle(Integer idSegmentoCreditoDetalle) throws Exception;
	List<SegmentoPago> getBySegmento(Integer idSegmento) throws Exception;
	List<SegmentoPago> getByFechaPago(String fechaDesde, String fechaHasta);
	void createDTO(SegmentoPagoTransaccionChequeDTO segmentoPagoDto) throws Exception;
	List<SegmentoPago> getByFacturaNumeroEfectivo(String facturaNumero) throws Exception;
	
}
