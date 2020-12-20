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
		List<ChecklistDetalle> checklistDetalleList = new ArrayList<ChecklistDetalle>();
		//Dependiendo del tipo de checklist asi se va a crear el detalle
		if(checklist.getChecklistServicioTipo().getIdChecklistServicioTipo() == 1) { //5000 KMS
			if(checklistEvaluacionList.size()==38) {
				checklistDetalleList.add(new ChecklistDetalle(null, checklist, checklistItemService.getById(2), checklistEvaluacionList.get(0), false));
				checklistDetalleList.add(new ChecklistDetalle(null, checklist, checklistItemService.getById(41), checklistEvaluacionList.get(1), false));
				checklistDetalleList.add(new ChecklistDetalle(null, checklist, checklistItemService.getById(42), checklistEvaluacionList.get(2), false));
				checklistDetalleList.add(new ChecklistDetalle(null, checklist, checklistItemService.getById(43), checklistEvaluacionList.get(3), false));
				checklistDetalleList.add(new ChecklistDetalle(null, checklist, checklistItemService.getById(7), checklistEvaluacionList.get(4), false));
				checklistDetalleList.add(new ChecklistDetalle(null, checklist, checklistItemService.getById(44), checklistEvaluacionList.get(5), false));
				checklistDetalleList.add(new ChecklistDetalle(null, checklist, checklistItemService.getById(8), checklistEvaluacionList.get(6), false));
				checklistDetalleList.add(new ChecklistDetalle(null, checklist, checklistItemService.getById(9), checklistEvaluacionList.get(7), false));
				checklistDetalleList.add(new ChecklistDetalle(null, checklist, checklistItemService.getById(10), checklistEvaluacionList.get(8), false));
				checklistDetalleList.add(new ChecklistDetalle(null, checklist, checklistItemService.getById(11), checklistEvaluacionList.get(9), false));
				checklistDetalleList.add(new ChecklistDetalle(null, checklist, checklistItemService.getById(45), checklistEvaluacionList.get(10), false));
				checklistDetalleList.add(new ChecklistDetalle(null, checklist, checklistItemService.getById(46), checklistEvaluacionList.get(11), false));
				checklistDetalleList.add(new ChecklistDetalle(null, checklist, checklistItemService.getById(47), checklistEvaluacionList.get(12), false));
				checklistDetalleList.add(new ChecklistDetalle(null, checklist, checklistItemService.getById(40), checklistEvaluacionList.get(13), false));
				checklistDetalleList.add(new ChecklistDetalle(null, checklist, checklistItemService.getById(12), checklistEvaluacionList.get(14), false));
				checklistDetalleList.add(new ChecklistDetalle(null, checklist, checklistItemService.getById(13), checklistEvaluacionList.get(15), false));
				checklistDetalleList.add(new ChecklistDetalle(null, checklist, checklistItemService.getById(14), checklistEvaluacionList.get(16), false));
				checklistDetalleList.add(new ChecklistDetalle(null, checklist, checklistItemService.getById(15), checklistEvaluacionList.get(17), false));
				checklistDetalleList.add(new ChecklistDetalle(null, checklist, checklistItemService.getById(16), checklistEvaluacionList.get(18), false));
				checklistDetalleList.add(new ChecklistDetalle(null, checklist, checklistItemService.getById(48), checklistEvaluacionList.get(19), false));
				checklistDetalleList.add(new ChecklistDetalle(null, checklist, checklistItemService.getById(24), checklistEvaluacionList.get(20), false));
				checklistDetalleList.add(new ChecklistDetalle(null, checklist, checklistItemService.getById(25), checklistEvaluacionList.get(21), false));
				checklistDetalleList.add(new ChecklistDetalle(null, checklist, checklistItemService.getById(49), checklistEvaluacionList.get(22), false));
				checklistDetalleList.add(new ChecklistDetalle(null, checklist, checklistItemService.getById(23), checklistEvaluacionList.get(23), false));
				checklistDetalleList.add(new ChecklistDetalle(null, checklist, checklistItemService.getById(26), checklistEvaluacionList.get(24), false));
				checklistDetalleList.add(new ChecklistDetalle(null, checklist, checklistItemService.getById(27), checklistEvaluacionList.get(25), false));
				checklistDetalleList.add(new ChecklistDetalle(null, checklist, checklistItemService.getById(28), checklistEvaluacionList.get(26), false));
				checklistDetalleList.add(new ChecklistDetalle(null, checklist, checklistItemService.getById(29), checklistEvaluacionList.get(27), false));
				checklistDetalleList.add(new ChecklistDetalle(null, checklist, checklistItemService.getById(30), checklistEvaluacionList.get(28), false));
				checklistDetalleList.add(new ChecklistDetalle(null, checklist, checklistItemService.getById(31), checklistEvaluacionList.get(29), false));
				checklistDetalleList.add(new ChecklistDetalle(null, checklist, checklistItemService.getById(32), checklistEvaluacionList.get(30), false));
				checklistDetalleList.add(new ChecklistDetalle(null, checklist, checklistItemService.getById(33), checklistEvaluacionList.get(31), false));
				checklistDetalleList.add(new ChecklistDetalle(null, checklist, checklistItemService.getById(34), checklistEvaluacionList.get(32), true));
				checklistDetalleList.add(new ChecklistDetalle(null, checklist, checklistItemService.getById(35), checklistEvaluacionList.get(33), true));
				checklistDetalleList.add(new ChecklistDetalle(null, checklist, checklistItemService.getById(36), checklistEvaluacionList.get(34), true));
				checklistDetalleList.add(new ChecklistDetalle(null, checklist, checklistItemService.getById(37), checklistEvaluacionList.get(35), true));
				checklistDetalleList.add(new ChecklistDetalle(null, checklist, checklistItemService.getById(38), checklistEvaluacionList.get(36), true));
				checklistDetalleList.add(new ChecklistDetalle(null, checklist, checklistItemService.getById(39), checklistEvaluacionList.get(37), true));
			}
		} else if(checklist.getChecklistServicioTipo().getIdChecklistServicioTipo() == 2) { //20000 KMS
			if(checklistEvaluacionList.size()==40) {
				checklistDetalleList.add(new ChecklistDetalle(null, checklist, checklistItemService.getById(1), checklistEvaluacionList.get(0), false));
				checklistDetalleList.add(new ChecklistDetalle(null, checklist, checklistItemService.getById(2), checklistEvaluacionList.get(1), false));
				checklistDetalleList.add(new ChecklistDetalle(null, checklist, checklistItemService.getById(3), checklistEvaluacionList.get(2), false));
				checklistDetalleList.add(new ChecklistDetalle(null, checklist, checklistItemService.getById(4), checklistEvaluacionList.get(3), false));
				checklistDetalleList.add(new ChecklistDetalle(null, checklist, checklistItemService.getById(5), checklistEvaluacionList.get(4), false));
				checklistDetalleList.add(new ChecklistDetalle(null, checklist, checklistItemService.getById(6), checklistEvaluacionList.get(5), false));
				checklistDetalleList.add(new ChecklistDetalle(null, checklist, checklistItemService.getById(7), checklistEvaluacionList.get(6), false));
				checklistDetalleList.add(new ChecklistDetalle(null, checklist, checklistItemService.getById(8), checklistEvaluacionList.get(7), false));
				checklistDetalleList.add(new ChecklistDetalle(null, checklist, checklistItemService.getById(9), checklistEvaluacionList.get(8), false));
				checklistDetalleList.add(new ChecklistDetalle(null, checklist, checklistItemService.getById(10), checklistEvaluacionList.get(9), false));
				checklistDetalleList.add(new ChecklistDetalle(null, checklist, checklistItemService.getById(11), checklistEvaluacionList.get(10), false));
				checklistDetalleList.add(new ChecklistDetalle(null, checklist, checklistItemService.getById(40), checklistEvaluacionList.get(11), false));
				checklistDetalleList.add(new ChecklistDetalle(null, checklist, checklistItemService.getById(12), checklistEvaluacionList.get(12), false));
				checklistDetalleList.add(new ChecklistDetalle(null, checklist, checklistItemService.getById(13), checklistEvaluacionList.get(13), false));
				checklistDetalleList.add(new ChecklistDetalle(null, checklist, checklistItemService.getById(14), checklistEvaluacionList.get(14), false));
				checklistDetalleList.add(new ChecklistDetalle(null, checklist, checklistItemService.getById(15), checklistEvaluacionList.get(15), false));
				checklistDetalleList.add(new ChecklistDetalle(null, checklist, checklistItemService.getById(16), checklistEvaluacionList.get(16), false));
				checklistDetalleList.add(new ChecklistDetalle(null, checklist, checklistItemService.getById(17), checklistEvaluacionList.get(17), false));
				checklistDetalleList.add(new ChecklistDetalle(null, checklist, checklistItemService.getById(18), checklistEvaluacionList.get(18), false));
				checklistDetalleList.add(new ChecklistDetalle(null, checklist, checklistItemService.getById(19), checklistEvaluacionList.get(19), false));
				checklistDetalleList.add(new ChecklistDetalle(null, checklist, checklistItemService.getById(20), checklistEvaluacionList.get(20), false));
				checklistDetalleList.add(new ChecklistDetalle(null, checklist, checklistItemService.getById(21), checklistEvaluacionList.get(21), false));
				checklistDetalleList.add(new ChecklistDetalle(null, checklist, checklistItemService.getById(22), checklistEvaluacionList.get(22), false));
				checklistDetalleList.add(new ChecklistDetalle(null, checklist, checklistItemService.getById(23), checklistEvaluacionList.get(23), false));
				checklistDetalleList.add(new ChecklistDetalle(null, checklist, checklistItemService.getById(24), checklistEvaluacionList.get(24), false));
				checklistDetalleList.add(new ChecklistDetalle(null, checklist, checklistItemService.getById(25), checklistEvaluacionList.get(25), false));
				checklistDetalleList.add(new ChecklistDetalle(null, checklist, checklistItemService.getById(26), checklistEvaluacionList.get(26), false));
				checklistDetalleList.add(new ChecklistDetalle(null, checklist, checklistItemService.getById(27), checklistEvaluacionList.get(27), false));
				checklistDetalleList.add(new ChecklistDetalle(null, checklist, checklistItemService.getById(28), checklistEvaluacionList.get(28), false));
				checklistDetalleList.add(new ChecklistDetalle(null, checklist, checklistItemService.getById(29), checklistEvaluacionList.get(29), false));
				checklistDetalleList.add(new ChecklistDetalle(null, checklist, checklistItemService.getById(30), checklistEvaluacionList.get(30), false));
				checklistDetalleList.add(new ChecklistDetalle(null, checklist, checklistItemService.getById(31), checklistEvaluacionList.get(31), false));
				checklistDetalleList.add(new ChecklistDetalle(null, checklist, checklistItemService.getById(32), checklistEvaluacionList.get(32), false));
				checklistDetalleList.add(new ChecklistDetalle(null, checklist, checklistItemService.getById(33), checklistEvaluacionList.get(33), false));
				checklistDetalleList.add(new ChecklistDetalle(null, checklist, checklistItemService.getById(34), checklistEvaluacionList.get(34), true));
				checklistDetalleList.add(new ChecklistDetalle(null, checklist, checklistItemService.getById(35), checklistEvaluacionList.get(35), true));
				checklistDetalleList.add(new ChecklistDetalle(null, checklist, checklistItemService.getById(36), checklistEvaluacionList.get(36), true));
				checklistDetalleList.add(new ChecklistDetalle(null, checklist, checklistItemService.getById(37), checklistEvaluacionList.get(37), true));
				checklistDetalleList.add(new ChecklistDetalle(null, checklist, checklistItemService.getById(38), checklistEvaluacionList.get(38), true));
				checklistDetalleList.add(new ChecklistDetalle(null, checklist, checklistItemService.getById(39), checklistEvaluacionList.get(39), true));
			}
		}
		return checklistDetalleList;
	}

	@Override
	public List<ChecklistDetalle> createByChecklistTipo(Integer idChecklistTipo) throws Exception {
		List<ChecklistDetalle> checklistDetalleList = new ArrayList<ChecklistDetalle>();
		ChecklistEvaluacion checklistEvaluacion = new ChecklistEvaluacion();
		//Dependiendo del tipo de checklist asi se va a crear el detalle
		if(idChecklistTipo == 1) { //5000 KMS
			checklistDetalleList.add(new ChecklistDetalle(null, null, checklistItemService.getById(2), checklistEvaluacion, false));
			checklistDetalleList.add(new ChecklistDetalle(null, null, checklistItemService.getById(41), checklistEvaluacion, false));
			checklistDetalleList.add(new ChecklistDetalle(null, null, checklistItemService.getById(42), checklistEvaluacion, false));
			checklistDetalleList.add(new ChecklistDetalle(null, null, checklistItemService.getById(43), checklistEvaluacion, false));
			checklistDetalleList.add(new ChecklistDetalle(null, null, checklistItemService.getById(7), checklistEvaluacion, false));
			checklistDetalleList.add(new ChecklistDetalle(null, null, checklistItemService.getById(44), checklistEvaluacion, false));
			checklistDetalleList.add(new ChecklistDetalle(null, null, checklistItemService.getById(8), checklistEvaluacion, false));
			checklistDetalleList.add(new ChecklistDetalle(null, null, checklistItemService.getById(9), checklistEvaluacion, false));
			checklistDetalleList.add(new ChecklistDetalle(null, null, checklistItemService.getById(10), checklistEvaluacion, false));
			checklistDetalleList.add(new ChecklistDetalle(null, null, checklistItemService.getById(11), checklistEvaluacion, false));
			checklistDetalleList.add(new ChecklistDetalle(null, null, checklistItemService.getById(45), checklistEvaluacion, false));
			checklistDetalleList.add(new ChecklistDetalle(null, null, checklistItemService.getById(46), checklistEvaluacion, false));
			checklistDetalleList.add(new ChecklistDetalle(null, null, checklistItemService.getById(47), checklistEvaluacion, false));
			checklistDetalleList.add(new ChecklistDetalle(null, null, checklistItemService.getById(40), checklistEvaluacion, false));
			checklistDetalleList.add(new ChecklistDetalle(null, null, checklistItemService.getById(12), checklistEvaluacion, false));
			checklistDetalleList.add(new ChecklistDetalle(null, null, checklistItemService.getById(13), checklistEvaluacion, false));
			checklistDetalleList.add(new ChecklistDetalle(null, null, checklistItemService.getById(14), checklistEvaluacion, false));
			checklistDetalleList.add(new ChecklistDetalle(null, null, checklistItemService.getById(15), checklistEvaluacion, false));
			checklistDetalleList.add(new ChecklistDetalle(null, null, checklistItemService.getById(16), checklistEvaluacion, false));
			checklistDetalleList.add(new ChecklistDetalle(null, null, checklistItemService.getById(48), checklistEvaluacion, false));
			checklistDetalleList.add(new ChecklistDetalle(null, null, checklistItemService.getById(24), checklistEvaluacion, false));
			checklistDetalleList.add(new ChecklistDetalle(null, null, checklistItemService.getById(25), checklistEvaluacion, false));
			checklistDetalleList.add(new ChecklistDetalle(null, null, checklistItemService.getById(49), checklistEvaluacion, false));
			checklistDetalleList.add(new ChecklistDetalle(null, null, checklistItemService.getById(23), checklistEvaluacion, false));
			checklistDetalleList.add(new ChecklistDetalle(null, null, checklistItemService.getById(26), checklistEvaluacion, false));
			checklistDetalleList.add(new ChecklistDetalle(null, null, checklistItemService.getById(27), checklistEvaluacion, false));
			checklistDetalleList.add(new ChecklistDetalle(null, null, checklistItemService.getById(28), checklistEvaluacion, false));
			checklistDetalleList.add(new ChecklistDetalle(null, null, checklistItemService.getById(29), checklistEvaluacion, false));
			checklistDetalleList.add(new ChecklistDetalle(null, null, checklistItemService.getById(30), checklistEvaluacion, false));
			checklistDetalleList.add(new ChecklistDetalle(null, null, checklistItemService.getById(31), checklistEvaluacion, false));
			checklistDetalleList.add(new ChecklistDetalle(null, null, checklistItemService.getById(32), checklistEvaluacion, false));
			checklistDetalleList.add(new ChecklistDetalle(null, null, checklistItemService.getById(33), checklistEvaluacion, false));
			checklistDetalleList.add(new ChecklistDetalle(null, null, checklistItemService.getById(34), checklistEvaluacion, true));
			checklistDetalleList.add(new ChecklistDetalle(null, null, checklistItemService.getById(35), checklistEvaluacion, true));
			checklistDetalleList.add(new ChecklistDetalle(null, null, checklistItemService.getById(36), checklistEvaluacion, true));
			checklistDetalleList.add(new ChecklistDetalle(null, null, checklistItemService.getById(37), checklistEvaluacion, true));
			checklistDetalleList.add(new ChecklistDetalle(null, null, checklistItemService.getById(38), checklistEvaluacion, true));
			checklistDetalleList.add(new ChecklistDetalle(null, null, checklistItemService.getById(39), checklistEvaluacion, true));
		} else if(idChecklistTipo == 2) { //20000 KMS
			checklistDetalleList.add(new ChecklistDetalle(null, null, checklistItemService.getById(1), checklistEvaluacion, false));
			checklistDetalleList.add(new ChecklistDetalle(null, null, checklistItemService.getById(2), checklistEvaluacion, false));
			checklistDetalleList.add(new ChecklistDetalle(null, null, checklistItemService.getById(3), checklistEvaluacion, false));
			checklistDetalleList.add(new ChecklistDetalle(null, null, checklistItemService.getById(4), checklistEvaluacion, false));
			checklistDetalleList.add(new ChecklistDetalle(null, null, checklistItemService.getById(5), checklistEvaluacion, false));
			checklistDetalleList.add(new ChecklistDetalle(null, null, checklistItemService.getById(6), checklistEvaluacion, false));
			checklistDetalleList.add(new ChecklistDetalle(null, null, checklistItemService.getById(7), checklistEvaluacion, false));
			checklistDetalleList.add(new ChecklistDetalle(null, null, checklistItemService.getById(8), checklistEvaluacion, false));
			checklistDetalleList.add(new ChecklistDetalle(null, null, checklistItemService.getById(9), checklistEvaluacion, false));
			checklistDetalleList.add(new ChecklistDetalle(null, null, checklistItemService.getById(10), checklistEvaluacion, false));
			checklistDetalleList.add(new ChecklistDetalle(null, null, checklistItemService.getById(11), checklistEvaluacion, false));
			checklistDetalleList.add(new ChecklistDetalle(null, null, checklistItemService.getById(40), checklistEvaluacion, false));
			checklistDetalleList.add(new ChecklistDetalle(null, null, checklistItemService.getById(12), checklistEvaluacion, false));
			checklistDetalleList.add(new ChecklistDetalle(null, null, checklistItemService.getById(13), checklistEvaluacion, false));
			checklistDetalleList.add(new ChecklistDetalle(null, null, checklistItemService.getById(14), checklistEvaluacion, false));
			checklistDetalleList.add(new ChecklistDetalle(null, null, checklistItemService.getById(15), checklistEvaluacion, false));
			checklistDetalleList.add(new ChecklistDetalle(null, null, checklistItemService.getById(16), checklistEvaluacion, false));
			checklistDetalleList.add(new ChecklistDetalle(null, null, checklistItemService.getById(17), checklistEvaluacion, false));
			checklistDetalleList.add(new ChecklistDetalle(null, null, checklistItemService.getById(18), checklistEvaluacion, false));
			checklistDetalleList.add(new ChecklistDetalle(null, null, checklistItemService.getById(19), checklistEvaluacion, false));
			checklistDetalleList.add(new ChecklistDetalle(null, null, checklistItemService.getById(20), checklistEvaluacion, false));
			checklistDetalleList.add(new ChecklistDetalle(null, null, checklistItemService.getById(21), checklistEvaluacion, false));
			checklistDetalleList.add(new ChecklistDetalle(null, null, checklistItemService.getById(22), checklistEvaluacion, false));
			checklistDetalleList.add(new ChecklistDetalle(null, null, checklistItemService.getById(23), checklistEvaluacion, false));
			checklistDetalleList.add(new ChecklistDetalle(null, null, checklistItemService.getById(24), checklistEvaluacion, false));
			checklistDetalleList.add(new ChecklistDetalle(null, null, checklistItemService.getById(25), checklistEvaluacion, false));
			checklistDetalleList.add(new ChecklistDetalle(null, null, checklistItemService.getById(26), checklistEvaluacion, false));
			checklistDetalleList.add(new ChecklistDetalle(null, null, checklistItemService.getById(27), checklistEvaluacion, false));
			checklistDetalleList.add(new ChecklistDetalle(null, null, checklistItemService.getById(28), checklistEvaluacion, false));
			checklistDetalleList.add(new ChecklistDetalle(null, null, checklistItemService.getById(29), checklistEvaluacion, false));
			checklistDetalleList.add(new ChecklistDetalle(null, null, checklistItemService.getById(30), checklistEvaluacion, false));
			checklistDetalleList.add(new ChecklistDetalle(null, null, checklistItemService.getById(31), checklistEvaluacion, false));
			checklistDetalleList.add(new ChecklistDetalle(null, null, checklistItemService.getById(32), checklistEvaluacion, false));
			checklistDetalleList.add(new ChecklistDetalle(null, null, checklistItemService.getById(33), checklistEvaluacion, false));
			checklistDetalleList.add(new ChecklistDetalle(null, null, checklistItemService.getById(34), checklistEvaluacion, true));
			checklistDetalleList.add(new ChecklistDetalle(null, null, checklistItemService.getById(35), checklistEvaluacion, true));
			checklistDetalleList.add(new ChecklistDetalle(null, null, checklistItemService.getById(36), checklistEvaluacion, true));
			checklistDetalleList.add(new ChecklistDetalle(null, null, checklistItemService.getById(37), checklistEvaluacion, true));
			checklistDetalleList.add(new ChecklistDetalle(null, null, checklistItemService.getById(38), checklistEvaluacion, true));
			checklistDetalleList.add(new ChecklistDetalle(null, null, checklistItemService.getById(39), checklistEvaluacion, true));
		}
		return checklistDetalleList;
	}
	
}
