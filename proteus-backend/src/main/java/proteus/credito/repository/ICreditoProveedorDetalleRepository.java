package proteus.credito.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import proteus.credito.model.CreditoProveedorDetalle;
import proteus.generico.repository.IGenericRepository;

/**
 * Repository for CreditoProveedorDetalle Model
 * 
 * @author Juan Tzun
 */
@Repository
public interface ICreditoProveedorDetalleRepository extends IGenericRepository<CreditoProveedorDetalle, Integer> {

	@Query("FROM CreditoProveedorDetalle WHERE facturaCompra.idFacturaCompra = :idFacturaCompra")
	CreditoProveedorDetalle findByFacturaCompra(Integer idFacturaCompra);
	
	@Query("FROM CreditoProveedorDetalle WHERE creditoProveedor.idCreditoProveedor = :idCreditoProveedor")
	List<CreditoProveedorDetalle> findByCreditoProveedor(Integer idCreditoProveedor);
	
	@Query("FROM CreditoProveedorDetalle WHERE creditoProveedor.proveedor.idProveedor = :idProveedor")
	List<CreditoProveedorDetalle> findByProveedor(Integer idProveedor);
	
	@Query("FROM CreditoProveedorDetalle WHERE creditoProveedor.idCreditoProveedor = :idCreditoProveedor AND pagada = :pagada")
	List<CreditoProveedorDetalle> findByCreditoProveedorAndPagada(Integer idCreditoProveedor, Boolean pagada);
	
	@Query("FROM CreditoProveedorDetalle WHERE pagada = :pagada")
	List<CreditoProveedorDetalle> findByPagada(Boolean pagada);

	@Query("FROM CreditoProveedorDetalle WHERE facturaCompra.fecha BETWEEN :fechaDesde AND :fechaHasta")
	List<CreditoProveedorDetalle> getByFechaFactura(LocalDate fechaDesde, LocalDate fechaHasta);
	
}
