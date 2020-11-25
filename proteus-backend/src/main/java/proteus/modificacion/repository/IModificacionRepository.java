package proteus.modificacion.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import proteus.generico.repository.IGenericRepository;
import proteus.modificacion.model.Modificacion;

/**
 * Repository for Modificacion Model
 * 
 * @author Juan Tzun
 */
@Repository
public interface IModificacionRepository extends IGenericRepository<Modificacion, Integer> {

	@Query("FROM Modificacion WHERE usuario.idUsuario = :idUsuario")
	List<Modificacion> findByUsuario(Integer idUsuario);
	
	@Query("FROM Modificacion WHERE concepto.idConcepto = :idConcepto")
	List<Modificacion> findByConcepto(Integer idConcepto);
	
	@Query("FROM Modificacion WHERE tabla = :tabla")
	List<Modificacion> findByTabla(String tabla);
	
	@Query("FROM Modificacion WHERE concepto.idConcepto = :idConcepto AND tabla = :tabla")
	List<Modificacion> findByConceptoAndTabla(Integer idConcepto, String tabla);
}
