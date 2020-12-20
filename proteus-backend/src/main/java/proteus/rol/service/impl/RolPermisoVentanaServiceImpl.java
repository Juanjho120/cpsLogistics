package proteus.rol.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import proteus.generico.repository.IGenericRepository;
import proteus.generico.service.CRUDImpl;
import proteus.rol.model.RolPermisoVentana;
import proteus.rol.repository.IRolPermisoVentanaRepository;
import proteus.rol.service.IRolPermisoVentanaService;

/**
 * Services for RolPermisoVentana Model (Implementation)
 * 
 * @author Juan Tzun
 */
@Service
public class RolPermisoVentanaServiceImpl extends CRUDImpl<RolPermisoVentana, Integer> implements IRolPermisoVentanaService {

	@Autowired
	private IRolPermisoVentanaRepository rolPermisoVentanaRepository;

	@Override
	protected IGenericRepository<RolPermisoVentana, Integer> getRepository() {
		return rolPermisoVentanaRepository;
	}

	@Override
	public RolPermisoVentana getByRolAndPermisoAndVentana(Integer idRol, Integer idPermiso, Integer idVentana)
			throws Exception {
		return rolPermisoVentanaRepository.findByRolAndPermisoAndVentana(idRol, idPermiso, idVentana);
	}
	
	@Override
	public RolPermisoVentana create(RolPermisoVentana rolPermisoVentana) throws Exception {
		RolPermisoVentana rolPermisoVentanaAux = this.getByRolAndPermisoAndVentana(rolPermisoVentana.getRol().getIdRol(), 
				rolPermisoVentana.getPermiso().getIdPermiso(), rolPermisoVentana.getVentana().getIdVentana());
		if(rolPermisoVentanaAux==null) {
			return rolPermisoVentanaRepository.save(rolPermisoVentana);
		}
		return null;
	}
	
}
