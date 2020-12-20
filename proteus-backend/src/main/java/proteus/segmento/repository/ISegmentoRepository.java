package proteus.segmento.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
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
	
	@Query("SELECT sc.segmento FROM SegmentoCredito sc")
	List<Segmento> findWithCredito();
	
}
