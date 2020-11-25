package proteus.checklist.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * Model for Table "checklist_detalles"
 * 
 * @author Juan Tzun
 */
@Entity
@Table(name = "checklist_detalles")
public class ChecklistDetalle {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idChecklistDetalle;
	
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name = "id_checklist", nullable = false, foreignKey = @ForeignKey(name = "fkChecklistDetalleChecklist"))
	private Checklist checklist;
	
	@NotNull(message = "El item del checklist no puede ser nulo")
	@ManyToOne
	@JoinColumn(name = "id_checklist_item", nullable = false, foreignKey = @ForeignKey(name = "fkChecklistDetalleChecklistItem"))
	private ChecklistItem checklistItem;
	
	@NotNull(message = "El item del checklist no puede ser nulo")
	@ManyToOne
	@JoinColumn(name = "id_checklist_evaluacion", nullable = false, foreignKey = @ForeignKey(name = "fkChecklistDetalleChecklistEvaluacion"))
	private ChecklistEvaluacion checklistEvaluacion;
	
	@Column(name = "electrico")
	private Boolean electrico;
	
	public ChecklistDetalle() {}

	/**
	 * @param idChecklistDetalle
	 * @param checklist
	 * @param checklistItem
	 * @param checklistEvaluacion
	 * @param electrico
	 */
	public ChecklistDetalle(Integer idChecklistDetalle, Checklist checklist, ChecklistItem checklistItem, ChecklistEvaluacion checklistEvaluacion,
			Boolean electrico) {
		this.idChecklistDetalle = idChecklistDetalle;
		this.checklist = checklist;
		this.checklistItem = checklistItem;
		this.checklistEvaluacion = checklistEvaluacion;
		this.electrico = electrico;
	}

	/**
	 * @return the idChecklistDetalle
	 */
	public Integer getIdChecklistDetalle() {
		return idChecklistDetalle;
	}

	/**
	 * @param idChecklistDetalle the idChecklistDetalle to set
	 */
	public void setIdChecklistDetalle(Integer idChecklistDetalle) {
		this.idChecklistDetalle = idChecklistDetalle;
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
	 * @return the checklistItem
	 */
	public ChecklistItem getChecklistItem() {
		return checklistItem;
	}

	/**
	 * @param checklistItem the checklistItem to set
	 */
	public void setChecklistItem(ChecklistItem checklistItem) {
		this.checklistItem = checklistItem;
	}

	/**
	 * @return the checklistEvaluacion
	 */
	public ChecklistEvaluacion getChecklistEvaluacion() {
		return checklistEvaluacion;
	}

	/**
	 * @param checklistEvaluacion the checklistEvaluacion to set
	 */
	public void setChecklistEvaluacion(ChecklistEvaluacion checklistEvaluacion) {
		this.checklistEvaluacion = checklistEvaluacion;
	}

	/**
	 * @return the electrico
	 */
	public Boolean getElectrico() {
		return electrico;
	}

	/**
	 * @param electrico the electrico to set
	 */
	public void setElectrico(Boolean electrico) {
		this.electrico = electrico;
	}
	
}
