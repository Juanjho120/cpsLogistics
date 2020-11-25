package proteus.checklist.service;

import java.util.List;

import proteus.checklist.dto.ChecklistChecklistEvaluacionDTO;
import proteus.checklist.model.Checklist;
import proteus.generico.service.ICRUD;

/**
 * Services for Checklist Model (Interface)
 * 
 * @author Juan Tzun
 */
public interface IChecklistService extends ICRUD<Checklist, Integer> {

	Checklist createDTO(ChecklistChecklistEvaluacionDTO checklistDto) throws Exception;
	Checklist getByServicio(Integer idServicio) throws Exception;
	Checklist getByNoOrdenTrabajo(String noOrdenTrabajo) throws Exception;
	Checklist updateDTO(ChecklistChecklistEvaluacionDTO checklistDto) throws Exception;
	List<Checklist> getByFechaHoraIngreso(String fechaDesde, String fechaHasta) throws Exception;
	List<Checklist> getByFechaRevision(String fechaDesde, String fechaHasta) throws Exception;
	List<Checklist> getByMecanico(Integer idPersonal) throws Exception;
	List<Checklist> getBySupervisor(Integer idPersonal) throws Exception;
	List<Checklist> getByUsuarioIngreso(Integer idUsuario) throws Exception;
	List<Checklist> getByChecklistServicioTipo(Integer idChecklistServicioTipo);
	
}
