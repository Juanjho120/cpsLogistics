package proteus.rol.service;

import org.springframework.stereotype.Repository;

import proteus.generico.service.ICRUD;
import proteus.rol.model.RolPermisoVentana;

/**
 * Repository for RolPermisoVentana Model
 * 
 * @author Juan Tzun
 */
@Repository
public interface IRolPermisoVentanaService extends ICRUD<RolPermisoVentana, Integer> {

	RolPermisoVentana getByRolAndPermisoAndVentana(Integer idRol, Integer idPermiso, Integer idVentana) throws Exception;
	
}
