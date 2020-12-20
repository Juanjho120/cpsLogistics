package proteus.permiso.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * Model for Table "permisos"
 * 
 * @author Juan Tzun
 */
@Entity
@Table(name = "permisos")
public class Permiso {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idPermiso;
	
	@NotNull(message = "El nombre del permiso no puede ser nulo")
	@Size(min = 4, message = "El nombre del permiso debe tener por lo menos 4 caracteres")
	@Column(name = "nombre", nullable = false, length = 30)
	private String nombre;
	
	public Permiso() {}

	/**
	 * @param idPermiso
	 * @param nombre
	 */
	public Permiso(Integer idPermiso, String nombre) {
		this.idPermiso = idPermiso;
		this.nombre = nombre;
	}

	/**
	 * @return the idPermiso
	 */
	public Integer getIdPermiso() {
		return idPermiso;
	}

	/**
	 * @param idPermiso the idPermiso to set
	 */
	public void setIdPermiso(Integer idPermiso) {
		this.idPermiso = idPermiso;
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
