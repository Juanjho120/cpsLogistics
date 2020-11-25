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
 * Model for Table "checklist_items"
 * 
 * @author Juan Tzun
 */
@Entity
@Table(name = "checklist_items")
public class ChecklistItem {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idChecklistItem;
	
	@NotNull(message = "El nombre del item del checklist no puede ser nulo")
	@Size(min = 5, message = "El nombre del item del checklist debe tener por lo menos 5 caracteres")
	@Column(name = "nombre", nullable = false)
	private String nombre;
	
	public ChecklistItem() {}
	
	/**
	 * @param idChecklistItem
	 * @param nombre
	 */
	public ChecklistItem(Integer idChecklistItem, String nombre) {
		this.idChecklistItem = idChecklistItem;
		this.nombre = nombre;
	}

	/**
	 * @return the idChecklistItem
	 */
	public Integer getIdChecklistItem() {
		return idChecklistItem;
	}

	/**
	 * @param idChecklistItem the idChecklistItem to set
	 */
	public void setIdChecklistItem(Integer idChecklistItem) {
		this.idChecklistItem = idChecklistItem;
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
