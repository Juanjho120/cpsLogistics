package proteus.checklist.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * Model for Table "checklist_evaluaciones"
 * 
 * @author Juan Tzun
 */
@Entity
@Table(name = "checklist_evaluaciones")
public class ChecklistEvaluacion {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idChecklistEvaluacion;
	
	@NotNull(message = "El nombre de la opcion no puede ser nulo")
	@Size(min = 3, message = "El nombre de la opcion debe tener por lo menos 3 caracteres")
	@Column(name = "nombre", nullable = false, length = 20)
	private String nombre;
	
	public ChecklistEvaluacion() {}
	
	/**
	 * @param idChecklistOpcion
	 * @param nombre
	 */
	public ChecklistEvaluacion(Integer idChecklistEvaluacion, String nombre) {
		this.idChecklistEvaluacion = idChecklistEvaluacion;
		this.nombre = nombre;
	}

	/**
	 * @return the idChecklistEvaluacion
	 */
	public Integer getIdChecklistEvaluacion() {
		return idChecklistEvaluacion;
	}

	/**
	 * @param idChecklistEvaluacion the idChecklistEvaluacion to set
	 */
	public void setIdChecklistEvaluacion(Integer idChecklistEvaluacion) {
		this.idChecklistEvaluacion = idChecklistEvaluacion;
	}

	/**
	 * @return the nombre
	 */
	public String getNombre() {
		return nombre;
	}

	/**
	 * @param nombre the nombre to set
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
}
