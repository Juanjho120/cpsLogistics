package proteus.permiso.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import proteus.generico.repository.IGenericRepository;
import proteus.generico.service.CRUDImpl;
import proteus.permiso.model.Permiso;
import proteus.permiso.repository.IPermisoRepository;
import proteus.permiso.service.IPermisoService;

/**
 * Services for Permiso Model (Implementation)
 * 
 * @author Juan Tzun
 */
@Service
public class PermisoServiceImpl extends CRUDImpl<Permiso, Integer> implements IPermisoService {

	@Autowired
	private IPermisoRepository permisoRepository;

	@Override
	protected IGenericRepository<Permiso, Integer> getRepository() {
		return permisoRepository;
	}
	
}
