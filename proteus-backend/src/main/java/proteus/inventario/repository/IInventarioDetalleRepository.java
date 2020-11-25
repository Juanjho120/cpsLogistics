package proteus.inventario.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import proteus.generico.repository.IGenericRepository;
import proteus.inventario.model.InventarioDetalle;

/**
 * Repository for InventarioDetalle Model
 * 
 * @author Juan Tzun
 */
@Repository
public interface IInventarioDetalleRepository extends IGenericRepository<InventarioDetalle, Integer> {

	@Modifying
	@Query("DELETE FROM InventarioDetalle WHERE inventario.idInventario = :idInventario")
	void deleteByInventario(Integer idInventario);
	
	@Query("FROM InventarioDetalle WHERE inventario.idInventario = :idInventario")
	List<InventarioDetalle> findByInventario(Integer idInventario);
	
}
