package proteus.credito.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import proteus.credito.model.CreditoProveedor;
import proteus.generico.repository.IGenericRepository;

/**
 * Repository for CreditoProveedor Model
 * 
 * @author Juan Tzun
 */
@Repository
public interface ICreditoProveedorRepository extends IGenericRepository<CreditoProveedor, Integer> {

	@Query("FROM CreditoProveedor WHERE proveedor.idProveedor = :idProveedor")
	CreditoProveedor findByProveedor(Integer idProveedor);
	
}
