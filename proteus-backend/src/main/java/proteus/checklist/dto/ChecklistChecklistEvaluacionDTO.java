package proteus.checklist.dto;

import java.util.List;

import proteus.checklist.model.Checklist;
import proteus.checklist.model.ChecklistEvaluacion;

/**
 * DTO para la creacion de un checklist
 * 
 * @author Juan Tzun
 */
public class ChecklistChecklistEvaluacionDTO {

	private Checklist checklist;
	private List<ChecklistEvaluacion> checklistEvaluacion;
	
	public ChecklistChecklistEvaluacionDTO() {}

	/**
	 * @param checklist
	 * @param checklistEvaluacion
	 */
	public ChecklistChecklistEvaluacionDTO(Checklist checklist, List<ChecklistEvaluacion> checklistEvaluacion) {
		this.checklist = checklist;
		this.checklistEvaluacion = checklistEvaluacion;
	}

	/**
	 * @return the checklist
	 */
	public Checklist getChecklist() {
		return checklist;
	}

	/**
	 * @param checklist the checklist to set
	 */
	public void setChecklist(Checklist checklist) {
		this.checklist = checklist;
	}

	/**
	 * @return the checklistEvaluacion
	 */
	public List<ChecklistEvaluacion> getChecklistEvaluacion() {
		return checklistEvaluacion;
	}

	/**
	 * @param checklistEvaluacion the checklistEvaluacion to set
	 */
	public void setChecklistEvaluacion(List<ChecklistEvaluacion> checklistEvaluacion) {
		this.checklistEvaluacion = checklistEvaluacion;
	}
	
}
