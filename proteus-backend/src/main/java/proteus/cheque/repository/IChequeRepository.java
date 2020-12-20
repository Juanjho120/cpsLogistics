package proteus.cheque.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import proteus.cheque.model.Cheque;
import proteus.generico.repository.IGenericRepository;

/**
 * Repository for Cheque Model
 * 
 * @author Juan Tzun
 */
@Repository
public interface IChequeRepository extends IGenericRepository<Cheque, Integer> {

	@Query("FROM Cheque WHERE numero = :numero AND cuentaBancaria.idCuentaBancaria = :idCuentaBancaria")
	Cheque findByNumeroAndCuentaBancaria(String numero, Integer idCuentaBancaria);
	
	@Query("FROM Cheque WHERE cuentaBancaria.idCuentaBancaria = :idCuentaBancaria")
	List<Cheque> findByCuentaBancaria(Integer idCuentaBancaria);
	
	@Query("FROM Cheque WHERE cuentaBancaria.banco.idBanco = :idBanco")
	List<Cheque> findByBanco(Integer idBanco);
	
	@Query("FROM Cheque WHERE cuentaBancaria.nombre LIKE :nombre")
	List<Cheque> findByNombre(String nombre);
	
	@Query("FROM Cheque WHERE numero LIKE :numero")
	List<Cheque> findByNumero(String numero);
	
	@Query("FROM Cheque WHERE idCheque NOT IN (SELECT b.cheque.idCheque FROM Boleta b WHERE b.cheque IS NOT NULL)")
	List<Cheque> findNotInBoletas();
	
	@Query("FROM Cheque WHERE idCheque NOT IN (SELECT b.cheque.idCheque FROM Boleta b WHERE b.cheque IS NOT NULL) OR idCheque = :idCheque")
	List<Cheque> findNotInBoletasExceptCheque(Integer idCheque);
	
	@Query("FROM Cheque WHERE idCheque IN (SELECT b.cheque.idCheque FROM Boleta b WHERE b.cheque IS NOT NULL)")
	List<Cheque> findInBoletas();
	
	@Query("FROM Cheque WHERE fechaEmision BETWEEN :fechaDesde AND :fechaHasta")
	List<Cheque> findByFechaEmision(LocalDate fechaDesde, LocalDate fechaHasta);
	
	@Query("FROM Cheque WHERE fechaDeposito BETWEEN :fechaDesde AND :fechaHasta")
	List<Cheque> findByFechaDeposito(LocalDate fechaDesde, LocalDate fechaHasta);
	
	@Query("FROM Cheque c WHERE c.idCheque IN (SELECT sp.idItem FROM SegmentoPago sp "
			+ "												INNER JOIN SegmentoPagoDetalle spd ON spd.segmentoPago.idSegmentoPago = sp.idSegmentoPago "
			+ "												INNER JOIN SegmentoCreditoDetalle scd ON scd.idSegmentoCreditoDetalle = spd.segmentoCreditoDetalle.idSegmentoCreditoDetalle "
			+ "											 WHERE sp.pagoTipo.nombre = 'CHEQUE' AND scd.facturaNumero = :facturaNumero)")	
	List<Cheque> findByNumeroFacturaInSegmentoPago(String facturaNumero);
	
	@Query("FROM Cheque c WHERE c.idCheque IN (SELECT pp.idItem FROM PagoProveedor pp "
			+ "												INNER JOIN PagoProveedorDetalle ppd ON ppd.pagoProveedor.idPagoProveedor = pp.idPagoProveedor "
			+ "												INNER JOIN CreditoProveedorDetalle cpd ON cpd.idCreditoProveedorDetalle = ppd.creditoProveedorDetalle.idCreditoProveedorDetalle "
			+ "											 WHERE pp.pagoTipo.nombre = 'CHEQUE' AND cpd.facturaCompra.codigo = :codigo)")	
	List<Cheque> findByCodigoFacturaInPagoProveedor(String codigo);
	
	@Query("FROM Cheque c WHERE c.idCheque IN (SELECT sp.idItem FROM SegmentoPago sp WHERE sp.pagoTipo.nombre = 'CHEQUE' AND sp.idItem = :idCheque)")	
	Cheque findChequeInSegmentoPago(Integer idCheque);
	
	@Query("FROM Cheque c WHERE c.idCheque IN (SELECT pp.idItem FROM PagoProveedor pp WHERE pp.pagoTipo.nombre = 'CHEQUE' AND pp.idItem = :idCheque)")
	Cheque findChequeInPagoProveedor(Integer idCheque);
	
}
