package proteus.servicio.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import proteus.generico.repository.IGenericRepository;
import proteus.servicio.model.ServicioRepuesto;

/**
 * Repository for ServicioRepuesto Model
 * 
 * @author Juan Tzun
 */
@Repository
public interface IServicioRepuestoRepository extends IGenericRepository<ServicioRepuesto, Integer> {

	@Modifying
	@Query("DELETE FROM ServicioRepuesto WHERE servicio.idServicio = :idServicio")
	void deleteByServicio(Integer idServicio);
	
	@Query("FROM ServicioRepuesto WHERE servicio.idServicio = :idServicio")
	List<ServicioRepuesto> findByServicio(Integer idServicio);
	
}
