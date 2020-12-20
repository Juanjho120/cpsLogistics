package proteus.segmento.service;

import java.util.List;

import proteus.generico.service.ICRUD;
import proteus.segmento.dto.SegmentoCreditoDetalleDTO;
import proteus.segmento.model.SegmentoCreditoDetalle;

/**
 * Services for SegmentoCreditoDetalle Model (Interface)
 * 
 * @author Juan Tzun
 */
public interface ISegmentoCreditoDetalleService extends ICRUD<SegmentoCreditoDetalle, Integer> {

	SegmentoCreditoDetalle getByServicio(Integer idServicio) throws Exception;
	List<SegmentoCreditoDetalle> getBySegmentoCredito(Integer idSegmentoCredito) throws Exception;
	List<SegmentoCreditoDetalle> getBySegmento(Integer idSegmento) throws Exception;
	List<SegmentoCreditoDetalle> getBySegmentoSinPagar(Integer idSegmento) throws Exception;
	List<SegmentoCreditoDetalle> getByFechaEmision(String fechaDesde, String fechaHasta) throws Exception;
	List<SegmentoCreditoDetalleDTO> getDTOBySegmento(Integer idSegmento) throws Exception;
	SegmentoCreditoDetalle getByFacturaNumero(String facturaNumero) throws Exception;
	Double sumTotalRestante(List<SegmentoCreditoDetalle> segmentoCreditoDetalleList) throws Exception;
	List<SegmentoCreditoDetalle> getSaldoPendiente() throws Exception;
	List<SegmentoCreditoDetalle> getPagadas() throws Exception;
	
}
