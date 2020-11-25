package proteus.servicio.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import proteus.generico.repository.IGenericRepository;
import proteus.servicio.model.Servicio;

/**
 * Repository for Servicio Model
 * 
 * @author Juan Tzun
 */
@Repository
public interface IServicioRepository extends IGenericRepository<Servicio, Integer> {

	@Query("FROM Servicio WHERE placa.idPlaca = :idPlaca")
	List<Servicio> findByPlaca(Integer idPlaca);
	
	@Query("FROM Servicio WHERE finalizado = :finalizado")
	List<Servicio> findByFinalizado(Boolean finalizado);
	
	@Query("FROM Servicio WHERE facturado = :facturado")
	List<Servicio> findByFacturado(Boolean facturado);
	
	@Query("FROM Servicio WHERE segmento.idSegmento = :idSegmento")
	List<Servicio> findBySegmento(Integer idSegmento);
	
	@Query("FROM Servicio WHERE fechaHora BETWEEN :fechaDesde AND :fechaHasta")
	List<Servicio> findByFechaRango(LocalDateTime fechaDesde, LocalDateTime fechaHasta);
	
	@Query("FROM Servicio WHERE placa.idPlaca = :idPlaca AND finalizado = :finalizado")
	List<Servicio> findByPlacaAndFinalizado(Integer idPlaca, Boolean finalizado);
	
	@Query("FROM Servicio WHERE placa.idPlaca = :idPlaca AND facturado = :facturado")
	List<Servicio> findByPlacaAndFacturado(Integer idPlaca, Boolean facturado);
	
	@Query("FROM Servicio WHERE placa.idPlaca = :idPlaca AND finalizado = :finalizado AND facturado = :facturado")
	List<Servicio> findByPlacaAndFinalizadoAndFacturado(Integer idPlaca, Boolean finalizado, Boolean facturado);
	
	@Query("FROM Servicio WHERE segmento.idSegmento = :idSegmento AND finalizado = :finalizado")
	List<Servicio> findBySegmentoAndFinalizado(Integer idSegmento, Boolean finalizado);
	
	@Query("FROM Servicio WHERE segmento.idSegmento = :idSegmento AND facturado = :facturado")
	List<Servicio> findBySegmentoAndFacturado(Integer idSegmento, Boolean facturado);
	
	@Query("FROM Servicio WHERE segmento.idSegmento = :idSegmento AND finalizado = :finalizado AND facturado = :facturado")
	List<Servicio> findBySegmentoAndFinalizadoAndFacturado(Integer idSegmento, Boolean finalizado, Boolean facturado);
	
	@Query("FROM Servicio WHERE segmento.idSegmento = :idSegmento AND placa.idPlaca = :idPlaca")
	List<Servicio> findBySegmentoAndPlaca(Integer idSegmento, Integer idPlaca);
	
	@Query("FROM Servicio WHERE servicioTipo.idServicioTipo = :idServicioTipo")
	List<Servicio> findByServicioTipo(Integer idServicioTipo);
	
	@Query("FROM Servicio WHERE cotizacion.idCotizacion = :idCotizacion")
	List<Servicio> findByCotizacion(Integer idCotizacion);
	
}
