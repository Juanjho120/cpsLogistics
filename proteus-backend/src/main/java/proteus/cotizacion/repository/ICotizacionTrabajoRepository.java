package proteus.cotizacion.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import proteus.cotizacion.model.CotizacionTrabajo;
import proteus.generico.repository.IGenericRepository;

/**
 * Repository for CotizacionTrabajo Model
 * 
 * @author Juan Tzun
 */
@Repository
public interface ICotizacionTrabajoRepository extends IGenericRepository<CotizacionTrabajo, Integer> {

	@Query("FROM CotizacionTrabajo WHERE cotizacion.idCotizacion = :idCotizacion")
	List<CotizacionTrabajo> findByCotizacion(Integer idCotizacion);
	
	@Modifying
	@Query("DELETE FROM CotizacionTrabajo WHERE cotizacion.idCotizacion = :idCotizacion")
	void deleteByCotizacion(Integer idCotizacion);
	
}
