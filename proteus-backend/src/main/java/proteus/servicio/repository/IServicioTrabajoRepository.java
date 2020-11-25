package proteus.servicio.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import proteus.generico.repository.IGenericRepository;
import proteus.servicio.model.ServicioTrabajo;

/**
 * Repository for ServicioTrabajo Model
 * 
 * @author Juan Tzun
 */
@Repository
public interface IServicioTrabajoRepository extends IGenericRepository<ServicioTrabajo, Integer> {

	@Modifying
	@Query("DELETE FROM ServicioTrabajo WHERE servicio.idServicio = :idServicio")
	void deleteByServicio(Integer idServicio);
	
	@Query("FROM ServicioTrabajo WHERE servicio.idServicio = :idServicio")
	List<ServicioTrabajo> findByServicio(Integer idServicio);
	
}
