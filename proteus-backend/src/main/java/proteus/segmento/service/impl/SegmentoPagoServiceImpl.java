package proteus.segmento.service.impl;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import proteus.cheque.model.Cheque;
import proteus.cheque.service.IChequeService;
import proteus.generico.repository.IGenericRepository;
import proteus.generico.service.CRUDImpl;
import proteus.pago.service.IPagoTipoService;
import proteus.segmento.dto.SegmentoPagoTransaccionChequeDTO;
import proteus.segmento.model.SegmentoCreditoDetalle;
import proteus.segmento.model.SegmentoPago;
import proteus.segmento.model.SegmentoPagoDetalle;
import proteus.segmento.repository.ISegmentoPagoRepository;
import proteus.segmento.service.ISegmentoCreditoDetalleService;
import proteus.segmento.service.ISegmentoCreditoService;
import proteus.segmento.service.ISegmentoPagoService;
import proteus.transaccion.model.Transaccion;
import proteus.transaccion.service.ITransaccionService;

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
	private IPagoTipoService pagoTipoService;
	
	@Autowired
	private ISegmentoCreditoDetalleService segmentoCreditoDetalleService;
	
	@Autowired
	private ISegmentoCreditoService segmentoCreditoService;
	
	@Autowired
	private ITransaccionService transaccionService;
	
	@Autowired
	private IChequeService chequeService;

	@Override
	protected IGenericRepository<SegmentoPago, Integer> getRepository() {
		return segmentoPagoRepository;
	}

	@Override
	public SegmentoPago create(SegmentoPago segmentoPago) throws Exception {
		for(SegmentoPagoDetalle segmentoPagoDetalle : segmentoPago.getSegmentoPagoDetalle()) {
			segmentoPagoDetalle.setSegmentoPago(segmentoPago);
			SegmentoCreditoDetalle segmentoCreditoDetalle = segmentoCreditoDetalleService.getById(segmentoPagoDetalle.getSegmentoCreditoDetalle().getIdSegmentoCreditoDetalle());
			if(segmentoCreditoDetalle!=null) {
				if(segmentoCreditoDetalle.getTotalRestante() > 0 && segmentoCreditoDetalle.getTotalRestante() >= segmentoPago.getMonto()) {
					//El pago disminuye el credito dado del servicio a un segmento en el detalle de su credito
					segmentoCreditoDetalle.setTotalPagado(segmentoCreditoDetalle.getTotalPagado() + segmentoPago.getMonto());
					segmentoCreditoDetalle.setTotalRestante(segmentoCreditoDetalle.getTotalRestante() - segmentoPago.getMonto());
					segmentoCreditoDetalleService.update(segmentoCreditoDetalle);
					
					//Se actualiza el credito total extendido al segmento y la fecha de la ultima transaccion
					segmentoCreditoService.updateCredito(segmentoCreditoDetalle.getSegmentoCredito().getIdSegmentoCredito());
					segmentoCreditoService.updateUltimaTransaccion(segmentoCreditoDetalle.getSegmentoCredito().getIdSegmentoCredito(), LocalDate.now());
				} else {
					throw new Exception("El monto a pagar excede el total del credito restante o ya ha sido pagado en su totalidad");
				}
			}
		}
		
		segmentoPago.setFechaHoraPago(LocalDateTime.now());
		return segmentoPagoRepository.save(segmentoPago);
	}

	@Override
	public List<SegmentoPago> getBySegmentoCreditoDetalle(Integer idSegmentoCreditoDetalle) throws Exception {
		return segmentoPagoRepository.findBySegmentoCreditoDetalle(idSegmentoCreditoDetalle);
	}
	
	@Override
	public void delete(Integer idSegmentoPago) throws Exception {
		SegmentoPago segmentoPago = this.getById(idSegmentoPago);
		segmentoPago.setPagoTipo(pagoTipoService.getById(segmentoPago.getPagoTipo().getIdPagoTipo()));
		
		if(segmentoPago.getPagoTipo().getNombre().equalsIgnoreCase("CHEQUE")) {
			Cheque cheque = chequeService.getById(segmentoPago.getIdItem());
			if(cheque!=null) {
				chequeService.delete(cheque.getIdCheque());
			}
		} else if(segmentoPago.getPagoTipo().getNombre().equalsIgnoreCase("TRANSACCION")) {
			Transaccion transaccion = transaccionService.getById(segmentoPago.getIdItem());
			if(transaccion!=null) {
				transaccionService.delete(transaccion.getIdTransaccion());
			}
		}
		Double monto = segmentoPago.getMonto();
		for(SegmentoPagoDetalle segmentoPagoDetalle : segmentoPago.getSegmentoPagoDetalle()) {
			SegmentoCreditoDetalle segmentoCreditoDetalle = segmentoCreditoDetalleService.getById(segmentoPagoDetalle.getSegmentoCreditoDetalle().getIdSegmentoCreditoDetalle());
			if(segmentoCreditoDetalle!=null) {
				//El pago disminuye el credito dado del servicio a un segmento en el detalle de su credito
				if(monto>segmentoCreditoDetalle.getTotalFacturado()) {
					segmentoCreditoDetalle.setTotalPagado(0.0);
					segmentoCreditoDetalle.setTotalRestante(segmentoCreditoDetalle.getTotalFacturado());
					monto -= segmentoCreditoDetalle.getTotalFacturado();
				} else if(monto<=segmentoCreditoDetalle.getTotalFacturado()) {
					segmentoCreditoDetalle.setTotalPagado(segmentoCreditoDetalle.getTotalPagado() - monto);
					segmentoCreditoDetalle.setTotalRestante(segmentoCreditoDetalle.getTotalRestante() + monto);
					monto = 0.0;
				}
				segmentoCreditoDetalleService.update(segmentoCreditoDetalle);
				
				//Se actualiza el credito total extendido al segmento y la fecha de la ultima transaccion
				segmentoCreditoService.updateCredito(segmentoCreditoDetalle.getSegmentoCredito().getIdSegmentoCredito());
				segmentoCreditoService.updateUltimaTransaccion(segmentoCreditoDetalle.getSegmentoCredito().getIdSegmentoCredito(), LocalDate.now());
			}
		}
		//segmentoPagoDetalleService.deleteBySegmentoPago(idSegmentoPago);
		segmentoPagoRepository.deleteById(idSegmentoPago);
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
	public void createDTO(SegmentoPagoTransaccionChequeDTO segmentoPagoDto) throws Exception {
		SegmentoPago segmentoPago = segmentoPagoDto.getSegmentoPago();
		segmentoPago.setPagoTipo(pagoTipoService.getById(segmentoPago.getPagoTipo().getIdPagoTipo()));
		
		List<SegmentoCreditoDetalle> segmentoCreditoDetalleList = new ArrayList<SegmentoCreditoDetalle>();
		for(SegmentoPagoDetalle segmentoPagoDetalle : segmentoPago.getSegmentoPagoDetalle()) {
			SegmentoCreditoDetalle segmentoCreditoDetalle = segmentoCreditoDetalleService.getById(segmentoPagoDetalle.getSegmentoCreditoDetalle().getIdSegmentoCreditoDetalle());
			segmentoCreditoDetalleList.add(segmentoCreditoDetalle);
		}
		Double totalRestante = segmentoCreditoDetalleService.sumTotalRestante(segmentoCreditoDetalleList);
		
		if(totalRestante>0 && segmentoPago.getMonto()<=totalRestante) {
			//EVALUANDO EL TIPO DE DOCUMENTO QUE RESPALDA EL PAGO REALIZADO POR EL CLIENTE
			if(segmentoPago.getPagoTipo().getNombre().equalsIgnoreCase("CHEQUE")) {
				Cheque cheque = segmentoPagoDto.getCheque();
				chequeService.create(cheque);
				segmentoPago.setIdItem(cheque.getIdCheque());
				segmentoPago.setMonto(cheque.getMonto());
			} else if(segmentoPago.getPagoTipo().getNombre().equalsIgnoreCase("TRANSACCION")) {
				Transaccion transaccion = segmentoPagoDto.getTransaccion();
				transaccionService.create(transaccion);
				segmentoPago.setIdItem(transaccion.getIdTransaccion());
				segmentoPago.setMonto(transaccion.getMonto());
			} else if(segmentoPago.getPagoTipo().getNombre().equalsIgnoreCase("EFECTIVO")) {
				segmentoPago.setIdItem(0);
			}
			
			Double monto = segmentoPago.getMonto();
			for(SegmentoPagoDetalle segmentoPagoDetalle : segmentoPago.getSegmentoPagoDetalle()) {
				segmentoPagoDetalle.setSegmentoPago(segmentoPago);
				SegmentoCreditoDetalle segmentoCreditoDetalle = segmentoCreditoDetalleService.getById(segmentoPagoDetalle.getSegmentoCreditoDetalle().getIdSegmentoCreditoDetalle());
				if(segmentoCreditoDetalle!=null) {
					if(segmentoCreditoDetalle.getTotalRestante() > 0) {
						//El pago disminuye el credito dado del servicio a un segmento en el detalle de su credito
						if(monto>segmentoCreditoDetalle.getTotalRestante()) {
							monto -= segmentoCreditoDetalle.getTotalRestante();
							segmentoCreditoDetalle.setTotalPagado(segmentoCreditoDetalle.getTotalFacturado());
							segmentoCreditoDetalle.setTotalRestante(0.0);
						} else if(monto<=segmentoCreditoDetalle.getTotalRestante()){
							segmentoCreditoDetalle.setTotalPagado(segmentoCreditoDetalle.getTotalPagado() + monto);
							segmentoCreditoDetalle.setTotalRestante(segmentoCreditoDetalle.getTotalRestante() - monto);
							monto = 0.0;
						}
						segmentoCreditoDetalleService.update(segmentoCreditoDetalle);
						
						//Se actualiza el credito total extendido al segmento y la fecha de la ultima transaccion
						segmentoCreditoService.updateCredito(segmentoCreditoDetalle.getSegmentoCredito().getIdSegmentoCredito());
						segmentoCreditoService.updateUltimaTransaccion(segmentoCreditoDetalle.getSegmentoCredito().getIdSegmentoCredito(), LocalDate.now());
					}
				}
			}
			
			segmentoPago.setFechaHoraPago(LocalDateTime.now());
			segmentoPagoRepository.save(segmentoPago);
		} else {
			throw new Exception("El monto a pagar excede el total del credito restante o ya ha sido pagado en su totalidad");
		}
	}

	@Override
	public List<SegmentoPago> getByFacturaNumeroEfectivo(String facturaNumero) throws Exception {
		return segmentoPagoRepository.findByFacturaNumeroEfectivo(facturaNumero);
	}
	
}