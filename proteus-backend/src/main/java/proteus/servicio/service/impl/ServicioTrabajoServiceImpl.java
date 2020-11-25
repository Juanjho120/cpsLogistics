package proteus.servicio.service.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import proteus.generico.repository.IGenericRepository;
import proteus.generico.service.CRUDImpl;
import proteus.servicio.model.ServicioTrabajo;
import proteus.servicio.repository.IServicioTrabajoRepository;
import proteus.servicio.service.IServicioTrabajoService;

/**
 * Services for ServicioTrabajo Model (Implementation)
 * 
 * @author Juan Tzun
 */
@Service
public class ServicioTrabajoServiceImpl extends CRUDImpl<ServicioTrabajo, Integer> implements IServicioTrabajoService {

	@Autowired
	private IServicioTrabajoRepository servicioTrabajoRepository;

	@Override
	protected IGenericRepository<ServicioTrabajo, Integer> getRepository() {
		return servicioTrabajoRepository;
	}

	@Transactional
	@Override
	public void deleteByServicio(Integer idServicio) throws Exception {
		servicioTrabajoRepository.deleteByServicio(idServicio);
	}

	@Override
	public List<ServicioTrabajo> getByServicio(Integer idServicio) throws Exception {
		return servicioTrabajoRepository.findByServicio(idServicio);
	}
	
}