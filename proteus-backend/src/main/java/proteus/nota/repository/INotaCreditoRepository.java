package proteus.nota.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import proteus.generico.repository.IGenericRepository;
import proteus.nota.model.NotaCredito;

/**
 * Repository for NotaCredito Model
 * 
 * @author Juan Tzun
 */
@Repository
public interface INotaCreditoRepository extends IGenericRepository<NotaCredito, Integer> {

	@Query("FROM NotaCredito WHERE facturaCompra.idFacturaCompra = :idFacturaCompra")
	List<NotaCredito> findByFacturaCompra(Integer idFacturaCompra);
	
	@Query("FROM NotaCredito WHERE fechaIngreso BETWEEN :fechaDesde AND :fechaHasta")
	List<NotaCredito> findByFechaIngreso(LocalDate fechaDesde, LocalDate fechaHasta);
	
	@Query("FROM NotaCredito WHERE codigo = :codigo")
	NotaCredito findByCodigo(String codigo);
	
}
