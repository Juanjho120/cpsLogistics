package proteus.segmento.service.impl;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import proteus.generico.repository.IGenericRepository;
import proteus.generico.service.CRUDImpl;
import proteus.segmento.dto.SegmentoCreditoDetalleDTO;
import proteus.segmento.model.SegmentoCredito;
import proteus.segmento.model.SegmentoCreditoDetalle;
import proteus.segmento.model.SegmentoPago;
import proteus.segmento.repository.ISegmentoCreditoDetalleRepository;
import proteus.segmento.service.ISegmentoCreditoDetalleService;
import proteus.segmento.service.ISegmentoCreditoService;
import proteus.segmento.service.ISegmentoPagoService;
import proteus.servicio.model.Servicio;
import proteus.servicio.service.IServicioService;

/**
 * Services for SegmentoCreditoDetalle Model (Implementation)
 * 
 * @author Juan Tzun
 */
@Service
public class SegmentoCreditoDetalleServiceImpl extends CRUDImpl<SegmentoCreditoDetalle, Integer> implements ISegmentoCreditoDetalleService {

	@Autowired
	private ISegmentoCreditoDetalleRepository segmentoCreditoDetalleRepository;
	
	@Autowired
	private ISegmentoPagoService segmentoPagoService;
	
	@Autowired
	private IServicioService servicioService;
	
	@Autowired
	private ISegmentoCreditoService segmentoCreditoService;

	@Override
	protected IGenericRepository<SegmentoCreditoDetalle, Integer> getRepository() {
		return segmentoCreditoDetalleRepository;
	}
	
	@Override
	public SegmentoCreditoDetalle create(SegmentoCreditoDetalle segmentoCreditoDetalle) throws Exception {
		SegmentoCreditoDetalle segmentoCreditoDetalleAux = this.getByServicio(segmentoCreditoDetalle.getServicio().getIdServicio());
		if(segmentoCreditoDetalleAux==null) {
			Double totalFacturado = 0.0;
			
			//Busco los valores del servicio
			Servicio servicio = servicioService.getById(segmentoCreditoDetalle.getServicio().getIdServicio());
			
			//Le asigno el total facturado del servicio al detalle de credito
			totalFacturado = servicio.getCostoTotal();
			
			//Obtengo el credito del segmento a traves del segmento que esta asignado al servicio
			SegmentoCredito segmentoCredito = segmentoCreditoService.getBySegmento(servicio.getSegmento().getIdSegmento());
			segmentoCreditoDetalle.setSegmentoCredito(segmentoCredito);
			
			//El total facturado y el restante es el mismo que el del servicio
			//Aun no se ha pagado nada al momento de crearse
			segmentoCreditoDetalle.setTotalFacturado(totalFacturado);
			segmentoCreditoDetalle.setTotalRestante(totalFacturado);
			segmentoCreditoDetalle.setTotalPagado(0.0);
			segmentoCreditoDetalle.setFechaHoraEmision(LocalDateTime.now());
			
			servicioService.updateFacturado(servicio.getIdServicio(), true);
			segmentoCreditoDetalle =  segmentoCreditoDetalleRepository.save(segmentoCreditoDetalle);
			segmentoCreditoService.updateCredito(segmentoCredito.getIdSegmentoCredito());
			return segmentoCreditoDetalle;
		}
		return null;
	}

	@Override
	public SegmentoCreditoDetalle getByServicio(Integer idServicio) throws Exception {
		return segmentoCreditoDetalleRepository.findByServicio(idServicio);
	}

	@Override
	public List<SegmentoCreditoDetalle> getBySegmentoCredito(Integer idSegmentoCredito) throws Exception {
		return segmentoCreditoDetalleRepository.findBySegmentoCredito(idSegmentoCredito);
	}

	@Override
	public List<SegmentoCreditoDetalle> getBySegmento(Integer idSegmento) throws Exception {
		return segmentoCreditoDetalleRepository.findBySegmento(idSegmento);
	}

	@Override
	public List<SegmentoCreditoDetalle> getByFechaEmision(String fechaDesde, String fechaHasta) throws Exception {
		//Convirtiendo cadena de texto a tipo de fecha LocalDateTime
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		
		LocalDateTime fechaDesdeParse = LocalDateTime.parse(fechaDesde, formatter);
		LocalDateTime fechaHastaParse = LocalDateTime.parse(fechaHasta, formatter);
		
		return segmentoCreditoDetalleRepository.findByFechaEmision(fechaDesdeParse, fechaHastaParse.plusDays(1));
	}
	
	//No deberia dejar eliminar si ya existen pagos
	@Override
	public void delete(Integer idSegmentoCreditoDetalle) throws Exception {
		List<SegmentoPago> segmentoPagoList = segmentoPagoService.getBySegmentoCreditoDetalle(idSegmentoCreditoDetalle);
		if(segmentoPagoList.isEmpty()) {
			SegmentoCreditoDetalle segmentoCreditoDetalle = this.getById(idSegmentoCreditoDetalle);
			
			//Cambia el estado de facturacion del servicio
			servicioService.updateFacturado(segmentoCreditoDetalle.getServicio().getIdServicio(), false);
			
			//Borra el detalle de credito del segmento
			segmentoCreditoDetalleRepository.deleteById(idSegmentoCreditoDetalle);
			
			//Actualiza el credito del segmento
			segmentoCreditoService.updateCredito(segmentoCreditoDetalle.getSegmentoCredito().getIdSegmentoCredito());
		} else {
			throw new Exception("No es posible eliminar detalles de credito que tienen pagos asociados");
		}
		
	}

	@Override
	public List<SegmentoCreditoDetalleDTO> getDTOBySegmento(Integer idSegmento) throws Exception {
		List<SegmentoCreditoDetalleDTO> segmentoCreditoDetalleDTOList = new ArrayList<SegmentoCreditoDetalleDTO>();
		List<SegmentoCreditoDetalle> segmentoCreditoDetalleList = this.getBySegmento(idSegmento);
		for(SegmentoCreditoDetalle segmentoCreditoDetalle : segmentoCreditoDetalleList) {
			if(segmentoCreditoDetalle.getTotalRestante()>0) {
				SegmentoCreditoDetalleDTO segmentoCreditoDetalleDTO = new SegmentoCreditoDetalleDTO();
				segmentoCreditoDetalleDTO.setIdSegmentoCreditoDetalle(segmentoCreditoDetalle.getIdSegmentoCreditoDetalle());
				segmentoCreditoDetalleDTO.setIdServicio(segmentoCreditoDetalle.getServicio().getIdServicio());
				segmentoCreditoDetalleDTO.setPlacaNumero(segmentoCreditoDetalle.getServicio().getPlaca().getNumero());
				segmentoCreditoDetalleDTO.setFechaEmision(segmentoCreditoDetalle.getFechaHoraEmision().toLocalDate().toString());
				segmentoCreditoDetalleDTO.setTotalFacturado("Q. "+String.format("%.2f", segmentoCreditoDetalle.getTotalFacturado()));
				segmentoCreditoDetalleDTO.setTotalPagado("Q. "+String.format("%.2f", segmentoCreditoDetalle.getTotalPagado()));
				segmentoCreditoDetalleDTO.setTotalRestante("Q. "+String.format("%.2f", segmentoCreditoDetalle.getTotalRestante()));
				
				segmentoCreditoDetalleDTOList.add(segmentoCreditoDetalleDTO);
			}
		}
		return segmentoCreditoDetalleDTOList;
	}
}