package proteus.segmento.repository;

import org.springframework.stereotype.Repository;

import proteus.generico.repository.IGenericRepository;
import proteus.segmento.model.Segmento;

/**
 * Repository for Segmento Model
 * 
 * @author Juan Tzun
 */
@Repository
public interface ISegmentoRepository extends IGenericRepository<Segmento, Integer> {

	Segmento findByNit(String nit);
	
}
