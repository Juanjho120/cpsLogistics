package proteus.transaccion.repository;

import org.springframework.stereotype.Repository;

import proteus.generico.repository.IGenericRepository;
import proteus.transaccion.model.TransaccionEstado;

/**
 * Repository for TransaccionEstado Model
 * 
 * @author Juan Tzun
 */
@Repository
public interface ITransaccionEstadoRepository extends IGenericRepository<TransaccionEstado, Integer> {

	TransaccionEstado findByNombre(String nombre);
	
}
