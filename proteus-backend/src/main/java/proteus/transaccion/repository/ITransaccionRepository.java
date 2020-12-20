package proteus.transaccion.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import proteus.generico.repository.IGenericRepository;
import proteus.transaccion.model.Transaccion;

/**
 * Repository for Transaccion Model
 * 
 * @author Juan Tzun
 */
@Repository
public interface ITransaccionRepository extends IGenericRepository<Transaccion, Integer> {

	@Query("FROM Transaccion WHERE referencia = :referencia")
	Transaccion findByReferencia(String referencia);
	
	@Query("FROM Transaccion WHERE fechaAprobacion BETWEEN :fechaDesde AND :fechaHasta")
	List<Transaccion> findByFechaAprobacion(LocalDate fechaDesde, LocalDate fechaHasta);
	
	@Query("FROM Transaccion t WHERE t.idTransaccion IN (SELECT sp.idItem FROM SegmentoPago sp "
			+ "												INNER JOIN SegmentoPagoDetalle spd ON spd.segmentoPago.idSegmentoPago = sp.idSegmentoPago "
			+ "												INNER JOIN SegmentoCreditoDetalle scd ON scd.idSegmentoCreditoDetalle = spd.segmentoCreditoDetalle.idSegmentoCreditoDetalle "
			+ "											 WHERE sp.pagoTipo.nombre = 'TRANSACCION' AND scd.facturaNumero = :facturaNumero)")	
	List<Transaccion> findByNumeroFacturaInSegmentoPago(String facturaNumero);
	
}
