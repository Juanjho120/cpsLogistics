package proteus.rol.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * Model for Table "roles"
 * 
 * @author Juan Tzun
 */
@Entity
@Table(name = "roles")
public class Rol {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idRol;
	
	@NotNull(message = "El nombre del rol no puede ser nulo")
	@Size(min = 3, message = "El nombre del rol debe tener por lo menos 3 caracteres")
	@Column(name = "nombre", nullable = false)
	private String nombre;
	
	public Rol() {}
	
	/**
	 * @param idRol
	 * @param nombre
	 */
	public Rol(Integer idRol, String nombre) {
		this.idRol = idRol;
		this.nombre = nombre;
	}

	/**
	 * @return the idRol
	 */
	public Integer getIdRol() {
		return idRol;
	}

	/**
	 * @param idRol the idRol to set
	 */
	public void setIdRol(Integer idRol) {
		this.idRol = idRol;
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
