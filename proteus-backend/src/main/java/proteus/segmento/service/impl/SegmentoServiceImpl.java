package proteus.segmento.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import proteus.generico.repository.IGenericRepository;
import proteus.generico.service.CRUDImpl;
import proteus.segmento.model.Segmento;
import proteus.segmento.repository.ISegmentoRepository;
import proteus.segmento.service.ISegmentoService;

/**
 * Services for Segmento Model (Implementation)
 * 
 * @author Juan Tzun
 */
@Service
public class SegmentoServiceImpl extends CRUDImpl<Segmento, Integer> implements ISegmentoService {

	@Autowired
	private ISegmentoRepository segmentoRepository;

	@Override
	protected IGenericRepository<Segmento, Integer> getRepository() {
		return segmentoRepository;
	}

	@Override
	public Segmento getByNit(String nit) throws Exception {
		return segmentoRepository.findByNit(nit);
	}
	
	@Override
	public Segmento create(Segmento segmento) throws Exception {
		Segmento segmentoAux = this.getByNit(segmento.getNit());
		if(segmentoAux==null) {
			return segmentoRepository.save(segmento);
		}
		return null;
	}

	@Override
	public List<Segmento> getWithCredito() throws Exception {
		return segmentoRepository.findWithCredito();
	}
	
}