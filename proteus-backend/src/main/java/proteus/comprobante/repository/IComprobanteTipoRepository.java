package proteus.comprobante.repository;

import org.springframework.stereotype.Repository;

import proteus.comprobante.model.ComprobanteTipo;
import proteus.generico.repository.IGenericRepository;

/**
 * Repository for ComprobanteTipo Model
 * 
 * @author Juan Tzun
 */
@Repository
public interface IComprobanteTipoRepository extends IGenericRepository<ComprobanteTipo, Integer> {

	ComprobanteTipo findByNombre(String nombre);
	
}
