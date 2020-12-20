package proteus.permiso.repository;

import org.springframework.stereotype.Repository;

import proteus.generico.repository.IGenericRepository;
import proteus.permiso.model.Permiso;

/**
 * Repository for Permiso Model
 * 
 * @author Juan Tzun
 */
@Repository
public interface IPermisoRepository extends IGenericRepository<Permiso, Integer> {

}
