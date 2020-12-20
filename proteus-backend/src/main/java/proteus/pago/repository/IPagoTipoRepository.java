package proteus.pago.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import proteus.generico.repository.IGenericRepository;
import proteus.pago.model.PagoTipo;

/**
 * Repository for PagoTipo Model
 * 
 * @author Juan Tzun
 */
@Repository
public interface IPagoTipoRepository extends IGenericRepository<PagoTipo, Integer> {

	@Query("FROM PagoTipo WHERE nombre = :nombre")
	PagoTipo findByNombre(String nombre);
	
}
