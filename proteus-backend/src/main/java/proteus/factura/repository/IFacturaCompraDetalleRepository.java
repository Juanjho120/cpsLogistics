package proteus.factura.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import proteus.factura.model.FacturaCompraDetalle;
import proteus.generico.repository.IGenericRepository;

/**
 * Repository for FacturaCompraDetalle Model
 * 
 * @author Juan Tzun
 */
@Repository
public interface IFacturaCompraDetalleRepository extends IGenericRepository<FacturaCompraDetalle, Integer> {

	@Query("FROM FacturaCompraDetalle WHERE facturaCompra.idFacturaCompra = :idFacturaCompra")
	List<FacturaCompraDetalle> findByFacturaCompra(Integer idFacturaCompra);
	
	@Modifying
	@Query("DELETE FROM FacturaCompraDetalle WHERE facturaCompra.idFacturaCompra = :idFacturaCompra")
	void deleteByFacturaCompra(Integer idFacturaCompra);
	
}
