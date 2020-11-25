package proteus.checklist.service.impl;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import proteus.checklist.dto.ChecklistChecklistEvaluacionDTO;
import proteus.checklist.model.Checklist;
import proteus.checklist.model.ChecklistDetalle;
import proteus.checklist.model.ChecklistEvaluacion;
import proteus.checklist.repository.IChecklistRepository;
import proteus.checklist.service.IChecklistDetalleService;
import proteus.checklist.service.IChecklistService;
import proteus.generico.repository.IGenericRepository;
import proteus.generico.service.CRUDImpl;

/**
 * Services for Checklist Model (Implementation)
 * 
 * @author Juan Tzun
 */
@Service
public class ChecklistServiceImpl extends CRUDImpl<Checklist, Integer> implements IChecklistService {

	@Autowired
	private IChecklistRepository checklistRepository;
	
	@Autowired
	private IChecklistDetalleService checklistDetalleService;

	@Override
	protected IGenericRepository<Checklist, Integer> getRepository() {
		return checklistRepository;
	}

	@Override
	public Checklist createDTO(ChecklistChecklistEvaluacionDTO checklistDto) throws Exception {
		Checklist checklist = checklistDto.getChecklist();
		Checklist checklistAux = this.getByServicio(checklist.getServicio().getIdServicio());
		if(checklistAux==null) {
			List<ChecklistEvaluacion> checklistEvaluacionList = checklistDto.getChecklistEvaluacion();
			
			//Se crea el detalle dependiendo si es de 5000Kms o 20000Kms
			List<ChecklistDetalle> checkistDetalleList = checklistDetalleService.createByChecklistTipo(checklist, checklistEvaluacionList);
			
			checklist.setFechaHoraIngreso(LocalDateTime.now());
			checklist.setChecklistDetalle(checkistDetalleList);
			return checklistRepository.save(checklist);
		}
		return null;
	}

	@Override
	public Checklist getByServicio(Integer idServicio) throws Exception {
		return checklistRepository.findByServicio(idServicio);
	}
	
	@Override
	public void delete(Integer idChecklist) throws Exception {
		if(!checklistDetalleService.getByChecklist(idChecklist).isEmpty()) {
			checklistDetalleService.deleteByChecklist(idChecklist);
		}
		checklistRepository.deleteById(idChecklist);
	}

	@Override
	public Checklist updateDTO(ChecklistChecklistEvaluacionDTO checklistDto) throws Exception {
		Checklist checklist = checklistDto.getChecklist();
		Checklist checklistAux = this.getByServicio(checklist.getServicio().getIdServicio());
		
		List<ChecklistEvaluacion> checklistEvaluacionList = checklistDto.getChecklistEvaluacion();
		
		//Borra el detalle antiguo
		if(!checklistDetalleService.getByChecklist(checklist.getIdChecklist()).isEmpty()) {
			checklistDetalleService.deleteByChecklist(checklist.getIdChecklist());
		}
		
		//Se crea el detalle dependiendo si es de 5000Kms o 20000Kms
		List<ChecklistDetalle> checkistDetalleList = checklistDetalleService.createByChecklistTipo(checklist, checklistEvaluacionList);
		checklist.setChecklistDetalle(checkistDetalleList);
		
		//Se mantiene la fecha y hora de ingreso original
		checklist.setFechaHoraIngreso(checklistAux.getFechaHoraIngreso());
		
		return checklistRepository.save(checklist);
	}

	@Override
	public List<Checklist> getByFechaHoraIngreso(String fechaDesde, String fechaHasta) throws Exception {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		LocalDateTime fechaDesdeParse = LocalDateTime.parse(fechaDesde, formatter);
		LocalDateTime fechaHastaParse = LocalDateTime.parse(fechaHasta, formatter);
		return checklistRepository.findByFechaHoraIngreso(fechaDesdeParse, fechaHastaParse.plusDays(1));
	}

	@Override
	public List<Checklist> getByFechaRevision(String fechaDesde, String fechaHasta) throws Exception {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		LocalDateTime fechaDesdeParse = LocalDateTime.parse(fechaDesde, formatter);
		LocalDateTime fechaHastaParse = LocalDateTime.parse(fechaHasta, formatter);
		return checklistRepository.findByFechaRevision(fechaDesdeParse, fechaHastaParse.plusDays(1));
	}

	@Override
	public Checklist getByNoOrdenTrabajo(String noOrdenTrabajo) throws Exception {
		return checklistRepository.findByNoOrdenTrabajo(noOrdenTrabajo);
	}

	@Override
	public List<Checklist> getByMecanico(Integer idPersonal) throws Exception {
		return checklistRepository.findByMecanico(idPersonal);
	}

	@Override
	public List<Checklist> getBySupervisor(Integer idPersonal) throws Exception {
		return checklistRepository.findBySupervisor(idPersonal);
	}

	@Override
	public List<Checklist> getByUsuarioIngreso(Integer idUsuario) throws Exception {
		return checklistRepository.findByUsuario(idUsuario);
	}

	@Override
	public List<Checklist> getByChecklistServicioTipo(Integer idChecklistServicioTipo) {
		return checklistRepository.findByChecklistServicioTipo(idChecklistServicioTipo);
	}
	
}
