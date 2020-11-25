package proteus.segmento.service.impl;

import java.time.LocalDate;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import proteus.generico.repository.IGenericRepository;
import proteus.generico.service.CRUDImpl;
import proteus.segmento.model.Segmento;
import proteus.segmento.model.SegmentoCredito;
import proteus.segmento.model.SegmentoCreditoDetalle;
import proteus.segmento.repository.ISegmentoCreditoRepository;
import proteus.segmento.service.ISegmentoCreditoDetalleService;
import proteus.segmento.service.ISegmentoCreditoService;
import proteus.segmento.service.ISegmentoService;

/**
 * Services for SegmentoCredito Model (Implementation)
 * 
 * @author Juan Tzun
 */
@Service
public class SegmentoCreditoServiceImpl extends CRUDImpl<SegmentoCredito, Integer> implements ISegmentoCreditoService {

	@Autowired
	private ISegmentoCreditoRepository segmentoCreditoRepository;
	
	@Autowired
	private ISegmentoCreditoDetalleService segmentoCreditoDetalleService;
	
	@Autowired
	private ISegmentoService segmentoService;

	@Override
	protected IGenericRepository<SegmentoCredito, Integer> getRepository() {
		return segmentoCreditoRepository;
	}

	@Override
	public SegmentoCredito getBySegmento(Integer idSegmento) throws Exception {
		return segmentoCreditoRepository.findBySegmento(idSegmento);
	}
	
	@Override
	public SegmentoCredito create(SegmentoCredito segmentoCredito) throws Exception {
		Segmento segmento = segmentoService.getById(segmentoCredito.getSegmento().getIdSegmento());
		if(segmento!=null) {
			SegmentoCredito segmentoCreditoAux = this.getBySegmento(segmentoCredito.getSegmento().getIdSegmento());
			if(segmentoCreditoAux==null) {
				return segmentoCreditoRepository.save(segmentoCredito);
			}
		}
		return null;
	}

	@Transactional
	@Override
	public void deleteBySegmento(Integer idSegmento) throws Exception {
		segmentoCreditoRepository.deleteBySegmento(idSegmento);
	}

	@Override
	public void updateCredito(Integer idSegmentoCredito) throws Exception {
		Double credito = 0.0;
		SegmentoCredito segmentoCredito = this.getById(idSegmentoCredito);
		if(segmentoCredito!=null) {
			List<SegmentoCreditoDetalle> segmentoCreditoDetalleList = segmentoCreditoDetalleService.getBySegmentoCredito(idSegmentoCredito);
			for(SegmentoCreditoDetalle segmentoCreditoDetalle : segmentoCreditoDetalleList) {
				credito += segmentoCreditoDetalle.getTotalRestante();
			}
			segmentoCredito.setCredito(credito);
			segmentoCreditoRepository.save(segmentoCredito);
		}
	}

	@Override
	public void updateUltimaTransaccion(Integer idSegmentoCredito, LocalDate ultimaTransaccion) throws Exception {
		SegmentoCredito segmentoCredito = this.getById(idSegmentoCredito);
		if(segmentoCredito!=null) {
			segmentoCredito.setUltimaTransaccion(ultimaTransaccion);
			segmentoCreditoRepository.save(segmentoCredito);
		}
	}
	
	@Override
	public void delete(Integer idSegmentoCredito) throws Exception {
		List<SegmentoCreditoDetalle> segmentoCreditoDetalleList = segmentoCreditoDetalleService.getBySegmentoCredito(idSegmentoCredito);
		if(segmentoCreditoDetalleList.isEmpty()) {
			segmentoCreditoRepository.deleteById(idSegmentoCredito);
		} else {
			throw new Exception("No es posible eliminar un credito de segmento que ya posee detalles");
		}
	}
	
}