package proteus.segmento.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import proteus.generico.repository.IGenericRepository;
import proteus.segmento.model.SegmentoPago;

/**
 * Repository for SegmentoPago Model
 * 
 * @author Juan Tzun
 */
@Repository
public interface ISegmentoPagoRepository extends IGenericRepository<SegmentoPago, Integer> {

	@Query("FROM SegmentoPago WHERE segmentoCreditoDetalle.idSegmentoCreditoDetalle = :idSegmentoCreditoDetalle")
	List<SegmentoPago> findBySegmentoCreditoDetalle(Integer idSegmentoCreditoDetalle);
	
	@Query("FROM SegmentoPago WHERE segmentoCreditoDetalle.segmentoCredito.segmento.idSegmento = :idSegmento")
	List<SegmentoPago> findBySegmento(Integer idSegmento);
	
	@Query("FROM SegmentoPago WHERE fechaHoraPago BETWEEN :fechaDesde AND :fechaHasta")
	List<SegmentoPago> findByFechaPago(LocalDateTime fechaDesde, LocalDateTime fechaHasta);
	
}
