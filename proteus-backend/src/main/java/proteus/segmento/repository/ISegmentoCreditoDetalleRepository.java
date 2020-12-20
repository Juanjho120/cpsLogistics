package proteus.segmento.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import proteus.generico.repository.IGenericRepository;
import proteus.segmento.model.SegmentoCreditoDetalle;

/**
 * Repository for SegmentoCreditoDetalle Model
 * 
 * @author Juan Tzun
 */
@Repository
public interface ISegmentoCreditoDetalleRepository extends IGenericRepository<SegmentoCreditoDetalle, Integer> {

	@Query("FROM SegmentoCreditoDetalle WHERE servicio.idServicio = :idServicio")
	SegmentoCreditoDetalle findByServicio(Integer idServicio);
	
	@Query("FROM SegmentoCreditoDetalle WHERE segmentoCredito.idSegmentoCredito = :idSegmentoCredito")
	List<SegmentoCreditoDetalle> findBySegmentoCredito(Integer idSegmentoCredito);
	
	@Query("FROM SegmentoCreditoDetalle WHERE fechaHoraEmision BETWEEN :fechaDesde AND :fechaHasta")
	List<SegmentoCreditoDetalle> findByFechaEmision(LocalDateTime fechaDesde, LocalDateTime fechaHasta);
	
	@Query("FROM SegmentoCreditoDetalle WHERE segmentoCredito.segmento.idSegmento = :idSegmento")
	List<SegmentoCreditoDetalle> findBySegmento(Integer idSegmento);
	
	@Query("FROM SegmentoCreditoDetalle WHERE segmentoCredito.segmento.idSegmento = :idSegmento AND totalRestante > 0")
	List<SegmentoCreditoDetalle> findBySegmentoSinPagar(Integer idSegmento);
	
	@Query("FROM SegmentoCreditoDetalle WHERE facturaNumero = :facturaNumero")
	SegmentoCreditoDetalle findByFacturaNumero(String facturaNumero);
	
	@Query("FROM SegmentoCreditoDetalle WHERE totalRestante > 0")
	List<SegmentoCreditoDetalle> findSaldoPendiente();
	
	@Query("FROM SegmentoCreditoDetalle WHERE totalRestante = 0")
	List<SegmentoCreditoDetalle> findPagadas();
	
}
