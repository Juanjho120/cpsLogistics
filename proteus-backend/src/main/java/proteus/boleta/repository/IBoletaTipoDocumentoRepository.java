package proteus.boleta.repository;

import org.springframework.stereotype.Repository;

import proteus.boleta.model.BoletaTipoDocumento;
import proteus.generico.repository.IGenericRepository;

/**
 * Repository for BoletaTipoDocumento Model
 * 
 * @author Juan Tzun
 */
@Repository
public interface IBoletaTipoDocumentoRepository extends IGenericRepository<BoletaTipoDocumento, Integer> {

}
