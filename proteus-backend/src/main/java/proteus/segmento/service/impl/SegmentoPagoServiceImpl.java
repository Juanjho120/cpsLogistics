package proteus.segmento.service.impl;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import proteus.generico.repository.IGenericRepository;
import proteus.generico.service.CRUDImpl;
import proteus.segmento.dto.SegmentoPagoDTO;
import proteus.segmento.model.SegmentoCreditoDetalle;
import proteus.segmento.model.SegmentoPago;
import proteus.segmento.repository.ISegmentoPagoRepository;
import proteus.segmento.service.ISegmentoCreditoDetalleService;
import proteus.segmento.service.ISegmentoCreditoService;
import proteus.segmento.service.ISegmentoPagoService;

/**
 * Services for SegmentoPago Model (Implementation)
 * 
 * @author Juan Tzun
 */
@Service
public class SegmentoPagoServiceImpl extends CRUDImpl<SegmentoPago, Integer> implements ISegmentoPagoService {

	@Autowired
	private ISegmentoPagoRepository segmentoPagoRepository;
	
	@Autowired
	private ISegmentoCreditoDetalleService segmentoCreditoDetalleService;
	
	@Autowired
	private ISegmentoCreditoService segmentoCreditoService;

	@Override
	protected IGenericRepository<SegmentoPago, Integer> getRepository() {
		return segmentoPagoRepository;
	}

	@Override
	public SegmentoPago create(SegmentoPago segmentoPago) throws Exception {
		SegmentoCreditoDetalle segmentoCreditoDetalle = segmentoCreditoDetalleService.getById(segmentoPago.getSegmentoCreditoDetalle().getIdSegmentoCreditoDetalle());
		if(segmentoCreditoDetalle!=null) {
			if(segmentoCreditoDetalle.getTotalRestante() > 0 && segmentoCreditoDetalle.getTotalRestante() >= segmentoPago.getMonto()) {
				//El pago disminuye el credito dado del servicio a un segmento en el detalle de su credito
				segmentoCreditoDetalle.setTotalPagado(segmentoCreditoDetalle.getTotalPagado() + segmentoPago.getMonto());
				segmentoCreditoDetalle.setTotalRestante(segmentoCreditoDetalle.getTotalRestante() - segmentoPago.getMonto());
				segmentoCreditoDetalleService.update(segmentoCreditoDetalle);
				
				//Se actualiza el credito total extendido al segmento y la fecha de la ultima transaccion
				segmentoCreditoService.updateCredito(segmentoCreditoDetalle.getSegmentoCredito().getIdSegmentoCredito());
				segmentoCreditoService.updateUltimaTransaccion(segmentoCreditoDetalle.getSegmentoCredito().getIdSegmentoCredito(), LocalDate.now());
				
				segmentoPago.setFechaHoraPago(LocalDateTime.now());
				return segmentoPagoRepository.save(segmentoPago);
			} else {
				throw new Exception("El monto a pagar excede el total del credito restante o ya ha sido pagado en su totalidad");
			}
		}
		return null;
	}

	@Override
	public List<SegmentoPago> getBySegmentoCreditoDetalle(Integer idSegmentoCreditoDetalle) throws Exception {
		return segmentoPagoRepository.findBySegmentoCreditoDetalle(idSegmentoCreditoDetalle);
	}
	
	@Override
	public void delete(Integer idSegmentoPago) throws Exception {
		SegmentoPago segmentoPago = this.getById(idSegmentoPago);
		SegmentoCreditoDetalle segmentoCreditoDetalle = segmentoCreditoDetalleService.getById(segmentoPago.getSegmentoCreditoDetalle().getIdSegmentoCreditoDetalle());
		if(segmentoCreditoDetalle!=null) {
			//El pago disminuye el credito dado del servicio a un segmento en el detalle de su credito
			segmentoCreditoDetalle.setTotalPagado(segmentoCreditoDetalle.getTotalPagado() - segmentoPago.getMonto());
			segmentoCreditoDetalle.setTotalRestante(segmentoCreditoDetalle.getTotalRestante() + segmentoPago.getMonto());
			segmentoCreditoDetalleService.update(segmentoCreditoDetalle);
			
			//Se actualiza el credito total extendido al segmento y la fecha de la ultima transaccion
			segmentoCreditoService.updateCredito(segmentoCreditoDetalle.getSegmentoCredito().getIdSegmentoCredito());
			segmentoCreditoService.updateUltimaTransaccion(segmentoCreditoDetalle.getSegmentoCredito().getIdSegmentoCredito(), LocalDate.now());
			
			segmentoPagoRepository.deleteById(idSegmentoPago);
		}
	}

	@Override
	public List<SegmentoPago> getBySegmento(Integer idSegmento) throws Exception {
		return segmentoPagoRepository.findBySegmento(idSegmento);
	}

	@Override
	public List<SegmentoPago> getByFechaPago(String fechaDesde, String fechaHasta) {
		//Convirtiendo cadena de texto a tipo de fecha LocalDateTime
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		
		LocalDateTime fechaDesdeParse = LocalDateTime.parse(fechaDesde, formatter);
		LocalDateTime fechaHastaParse = LocalDateTime.parse(fechaHasta, formatter);
		
		return segmentoPagoRepository.findByFechaPago(fechaDesdeParse, fechaHastaParse.plusDays(1));
	}

	@Override
	public List<SegmentoPagoDTO> getDTOBySegmento(Integer idSegmento) throws Exception {
		List<SegmentoPagoDTO> segmentoPagoDTOList = new ArrayList<SegmentoPagoDTO>();
		List<SegmentoPago> segmentoPagoList = this.getBySegmento(idSegmento);
		for(SegmentoPago segmentoPago : segmentoPagoList) {
			SegmentoPagoDTO segmentoPagoDTO = new SegmentoPagoDTO();
			segmentoPagoDTO.setIdSegmentoPago(segmentoPago.getIdSegmentoPago());
			segmentoPagoDTO.setIdServicio(segmentoPago.getSegmentoCreditoDetalle().getServicio().getIdServicio());
			segmentoPagoDTO.setPlacaNumero(segmentoPago.getSegmentoCreditoDetalle().getServicio().getPlaca().getNumero());
			segmentoPagoDTO.setFechaPago(segmentoPago.getFechaHoraPago().toLocalDate().toString());
			segmentoPagoDTO.setMonto("Q. "+String.format("%.2f", segmentoPago.getMonto()));
			segmentoPagoDTOList.add(segmentoPagoDTO);
		}
		return segmentoPagoDTOList;
	}
	
}