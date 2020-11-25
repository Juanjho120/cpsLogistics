package proteus.segmento.repository;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import proteus.generico.repository.IGenericRepository;
import proteus.segmento.model.SegmentoCredito;

/**
 * Repository for SegmentoCredito Model
 * 
 * @author Juan Tzun
 */
@Repository
public interface ISegmentoCreditoRepository extends IGenericRepository<SegmentoCredito, Integer> {

	@Query("FROM SegmentoCredito WHERE segmento.idSegmento = :idSegmento")
	SegmentoCredito findBySegmento(Integer idSegmento);
	
	@Modifying
	@Query("DELETE FROM SegmentoCredito WHERE segmento.idSegmento = :idSegmento")
	void deleteBySegmento(Integer idSegmento);
	
}
