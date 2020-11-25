package proteus.personal.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * Model for Table "personal_puestos"
 * 
 * @author Juan Tzun
 */
@Entity
@Table(name = "personal_puestos")
public class PersonalPuesto {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idPersonalPuesto;
	
	@NotNull(message = "El nombre del puesto del personal no puede ser nulo")
	@Size(min = 3, message = "El nombre del puesto del personal debe tener por lo menos 3 caracteres")
	@Column(name = "nombre", nullable = false, length = 20)
	private String nombre;
	
	public PersonalPuesto() {}
	
	/**
	 * @param idPersonalPuesto
	 * @param nombre
	 */
	public PersonalPuesto(Integer idPersonalPuesto, String nombre) {
		this.idPersonalPuesto = idPersonalPuesto;
		this.nombre = nombre;
	}

	/**
	 * @return the idPersonalPuesto
	 */
	public Integer getIdPersonalPuesto() {
		return idPersonalPuesto;
	}

	/**
	 * @param idPersonalPuesto the idPersonalPuesto to set
	 */
	public void setIdPersonalPuesto(Integer idPersonalPuesto) {
		this.idPersonalPuesto = idPersonalPuesto;
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
