package proteus.checklist.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import proteus.checklist.model.Checklist;
import proteus.checklist.model.ChecklistDetalle;
import proteus.checklist.model.ChecklistEvaluacion;
import proteus.checklist.repository.IChecklistDetalleRepository;
import proteus.checklist.service.IChecklistDetalleService;
import proteus.checklist.service.IChecklistItemService;
import proteus.generico.repository.IGenericRepository;
import proteus.generico.service.CRUDImpl;

/**
 * Services for ChecklistDetalle Model (Implementation)
 * 
 * @author Juan Tzun
 */
@Service
public class ChecklistDetalleServiceImpl extends CRUDImpl<ChecklistDetalle, Integer> implements IChecklistDetalleService {

	@Autowired
	private IChecklistDetalleRepository checklistDetalleRepository;
	
	@Autowired
	private IChecklistItemService checklistItemService;

	@Override
	protected IGenericRepository<ChecklistDetalle, Integer> getRepository() {
		return checklistDetalleRepository;
	}

	@Override
	public List<ChecklistDetalle> getByChecklist(Integer idChecklist) throws Exception {
		return checklistDetalleRepository.findByChecklist(idChecklist);
	}

	@Transactional
	@Override
	public void deleteByChecklist(Integer idChecklist) throws Exception {
		checklistDetalleRepository.deleteByChecklist(idChecklist);
	}

	@Override
	public List<ChecklistDetalle> createByChecklistTipo(Checklist checklist,
			List<ChecklistEvaluacion> checklistEvaluacionList) throws Exception {
		List<ChecklistDetalle> checkistDetalleList = new ArrayList<ChecklistDetalle>();
		//Dependiendo del tipo de checklist asi se va a crear el detalle
		if(checklist.getChecklistServicioTipo().getIdChecklistServicioTipo() == 1) { //5000 KMS
			if(checklistEvaluacionList.size()==38) {
				checkistDetalleList.add(new ChecklistDetalle(null, checklist, checklistItemService.getById(2), checklistEvaluacionList.get(0), false));
				checkistDetalleList.add(new ChecklistDetalle(null, checklist, checklistItemService.getById(41), checklistEvaluacionList.get(1), false));
				checkistDetalleList.add(new ChecklistDetalle(null, checklist, checklistItemService.getById(42), checklistEvaluacionList.get(2), false));
				checkistDetalleList.add(new ChecklistDetalle(null, checklist, checklistItemService.getById(43), checklistEvaluacionList.get(3), false));
				checkistDetalleList.add(new ChecklistDetalle(null, checklist, checklistItemService.getById(7), checklistEvaluacionList.get(4), false));
				checkistDetalleList.add(new ChecklistDetalle(null, checklist, checklistItemService.getById(44), checklistEvaluacionList.get(5), false));
				checkistDetalleList.add(new ChecklistDetalle(null, checklist, checklistItemService.getById(8), checklistEvaluacionList.get(6), false));
				checkistDetalleList.add(new ChecklistDetalle(null, checklist, checklistItemService.getById(9), checklistEvaluacionList.get(7), false));
				checkistDetalleList.add(new ChecklistDetalle(null, checklist, checklistItemService.getById(10), checklistEvaluacionList.get(8), false));
				checkistDetalleList.add(new ChecklistDetalle(null, checklist, checklistItemService.getById(11), checklistEvaluacionList.get(9), false));
				checkistDetalleList.add(new ChecklistDetalle(null, checklist, checklistItemService.getById(45), checklistEvaluacionList.get(10), false));
				checkistDetalleList.add(new ChecklistDetalle(null, checklist, checklistItemService.getById(46), checklistEvaluacionList.get(11), false));
				checkistDetalleList.add(new ChecklistDetalle(null, checklist, checklistItemService.getById(47), checklistEvaluacionList.get(12), false));
				checkistDetalleList.add(new ChecklistDetalle(null, checklist, checklistItemService.getById(40), checklistEvaluacionList.get(13), false));
				checkistDetalleList.add(new ChecklistDetalle(null, checklist, checklistItemService.getById(12), checklistEvaluacionList.get(14), false));
				checkistDetalleList.add(new ChecklistDetalle(null, checklist, checklistItemService.getById(13), checklistEvaluacionList.get(15), false));
				checkistDetalleList.add(new ChecklistDetalle(null, checklist, checklistItemService.getById(14), checklistEvaluacionList.get(16), false));
				checkistDetalleList.add(new ChecklistDetalle(null, checklist, checklistItemService.getById(15), checklistEvaluacionList.get(17), false));
				checkistDetalleList.add(new ChecklistDetalle(null, checklist, checklistItemService.getById(16), checklistEvaluacionList.get(18), false));
				checkistDetalleList.add(new ChecklistDetalle(null, checklist, checklistItemService.getById(48), checklistEvaluacionList.get(19), false));
				checkistDetalleList.add(new ChecklistDetalle(null, checklist, checklistItemService.getById(24), checklistEvaluacionList.get(20), false));
				checkistDetalleList.add(new ChecklistDetalle(null, checklist, checklistItemService.getById(25), checklistEvaluacionList.get(21), false));
				checkistDetalleList.add(new ChecklistDetalle(null, checklist, checklistItemService.getById(49), checklistEvaluacionList.get(22), false));
				checkistDetalleList.add(new ChecklistDetalle(null, checklist, checklistItemService.getById(23), checklistEvaluacionList.get(23), false));
				checkistDetalleList.add(new ChecklistDetalle(null, checklist, checklistItemService.getById(26), checklistEvaluacionList.get(24), false));
				checkistDetalleList.add(new ChecklistDetalle(null, checklist, checklistItemService.getById(27), checklistEvaluacionList.get(25), false));
				checkistDetalleList.add(new ChecklistDetalle(null, checklist, checklistItemService.getById(28), checklistEvaluacionList.get(26), false));
				checkistDetalleList.add(new ChecklistDetalle(null, checklist, checklistItemService.getById(29), checklistEvaluacionList.get(27), false));
				checkistDetalleList.add(new ChecklistDetalle(null, checklist, checklistItemService.getById(30), checklistEvaluacionList.get(28), false));
				checkistDetalleList.add(new ChecklistDetalle(null, checklist, checklistItemService.getById(31), checklistEvaluacionList.get(29), false));
				checkistDetalleList.add(new ChecklistDetalle(null, checklist, checklistItemService.getById(32), checklistEvaluacionList.get(30), false));
				checkistDetalleList.add(new ChecklistDetalle(null, checklist, checklistItemService.getById(33), checklistEvaluacionList.get(31), false));
				checkistDetalleList.add(new ChecklistDetalle(null, checklist, checklistItemService.getById(34), checklistEvaluacionList.get(32), true));
				checkistDetalleList.add(new ChecklistDetalle(null, checklist, checklistItemService.getById(35), checklistEvaluacionList.get(33), true));
				checkistDetalleList.add(new ChecklistDetalle(null, checklist, checklistItemService.getById(36), checklistEvaluacionList.get(34), true));
				checkistDetalleList.add(new ChecklistDetalle(null, checklist, checklistItemService.getById(37), checklistEvaluacionList.get(35), true));
				checkistDetalleList.add(new ChecklistDetalle(null, checklist, checklistItemService.getById(38), checklistEvaluacionList.get(36), true));
				checkistDetalleList.add(new ChecklistDetalle(null, checklist, checklistItemService.getById(39), checklistEvaluacionList.get(37), true));
			}
		} else if(checklist.getChecklistServicioTipo().getIdChecklistServicioTipo() == 2) { //20000 KMS
			if(checklistEvaluacionList.size()==40) {
				checkistDetalleList.add(new ChecklistDetalle(null, checklist, checklistItemService.getById(1), checklistEvaluacionList.get(0), false));
				checkistDetalleList.add(new ChecklistDetalle(null, checklist, checklistItemService.getById(2), checklistEvaluacionList.get(1), false));
				checkistDetalleList.add(new ChecklistDetalle(null, checklist, checklistItemService.getById(3), checklistEvaluacionList.get(2), false));
				checkistDetalleList.add(new ChecklistDetalle(null, checklist, checklistItemService.getById(4), checklistEvaluacionList.get(3), false));
				checkistDetalleList.add(new ChecklistDetalle(null, checklist, checklistItemService.getById(5), checklistEvaluacionList.get(4), false));
				checkistDetalleList.add(new ChecklistDetalle(null, checklist, checklistItemService.getById(6), checklistEvaluacionList.get(5), false));
				checkistDetalleList.add(new ChecklistDetalle(null, checklist, checklistItemService.getById(7), checklistEvaluacionList.get(6), false));
				checkistDetalleList.add(new ChecklistDetalle(null, checklist, checklistItemService.getById(8), checklistEvaluacionList.get(7), false));
				checkistDetalleList.add(new ChecklistDetalle(null, checklist, checklistItemService.getById(9), checklistEvaluacionList.get(8), false));
				checkistDetalleList.add(new ChecklistDetalle(null, checklist, checklistItemService.getById(10), checklistEvaluacionList.get(9), false));
				checkistDetalleList.add(new ChecklistDetalle(null, checklist, checklistItemService.getById(11), checklistEvaluacionList.get(10), false));
				checkistDetalleList.add(new ChecklistDetalle(null, checklist, checklistItemService.getById(40), checklistEvaluacionList.get(11), false));
				checkistDetalleList.add(new ChecklistDetalle(null, checklist, checklistItemService.getById(12), checklistEvaluacionList.get(12), false));
				checkistDetalleList.add(new ChecklistDetalle(null, checklist, checklistItemService.getById(13), checklistEvaluacionList.get(13), false));
				checkistDetalleList.add(new ChecklistDetalle(null, checklist, checklistItemService.getById(14), checklistEvaluacionList.get(14), false));
				checkistDetalleList.add(new ChecklistDetalle(null, checklist, checklistItemService.getById(15), checklistEvaluacionList.get(15), false));
				checkistDetalleList.add(new ChecklistDetalle(null, checklist, checklistItemService.getById(16), checklistEvaluacionList.get(16), false));
				checkistDetalleList.add(new ChecklistDetalle(null, checklist, checklistItemService.getById(17), checklistEvaluacionList.get(17), false));
				checkistDetalleList.add(new ChecklistDetalle(null, checklist, checklistItemService.getById(18), checklistEvaluacionList.get(18), false));
				checkistDetalleList.add(new ChecklistDetalle(null, checklist, checklistItemService.getById(19), checklistEvaluacionList.get(19), false));
				checkistDetalleList.add(new ChecklistDetalle(null, checklist, checklistItemService.getById(20), checklistEvaluacionList.get(20), false));
				checkistDetalleList.add(new ChecklistDetalle(null, checklist, checklistItemService.getById(21), checklistEvaluacionList.get(21), false));
				checkistDetalleList.add(new ChecklistDetalle(null, checklist, checklistItemService.getById(22), checklistEvaluacionList.get(22), false));
				checkistDetalleList.add(new ChecklistDetalle(null, checklist, checklistItemService.getById(23), checklistEvaluacionList.get(23), false));
				checkistDetalleList.add(new ChecklistDetalle(null, checklist, checklistItemService.getById(24), checklistEvaluacionList.get(24), false));
				checkistDetalleList.add(new ChecklistDetalle(null, checklist, checklistItemService.getById(25), checklistEvaluacionList.get(25), false));
				checkistDetalleList.add(new ChecklistDetalle(null, checklist, checklistItemService.getById(26), checklistEvaluacionList.get(26), false));
				checkistDetalleList.add(new ChecklistDetalle(null, checklist, checklistItemService.getById(27), checklistEvaluacionList.get(27), false));
				checkistDetalleList.add(new ChecklistDetalle(null, checklist, checklistItemService.getById(28), checklistEvaluacionList.get(28), false));
				checkistDetalleList.add(new ChecklistDetalle(null, checklist, checklistItemService.getById(29), checklistEvaluacionList.get(29), false));
				checkistDetalleList.add(new ChecklistDetalle(null, checklist, checklistItemService.getById(30), checklistEvaluacionList.get(30), false));
				checkistDetalleList.add(new ChecklistDetalle(null, checklist, checklistItemService.getById(31), checklistEvaluacionList.get(31), false));
				checkistDetalleList.add(new ChecklistDetalle(null, checklist, checklistItemService.getById(32), checklistEvaluacionList.get(32), false));
				checkistDetalleList.add(new ChecklistDetalle(null, checklist, checklistItemService.getById(33), checklistEvaluacionList.get(33), false));
				checkistDetalleList.add(new ChecklistDetalle(null, checklist, checklistItemService.getById(34), checklistEvaluacionList.get(34), true));
				checkistDetalleList.add(new ChecklistDetalle(null, checklist, checklistItemService.getById(35), checklistEvaluacionList.get(35), true));
				checkistDetalleList.add(new ChecklistDetalle(null, checklist, checklistItemService.getById(36), checklistEvaluacionList.get(36), true));
				checkistDetalleList.add(new ChecklistDetalle(null, checklist, checklistItemService.getById(37), checklistEvaluacionList.get(37), true));
				checkistDetalleList.add(new ChecklistDetalle(null, checklist, checklistItemService.getById(38), checklistEvaluacionList.get(38), true));
				checkistDetalleList.add(new ChecklistDetalle(null, checklist, checklistItemService.getById(39), checklistEvaluacionList.get(39), true));
			}
		}
		return checkistDetalleList;
	}
	
}
