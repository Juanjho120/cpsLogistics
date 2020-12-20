package proteus.pago.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import proteus.generico.repository.IGenericRepository;
import proteus.pago.model.PagoProveedor;

/**
 * Repository for PagoProveedor Model
 * 
 * @author Juan Tzun
 */
@Repository
public interface IPagoProveedorRepository extends IGenericRepository<PagoProveedor, Integer> {
	
	@Query("SELECT ppd.pagoProveedor FROM PagoProveedorDetalle ppd WHERE ppd.creditoProveedorDetalle.creditoProveedor.proveedor.idProveedor = :idProveedor")
	List<PagoProveedor> findByProveedor(Integer idProveedor);
	
	@Query("FROM PagoProveedor WHERE fechaHoraPago BETWEEN :fechaDesde AND :fechaHasta")
	List<PagoProveedor> findByFechaPago(LocalDateTime fechaDesde, LocalDateTime fechaHasta);
	
	@Query("SELECT DISTINCT ppd.pagoProveedor FROM PagoProveedorDetalle ppd WHERE ppd.creditoProveedorDetalle.creditoProveedor.idCreditoProveedor = :idCreditoProveedor")
	List<PagoProveedor> findByCreditoProveedor(Integer idCreditoProveedor);
	
}
