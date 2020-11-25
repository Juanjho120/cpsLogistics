package proteus.rol.repository;

import org.springframework.stereotype.Repository;

import proteus.generico.repository.IGenericRepository;
import proteus.rol.model.Rol;

/**
 * Repository for Rol Model
 * 
 * @author Juan Tzun
 */
@Repository
public interface IRolRepository extends IGenericRepository<Rol, Integer> {

	Rol findByNombre(String nombre);
	
}
