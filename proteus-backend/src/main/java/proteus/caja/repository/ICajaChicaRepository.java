package proteus.caja.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import proteus.caja.model.CajaChica;
import proteus.generico.repository.IGenericRepository;

/**
 * Repository for CajaChica Model
 * 
 * @author Juan Tzun
 */
@Repository
public interface ICajaChicaRepository extends IGenericRepository<CajaChica, Integer> {

	@Query("FROM CajaChica WHERE proveedor.idProveedor = :idProveedor AND codigoFactura = :codigoFactura")
	CajaChica findByProveedorAndCodigoFactura(Integer idProveedor, String codigoFactura);
	
	@Query("FROM CajaChica WHERE servicio.idServicio = :idServicio")
	List<CajaChica> findByServicio(Integer idServicio);
	
	@Query("FROM CajaChica WHERE proveedor.idProveedor = :idProveedor")
	List<CajaChica> findByProveedor(Integer idProveedor);
	
}
