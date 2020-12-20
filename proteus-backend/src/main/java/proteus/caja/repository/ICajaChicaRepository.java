package proteus.caja.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import proteus.caja.model.CajaChica;
import proteus.generico.repository.IGenericRepository;

/**
 * Repository for CajaChica Model
 * 
 * @author Juan Tzun
 */
@Repository
public interface ICajaChicaRepository extends IGenericRepository<CajaChica, Integer> {
	
	@Query("FROM CajaChica WHERE servicio.idServicio = :idServicio")
	List<CajaChica> findByServicio(Integer idServicio);
	
	@Query("FROM CajaChica WHERE servicio.placa.idPlaca = :idPlaca")
	List<CajaChica> findByPlaca(Integer idPlaca);
	
	@Query("FROM CajaChica WHERE fechaIngreso BETWEEN :fechaDesde AND :fechaHasta")
	List<CajaChica> findByFechaIngreso(LocalDate fechaDesde, LocalDate fechaHasta);
	
}
