package proteus.checklist.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import proteus.checklist.model.ChecklistServicioTipo;
import proteus.checklist.repository.IChecklistServicioTipoRepository;
import proteus.checklist.service.IChecklistServicioTipoService;
import proteus.generico.repository.IGenericRepository;
import proteus.generico.service.CRUDImpl;

/**
 * Services for ChecklistServicioTipo Model (Implementation)
 * 
 * @author Juan Tzun
 */
@Service
public class ChecklistServicioTipoServiceImpl extends CRUDImpl<ChecklistServicioTipo, Integer> implements IChecklistServicioTipoService {

	@Autowired
	private IChecklistServicioTipoRepository checklistServicioTipoRepository;

	@Override
	protected IGenericRepository<ChecklistServicioTipo, Integer> getRepository() {
		return checklistServicioTipoRepository;
	}

	@Override
	public ChecklistServicioTipo getByNombre(String nombre) throws Exception {
		return checklistServicioTipoRepository.findByNombre(nombre);
	}
	
	@Override
	public ChecklistServicioTipo create(ChecklistServicioTipo checklistServicioTipo) throws Exception{
		ChecklistServicioTipo checklistServicioTipoAux = this.getByNombre(checklistServicioTipo.getNombre());
		if(checklistServicioTipoAux==null) {
			return checklistServicioTipoRepository.save(checklistServicioTipo);
		}
		return null;
	}
	
}