package proteus.checklist.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import proteus.checklist.model.ChecklistEvaluacion;
import proteus.checklist.repository.IChecklistEvaluacionRepository;
import proteus.checklist.service.IChecklistEvaluacionService;
import proteus.generico.repository.IGenericRepository;
import proteus.generico.service.CRUDImpl;

/**
 * Services for ChecklistEvaluacion Model (Implementation)
 * 
 * @author Juan Tzun
 */
@Service
public class ChecklistEvaluacionServiceImpl extends CRUDImpl<ChecklistEvaluacion, Integer> implements IChecklistEvaluacionService {

	@Autowired
	private IChecklistEvaluacionRepository checklistEvaluacionRepository;

	@Override
	protected IGenericRepository<ChecklistEvaluacion, Integer> getRepository() {
		return checklistEvaluacionRepository;
	}

	@Override
	public ChecklistEvaluacion getByNombre(String nombre) throws Exception {
		return checklistEvaluacionRepository.findByNombre(nombre);
	}
	
	@Override
	public ChecklistEvaluacion create(ChecklistEvaluacion checklistEvaluacion) throws Exception {
		ChecklistEvaluacion checklistEvaluacionAux = this.getByNombre(checklistEvaluacion.getNombre());
		if(checklistEvaluacionAux==null) {
			return checklistEvaluacionRepository.save(checklistEvaluacion);
		}
		return null;
	}
	
}