package proteus.proveedor.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * Model for Table "proveedores"
 * 
 * @author Juan Tzun
 */
@Entity
@Table(name = "proveedores_menores")
public class ProveedorMenor {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idProveedorMenor;
	
	@NotNull(message = "El nombre del proveedor menor no puede ser nulo")
	@Size(min = 3, message = "El nombre del proveedor menor debe tener por lo menos 3 caracteres")
	@Column(name = "nombre", nullable = false, length = 30)
	private String nombre;
	
	public ProveedorMenor() {}

	/**
	 * @param idProveedorMenor
	 * @param nombre
	 */
	public ProveedorMenor(Integer idProveedorMenor, String nombre) {
		this.idProveedorMenor = idProveedorMenor;
		this.nombre = nombre;
	}

	/**
	 * @return the idProveedorMenor
	 */
	public Integer getIdProveedorMenor() {
		return idProveedorMenor;
	}

	/**
	 * @param idProveedorMenor the idProveedorMenor to set
	 */
	public void setIdProveedorMenor(Integer idProveedorMenor) {
		this.idProveedorMenor = idProveedorMenor;
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
