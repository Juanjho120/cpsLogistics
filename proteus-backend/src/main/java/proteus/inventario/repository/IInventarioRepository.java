package proteus.inventario.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import proteus.generico.repository.IGenericRepository;
import proteus.inventario.model.Inventario;

/**
 * Repository for Inventario Model
 * 
 * @author Juan Tzun
 */
@Repository
public interface IInventarioRepository extends IGenericRepository<Inventario, Integer> {

	@Query("FROM Inventario WHERE concepto.idConcepto = :idConcepto")
	List<Inventario> findByConcepto(Integer idConcepto);
	
	@Query("FROM Inventario WHERE fechaHora BETWEEN  :fechaDesde AND :fechaHasta")
	List<Inventario> findByFechaRango(LocalDateTime fechaDesde, LocalDateTime fechaHasta);
	
	@Query("FROM Inventario WHERE concepto.idConcepto = :idConcepto "
			+ "AND fechaHora BETWEEN :fechaDesde AND :fechaHasta")
	List<Inventario> findByConceptoAndFechaRango(Integer idConcepto, LocalDateTime fechaDesde, LocalDateTime fechaHasta);
	
	@Query("SELECT id.inventario FROM InventarioDetalle id WHERE id.repuesto.idRepuesto = :idRepuesto")
	List<Inventario> findByRepuesto(Integer idRepuesto);
	
	@Query("FROM Inventario WHERE usuario.idUsuario = :idUsuario")
	List<Inventario> findByUsuario(Integer idUsuario);
	
	@Query("SELECT id.inventario FROM InventarioDetalle id WHERE id.repuesto.idRepuesto = :idRepuesto "
			+ "AND id.inventario.concepto.idConcepto = :idConcepto")
	List<Inventario> findByConceptoAndRepuesto(Integer idConcepto, Integer idRepuesto);
	
	@Query("FROM Inventario WHERE concepto.idConcepto = :idConcepto AND usuario.idUsuario = :idUsuario")
	List<Inventario> findByConceptoAndUsuario(Integer idConcepto, Integer idUsuario);
	
	@Query("SELECT id.inventario FROM InventarioDetalle id WHERE id.repuesto.idRepuesto = :idRepuesto "
			+ "AND id.inventario.concepto.idConcepto = :idConcepto AND id.inventario.usuario.idUsuario = :idUsuario")
	List<Inventario> findByConceptoAndRepuestoAndUsuario(Integer idConcepto, Integer idRepuesto, Integer idUsuario);
	
	@Query("SELECT id.inventario FROM InventarioDetalle id WHERE id.repuesto.idRepuesto = :idRepuesto "
			+ "AND id.inventario.concepto.idConcepto = :idConcepto AND id.inventario.fechaHora BETWEEN :fechaDesde AND :fechaHasta")
	List<Inventario> findByConceptoAndRepuestoAndFecha(Integer idConcepto, Integer idRepuesto, LocalDateTime fechaDesde, LocalDateTime fechaHasta);
	
	@Query("SELECT id.inventario FROM InventarioDetalle id WHERE id.repuesto.idRepuesto = :idRepuesto "
			+ "AND id.inventario.concepto.idConcepto = :idConcepto AND id.inventario.fechaHora BETWEEN :fechaDesde AND :fechaHasta "
			+ "AND id.inventario.usuario.idUsuario = :idUsuario")
	List<Inventario> findByConceptoAndRepuestoAndFechaAndUsuario(Integer idConcepto, Integer idRepuesto, 
			LocalDateTime fechaDesde, LocalDateTime fechaHasta, Integer idUsuario);
	
	@Query("FROM Inventario WHERE concepto.idConcepto = :idConcepto "
			+ "AND fechaHora BETWEEN :fechaDesde AND :fechaHasta AND usuario.idUsuario = :idUsuario")
	List<Inventario> findByConceptoAndFechaAndUsuario(Integer idConcepto, LocalDateTime fechaDesde, LocalDateTime fechaHasta, Integer idUsuario);
	
	@Query("SELECT id.inventario FROM InventarioDetalle id WHERE id.repuesto.idRepuesto = :idRepuesto "
			+ "AND id.inventario.usuario.idUsuario = :idUsuario")
	List<Inventario> findByRepuestoAndUsuario(Integer idRepuesto, Integer idUsuario);
	
	@Query("SELECT id.inventario FROM InventarioDetalle id WHERE id.repuesto.idRepuesto = :idRepuesto "
			+ "AND id.inventario.usuario.idUsuario = :idUsuario AND id.inventario.fechaHora BETWEEN :fechaDesde AND :fechaHasta")
	List<Inventario> findByRepuestoAndFechaAndUsuario(Integer idRepuesto, LocalDateTime fechaDesde, LocalDateTime fechaHasta, Integer idUsuario);
	
	@Query("SELECT id.inventario FROM InventarioDetalle id WHERE id.repuesto.idRepuesto = :idRepuesto "
			+ "AND id.inventario.fechaHora BETWEEN :fechaDesde AND :fechaHasta")
	List<Inventario> findByRepuestoAndFechaRango(Integer idRepuesto, LocalDateTime fechaDesde, LocalDateTime fechaHasta);
	
	@Query("FROM Inventario WHERE usuario.idUsuario = :idUsuario "
			+ "AND fechaHora BETWEEN :fechaDesde AND :fechaHasta")
	List<Inventario> findByUsuarioAndFechaRango(Integer idUsuario, LocalDateTime fechaDesde, LocalDateTime fechaHasta);
	
}
