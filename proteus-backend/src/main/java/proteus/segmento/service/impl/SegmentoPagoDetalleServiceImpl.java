package proteus.segmento.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import proteus.generico.repository.IGenericRepository;
import proteus.generico.service.CRUDImpl;
import proteus.segmento.model.SegmentoPagoDetalle;
import proteus.segmento.repository.ISegmentoPagoDetalleRepository;
import proteus.segmento.service.ISegmentoPagoDetalleService;

/**
 * Services for SegmentoPagoDetalle Model (Implementation)
 * 
 * @author Juan Tzun
 */
@Service
public class SegmentoPagoDetalleServiceImpl extends CRUDImpl<SegmentoPagoDetalle, Integer> implements ISegmentoPagoDetalleService {

	@Autowired
	private ISegmentoPagoDetalleRepository segmentoPagoDetalleRepository;

	@Override
	protected IGenericRepository<SegmentoPagoDetalle, Integer> getRepository() {
		return segmentoPagoDetalleRepository;
	}

	@Override
	public List<SegmentoPagoDetalle> getBySegmentoCreditoDetalle(Integer idSegmentoCreditoDetalle) throws Exception {
		return segmentoPagoDetalleRepository.findBySegmentoCreditoDetalle(idSegmentoCreditoDetalle);
	}

	@Override
	public void deleteBySegmentoPago(Integer idSegmentoPago) throws Exception {
		segmentoPagoDetalleRepository.deleteBySegmentoPago(idSegmentoPago);
	}
	
	
}
