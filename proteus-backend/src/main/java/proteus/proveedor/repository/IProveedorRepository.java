package proteus.proveedor.repository;

import org.springframework.stereotype.Repository;

import proteus.generico.repository.IGenericRepository;
import proteus.proveedor.model.Proveedor;

/**
 * Repository for Proveedor Model
 * 
 * @author Juan Tzun
 */
@Repository
public interface IProveedorRepository extends IGenericRepository<Proveedor, Integer> {

	Proveedor findByNombreAndNit(String nombre, String nit);
	Proveedor findByNit(String nit);
	
}
