package proteus.repuesto.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import proteus.generico.repository.IGenericRepository;
import proteus.repuesto.model.Repuesto;

/**
 * Repository for Repuesto Model
 * 
 * @author Juan Tzun
 */
@Repository
public interface IRepuestoRepository extends IGenericRepository<Repuesto, Integer> {

	List<Repuesto> findByCodigo(String codigo);
	List<Repuesto> findByCodigoBarra(String codigoBarra);
	
	@Modifying
	@Query("DELETE FROM Repuesto WHERE codigo =: codigo")
	void deleteByCodigo(String codigo);
	
	@Modifying
	@Query("DELETE FROM Repuesto WHERE codigoBarra =: codigoBarra")
	void deleteByCodigoBarra(String codigoBarra);
	
}
