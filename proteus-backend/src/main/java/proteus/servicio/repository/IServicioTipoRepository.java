package proteus.servicio.repository;

import org.springframework.stereotype.Repository;

import proteus.generico.repository.IGenericRepository;
import proteus.servicio.model.ServicioTipo;

/**
 * Repository for ServicioTipo Model
 * 
 * @author Juan Tzun
 */
@Repository
public interface IServicioTipoRepository extends IGenericRepository<ServicioTipo, Integer> {

	ServicioTipo findByNombre(String nombre);
	
}
