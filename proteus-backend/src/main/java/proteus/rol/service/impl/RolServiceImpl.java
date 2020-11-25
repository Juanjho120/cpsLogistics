package proteus.rol.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import proteus.generico.repository.IGenericRepository;
import proteus.generico.service.CRUDImpl;
import proteus.rol.model.Rol;
import proteus.rol.repository.IRolRepository;
import proteus.rol.service.IRolService;

/**
 * Services for Rol Model (Implementation)
 * 
 * @author Juan Tzun
 */
@Service
public class RolServiceImpl extends CRUDImpl<Rol, Integer> implements IRolService {

	@Autowired
	private IRolRepository rolRepository;

	@Override
	protected IGenericRepository<Rol, Integer> getRepository() {
		return rolRepository;
	}

	@Override
	public Rol getByNombre(String nombre) throws Exception {
		return rolRepository.findByNombre(nombre);
	}
	
	@Override
	public Rol create(Rol rol) throws Exception {
		Rol rolAux = this.getByNombre(rol.getNombre());
		if(rolAux==null) {
			return rolRepository.save(rol);
		}
		return null;
	}
	
}