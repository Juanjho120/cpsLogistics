package proteus.rol.repository;

import org.springframework.data.jpa.repository.Query;

import proteus.generico.repository.IGenericRepository;
import proteus.rol.model.RolPermisoVentana;

public interface IRolPermisoVentanaRepository extends IGenericRepository<RolPermisoVentana, Integer> {

	@Query("FROM RolPermisoVentana WHERE rol.idRol = :idRol AND permiso.idPermiso = :idPermiso AND ventana.idVentana = :idVentana")
	RolPermisoVentana findByRolAndPermisoAndVentana(Integer idRol, Integer idPermiso, Integer idVentana);
	
}
