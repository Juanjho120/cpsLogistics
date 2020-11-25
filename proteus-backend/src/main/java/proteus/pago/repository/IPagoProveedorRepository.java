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

	PagoProveedor findByNoDocumento(String noDocumento);
	
	@Query("FROM PagoProveedor WHERE creditoProveedor.proveedor.idProveedor = :idProveedor")
	List<PagoProveedor> findByProveedor(Integer idProveedor);
	
	@Query("FROM PagoProveedor WHERE fechaHoraPago BETWEEN :fechaDesde AND :fechaHasta")
	List<PagoProveedor> findByFechaPago(LocalDateTime fechaDesde, LocalDateTime fechaHasta);
	
}
