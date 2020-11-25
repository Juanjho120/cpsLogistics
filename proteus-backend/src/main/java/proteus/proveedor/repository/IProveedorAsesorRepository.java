package proteus.proveedor.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import proteus.generico.repository.IGenericRepository;
import proteus.proveedor.model.ProveedorAsesor;

/**
 * Repository for ProveedorAsesor Model
 * 
 * @author Juan Tzun
 */
@Repository
public interface IProveedorAsesorRepository extends IGenericRepository<ProveedorAsesor, Integer> {

	ProveedorAsesor findByTelefono(String telefono);
	
	@Query("FROM ProveedorAsesor WHERE nombre = :nombre AND telefono = :telefono AND proveedor.id = :idProveedor")
	ProveedorAsesor findByNombreAndTelefonoAndProveedor(String nombre, String telefono, Integer idProveedor);
	
	@Query("FROM ProveedorAsesor WHERE proveedor.id = :idProveedor")
	List<ProveedorAsesor> findByProveedor(Integer idProveedor);
	
	@Modifying
	@Query("DELETE FROM ProveedorAsesor WHERE proveedor.id = :idProveedor")
	void deleteByProveedor(Integer idProveedor);
	
}
