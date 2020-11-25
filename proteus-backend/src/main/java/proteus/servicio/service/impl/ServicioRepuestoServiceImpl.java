package proteus.servicio.service.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import proteus.generico.repository.IGenericRepository;
import proteus.generico.service.CRUDImpl;
import proteus.servicio.model.ServicioRepuesto;
import proteus.servicio.repository.IServicioRepuestoRepository;
import proteus.servicio.service.IServicioRepuestoService;

/**
 * Services for ServicioRepuesto Model (Implementation)
 * 
 * @author Juan Tzun
 */
@Service
public class ServicioRepuestoServiceImpl extends CRUDImpl<ServicioRepuesto, Integer> implements IServicioRepuestoService {

	@Autowired
	private IServicioRepuestoRepository servicioRepuestoRepository;

	@Override
	protected IGenericRepository<ServicioRepuesto, Integer> getRepository() {
		return servicioRepuestoRepository;
	}

	@Transactional
	@Override
	public void deleteByServicio(Integer idServicio) throws Exception {
		servicioRepuestoRepository.deleteByServicio(idServicio);
	}

	@Override
	public List<ServicioRepuesto> getByServicio(Integer idServicio) throws Exception {
		return servicioRepuestoRepository.findByServicio(idServicio);
	}
	
}