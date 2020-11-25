package proteus.cotizacion.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import proteus.cotizacion.model.CotizacionRepuesto;
import proteus.generico.repository.IGenericRepository;

/**
 * Repository for CotizacionRepuesto Model
 * 
 * @author Juan Tzun
 */
@Repository
public interface ICotizacionRepuestoRepository extends IGenericRepository<CotizacionRepuesto, Integer> {

	@Query("FROM CotizacionRepuesto WHERE cotizacion.idCotizacion = :idCotizacion")
	List<CotizacionRepuesto> findByCotizacion(Integer idCotizacion);
	
	@Modifying
	@Query("DELETE FROM CotizacionRepuesto WHERE cotizacion.idCotizacion = :idCotizacion")
	void deleteByCotizacion(Integer idCotizacion);
	
}
