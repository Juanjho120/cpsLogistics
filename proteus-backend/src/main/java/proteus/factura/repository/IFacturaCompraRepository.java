package proteus.factura.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import proteus.factura.model.FacturaCompra;
import proteus.generico.repository.IGenericRepository;

/**
 * Repository for FacturaCompra Model
 * 
 * @author Juan Tzun
 */
@Repository
public interface IFacturaCompraRepository extends IGenericRepository<FacturaCompra, Integer> {

	List<FacturaCompra> findByCodigo(String codigo);
	
	@Query("FROM FacturaCompra WHERE proveedor.idProveedor = :idProveedor")
	List<FacturaCompra> findByProveedor(Integer idProveedor);
	
	@Query("FROM FacturaCompra WHERE proveedor.idProveedor = :idProveedor AND codigo = :codigo")
	FacturaCompra findByCodigoAndProveedor(String codigo, Integer idProveedor);
	
	@Query("FROM FacturaCompra WHERE fecha BETWEEN :fechaDesde AND :fechaHasta")
	List<FacturaCompra> findByFecha(LocalDate fechaDesde, LocalDate fechaHasta);
	
	@Query("SELECT DISTINCT fcd.facturaCompra FROM FacturaCompraDetalle fcd WHERE fcd.repuesto.idRepuesto = :idRepuesto")
	List<FacturaCompra> findByRepuesto(Integer idRepuesto);
	
	@Query("SELECT cpd.facturaCompra FROM CreditoProveedorDetalle cpd WHERE cpd.vencida = :vencida")
	List<FacturaCompra> findByVencimiento(Boolean vencida);
	
	@Query("FROM FacturaCompra WHERE idFacturaCompra NOT IN (SELECT cpd.facturaCompra.idFacturaCompra FROM CreditoProveedorDetalle cpd)")
	List<FacturaCompra> findNotInCreditoProveedorDetalle();
	
	@Query("SELECT cpd.facturaCompra FROM CreditoProveedorDetalle cpd WHERE cpd.pagada = :pagada")
	List<FacturaCompra> findByPagada(Boolean pagada);
	
}
