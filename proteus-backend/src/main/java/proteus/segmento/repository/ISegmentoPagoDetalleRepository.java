package proteus.segmento.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import proteus.generico.repository.IGenericRepository;
import proteus.segmento.model.SegmentoPagoDetalle;

/**
 * Repository for SegmentoPagoDetalle Model
 * 
 * @author Juan Tzun
 */
@Repository
public interface ISegmentoPagoDetalleRepository extends IGenericRepository<SegmentoPagoDetalle, Integer> {
	
	@Query("FROM SegmentoPagoDetalle WHERE segmentoCreditoDetalle.idSegmentoCreditoDetalle = :idSegmentoCreditoDetalle")
	List<SegmentoPagoDetalle> findBySegmentoCreditoDetalle(Integer idSegmentoCreditoDetalle);
	
	@Transactional
	@Modifying
	@Query("DELETE FROM SegmentoPagoDetalle WHERE segmentoPago.idSegmentoPago = :idSegmentoPago")
	void deleteBySegmentoPago(Integer idSegmentoPago);
	
}
