package proteus.servicio.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import proteus.generico.repository.IGenericRepository;
import proteus.generico.service.CRUDImpl;
import proteus.servicio.model.ServicioTipo;
import proteus.servicio.repository.IServicioTipoRepository;
import proteus.servicio.service.IServicioTipoService;

/**
 * Services for ServicioTipo Model (Implementation)
 * 
 * @author Juan Tzun
 */
@Service
public class ServicioTipoServiceImpl extends CRUDImpl<ServicioTipo, Integer> implements IServicioTipoService {

	@Autowired
	private IServicioTipoRepository servicioTipoRepository;

	@Override
	protected IGenericRepository<ServicioTipo, Integer> getRepository() {
		return servicioTipoRepository;
	}

	@Override
	public ServicioTipo getByNombre(String nombre) throws Exception {
		return servicioTipoRepository.findByNombre(nombre);
	}
	
	@Override
	public ServicioTipo create(ServicioTipo servicioTipo) throws Exception {
		ServicioTipo servicioTipoAux = this.getByNombre(servicioTipo.getNombre());
		if(servicioTipoAux==null) {
			return servicioTipoRepository.save(servicioTipo);
		}
		return null;
	}
	
	
}