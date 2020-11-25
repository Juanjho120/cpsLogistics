package proteus.checklist.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import proteus.checklist.model.Checklist;
import proteus.generico.repository.IGenericRepository;

/**
 * Repository for Checklist Model
 * 
 * @author Juan Tzun
 */
@Repository
public interface IChecklistRepository extends IGenericRepository<Checklist, Integer> {

	@Query("FROM Checklist WHERE servicio.idServicio = :idServicio")
	Checklist findByServicio(Integer idServicio);
	
	Checklist findByNoOrdenTrabajo(String noOrdenTrabajo);
	
	@Query("FROM Checklist WHERE fechaHoraIngreso BETWEEN :fechaDesde AND :fechaHasta")
	List<Checklist> findByFechaHoraIngreso(LocalDateTime fechaDesde, LocalDateTime fechaHasta);
	
	@Query("FROM Checklist WHERE fechaRevision BETWEEN :fechaDesde AND :fechaHasta")
	List<Checklist> findByFechaRevision(LocalDateTime fechaDesde, LocalDateTime fechaHasta);
	
	@Query("FROM Checklist WHERE mecanico.idPersonal = :idPersonal")
	List<Checklist> findByMecanico(Integer idPersonal);
	
	@Query("FROM Checklist WHERE supervisor.idPersonal = :idPersonal")
	List<Checklist> findBySupervisor(Integer idPersonal);
	
	@Query("FROM Checklist WHERE usuarioIngreso.idUsuario = :idUsuario")
	List<Checklist> findByUsuario(Integer idUsuario);
	
	@Query("FROM Checklist WHERE checklistServicioTipo.idChecklistServicioTipo = :idChecklistServicioTipo")
	List<Checklist> findByChecklistServicioTipo(Integer idChecklistServicioTipo);
	
}
