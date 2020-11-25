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
 * Model for Table "checklist_servicio_tipos"
 * 
 * @author Juan Tzun
 */
@Entity
@Table(name = "checklist_servicio_tipos")
public class ChecklistServicioTipo {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idChecklistServicioTipo;
	
	@NotNull(message = "El nombre del tipo de servicio del checklist no puede ser nulo")
	@Size(min = 3, message = "El nombre del tipo de servicio del checklist debe tener por lo menos 3 caracteres")
	@Column(name = "nombre", nullable = false, length = 20)
	private String nombre;
	
	public ChecklistServicioTipo() {}

	/**
	 * @param idChecklistServicioTipo
	 * @param nombre
	 */
	public ChecklistServicioTipo(Integer idChecklistServicioTipo, String nombre) {
		this.idChecklistServicioTipo = idChecklistServicioTipo;
		this.nombre = nombre;
	}

	/**
	 * @return the idChecklistServicioTipo
	 */
	public Integer getIdChecklistServicioTipo() {
		return idChecklistServicioTipo;
	}

	/**
	 * @param idChecklistServicioTipo the idChecklistServicioTipo to set
	 */
	public void setIdChecklistServicioTipo(Integer idChecklistServicioTipo) {
		this.idChecklistServicioTipo = idChecklistServicioTipo;
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
