package proteus.cotizacion.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import proteus.cotizacion.model.Cotizacion;
import proteus.generico.repository.IGenericRepository;

/**
 * Repository for Cotizacion Model
 * 
 * @author Juan Tzun
 */
@Repository
public interface ICotizacionRepository extends IGenericRepository<Cotizacion, Integer> {

	@Query("FROM Cotizacion WHERE segmento.idSegmento = :idSegmento")
	List<Cotizacion> findBySegmento(Integer idSegmento);
	
	@Query("FROM Cotizacion WHERE usuario.idUsuario = :idUsuario")
	List<Cotizacion> findByUsuario(Integer idUsuario);
	
	@Query("FROM Cotizacion WHERE fechaHora BETWEEN :fechaDesde AND :fechaHasta")
	List<Cotizacion> findByFecha(LocalDateTime fechaDesde, LocalDateTime fechaHasta);
	
}
