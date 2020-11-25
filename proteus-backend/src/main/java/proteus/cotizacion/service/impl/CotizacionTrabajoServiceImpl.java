package proteus.cotizacion.service.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import proteus.cotizacion.model.CotizacionTrabajo;
import proteus.cotizacion.repository.ICotizacionTrabajoRepository;
import proteus.cotizacion.service.ICotizacionTrabajoService;
import proteus.generico.repository.IGenericRepository;
import proteus.generico.service.CRUDImpl;

/**
 * Services for CotizacionTrabajo Model (Implementation)
 * 
 * @author Juan Tzun
 */
@Service
public class CotizacionTrabajoServiceImpl extends CRUDImpl<CotizacionTrabajo, Integer> implements ICotizacionTrabajoService {

	@Autowired
	private ICotizacionTrabajoRepository cotizacionTrabajoRepository;

	@Override
	protected IGenericRepository<CotizacionTrabajo, Integer> getRepository() {
		return cotizacionTrabajoRepository;
	}

	@Override
	public List<CotizacionTrabajo> getByCotizacion(Integer idCotizacion) throws Exception {
		return cotizacionTrabajoRepository.findByCotizacion(idCotizacion);
	}

	@Transactional
	@Override
	public void deleteByCotizacion(Integer idCotizacion) throws Exception {
		cotizacionTrabajoRepository.deleteByCotizacion(idCotizacion);
	}
	
}