package proteus.proveedor.repository;

import org.springframework.stereotype.Repository;

import proteus.generico.repository.IGenericRepository;
import proteus.proveedor.model.ProveedorMenor;

/**
 * Repository for ProveedorMenor Model
 * 
 * @author Juan Tzun
 */
@Repository
public interface IProveedorMenorRepository extends IGenericRepository<ProveedorMenor, Integer> {
	
	ProveedorMenor findByNombre(String nombre);

}
